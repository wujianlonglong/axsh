package anxian.gateway.admin.module.base.service.impl;

import anxian.gateway.admin.module.base.domain.Authority;
import anxian.gateway.admin.module.base.domain.Menu;
import anxian.gateway.admin.module.base.domain.RoleAuthority;
import anxian.gateway.admin.module.base.model.AuthTreeChild;
import anxian.gateway.admin.module.base.model.AuthTreeParent;
import anxian.gateway.admin.module.base.repository.AuthorityRepository;
import anxian.gateway.admin.module.base.repository.MenuRepository;
import anxian.gateway.admin.module.base.repository.RoleAuthRepository;
import anxian.gateway.admin.module.base.repository.UserAuthorityRepository;
import anxian.gateway.admin.module.base.service.AuthorityService;
import anxian.gateway.admin.module.security.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-18.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleAuthRepository roleAuthRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    /**
     * 根据菜单返回权限
     */
    @Override
    public List<Authority> findByMenu(Menu menu) {
        return authorityRepository.findByMenu(menu);
    }

    /**
     * 保存权限资源
     */
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    /**
     * 根据ID查询权限
     */
    @Override
    public Authority findById(Long id) {
        return authorityRepository.findOne(id);
    }

    /**
     * 根据ID删除权限
     */
    @Override
    public void remove(Long id) {
        Authority authority = authorityRepository.findOne(id);

        roleAuthRepository.removeByAuth(authority);

        userAuthorityRepository.removeByAuth(authority);

        authorityRepository.delete(id);
    }

    /**
     * 修改权限
     */
    @Override
    public void update(Long id, String authorityname, String authoritytype, String description, String displayref) {
        authorityRepository.update(id, authorityname, authoritytype, description, displayref);
    }

    /**
     * 根据角色返回关联的权限树
     */
    @Override
    public List<AuthTreeParent> authTreeForRole(Long role) {
        List<AuthTreeParent> authTreeParents = new ArrayList<>();

        //角色关联的权限
        List<RoleAuthority> roleAuthorities = roleAuthRepository.findByRole(role);

        //所有父菜单
        List<Menu> parentMenus = menuRepository.findIsParent();


        //循环父菜单，取出子菜单，拼接树
        for (Menu parentMenu : parentMenus) {
            AuthTreeParent authTreeParent = new AuthTreeParent();
            authTreeParent.setId(parentMenu.getId());
            authTreeParent.setText(parentMenu.getText());
            authTreeParent.setParent(parentMenu.isParent());
            authTreeParent.setExpanded(true);
            authTreeParent.setChecked(false);

            List<AuthTreeChild> authTreeChildren = new ArrayList<>();

            //不是超级管理员不能够给别的角色赋予“角色管理”和“权限资源管理”这个两个菜单的权限
            List<String> notContains = new ArrayList<>();
            if (!UserContextHelper.getUserContext().isSupperManager()) {
                notContains.add("角色管理");
                notContains.add("权限资源管理");
            }

            //循环子菜单
            for (Menu childMenu : parentMenu.getChildren()) {
                if (!notContains.contains(childMenu.getText())) {//不在刚才被限制的菜单中
                    AuthTreeChild authTreeChild = new AuthTreeChild();
                    authTreeChild.setId(parentMenu.getText() + "&" + childMenu.getId());
                    authTreeChild.setText(childMenu.getText());
                    authTreeChild.setLeaf(false);
                    authTreeChild.setChecked(false);

                    List<AuthTreeChild> authorityTrees = new ArrayList<>();
                    List<Authority> authorities = authorityRepository.findByMenu(childMenu);//取出此菜单的操作权限
                    //循环权限
                    for (Authority authority : authorities) {
                        AuthTreeChild authorityTree = new AuthTreeChild();
                        authorityTree.setId(childMenu.getText() + "&" + authority.getId());
                        //type为1就显示列表，2显示保存，3删除，4显示配置
                        authorityTree.setText(authority.getAuthoritytype() == 1 ? "列表" : authority.getAuthoritytype() == 2 ? "保存" : authority.getAuthoritytype() == 3 ? "删除" : authority.getAuthoritytype() == 4 ? "配置" : authority.getAuthoritytype() == 5 ? "导出" : authority.getAuthoritytype() == 6 ? "打印" : "拆分");
                        authorityTree.setLeaf(true);

                        //把该角色与关联的权限勾上
                        for (RoleAuthority roleAuthority : roleAuthorities) {
                            if (roleAuthority.getAuthority().getId() == authority.getId()) {
                                authorityTree.setChecked(true);
                                break;
                            }

                            authorityTree.setChecked(false);
                        }

                        authorityTrees.add(authorityTree);
                    }

                    authTreeChild.setChildren(authorityTrees);
                    authTreeChildren.add(authTreeChild);
                }

            }

            authTreeParent.setChildren(authTreeChildren);
            authTreeParents.add(authTreeParent);
        }


        return authTreeParents;
    }

    /**
     * 分页列表
     */
    @Override
    public Page<Authority> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter) {

        Specification<Authority> spec = new Specification<Authority>() {
            public Predicate toPredicate(Root<Authority> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                if (!filter.isEmpty()) {
                    for (Map.Entry<Object, Object> entry : filter.entrySet()) {
                        if (entry.getValue() != null) {
                            if (!entry.getValue().equals("") && !entry.getValue().equals("null")) {
                                Predicate p = cb.like(root.get(entry.getKey().toString()).as(String.class), "%" + entry.getValue().toString() + "%");
                                query.where(cb.and(p));
                            }
                        }

                    }
                }


                return query.getRestriction();
            }
        };

        Page<Authority> rolePage = authorityRepository.findAll(spec, buildPageRequest(pageNumber, pageSize, "authorityname"));

        return rolePage;
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "id");
        } else {
            sort = new Sort(Sort.Direction.ASC, sortType);
        }

        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }
}
