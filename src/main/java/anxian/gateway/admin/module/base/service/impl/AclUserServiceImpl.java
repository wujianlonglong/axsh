package anxian.gateway.admin.module.base.service.impl;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.base.domain.UserAuthority;
import anxian.gateway.admin.module.base.domain.UserRole;
import anxian.gateway.admin.module.base.domain.UserType;
import anxian.gateway.admin.module.base.model.UserJson;
import anxian.gateway.admin.module.base.repository.AclUserRepository;
import anxian.gateway.admin.module.base.repository.RoleUserRepository;
import anxian.gateway.admin.module.base.repository.UserAuthorityRepository;
import anxian.gateway.admin.module.base.service.AclUserService;
import anxian.gateway.admin.utils.BeanUtil;
import anxian.gateway.admin.utils.PasswordUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-16.
 */
@Service
public class AclUserServiceImpl implements AclUserService {

    @Autowired
    private AclUserRepository aclUserRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;

    /**
     * 保存用户
     */
    @Override
    public AclUser save(AclUser aclUser) {
        AclUser aclUserDB = aclUserRepository.findByName(aclUser.getUsername());
        if (aclUserDB != null) {
            if (!aclUser.getPassword().equals(aclUserDB.getPassword())) {
                aclUser.setPassword(PasswordUtil.generatePassword(aclUser.getPassword(), aclUser.getUsername()));
            }
        } else {
            aclUser.setPassword(PasswordUtil.generatePassword(aclUser.getPassword(), aclUser.getUsername()));
        }
        return aclUserRepository.save(aclUser);
    }

    /**
     * 根据ID删除用户
     */
    @Override
    public void del(Long id) {
        AclUser aclUser = aclUserRepository.findOne(id);

        List<UserAuthority> listByUser = userAuthorityRepository.findListByUser(aclUser);
        userAuthorityRepository.delete(listByUser);

        List<UserRole> byUser = roleUserRepository.findByUser(aclUser);
        roleUserRepository.delete(byUser);

        aclUserRepository.delete(id);
    }

    /**
     * 根据用户名返回用户
     */
    @Override
    public AclUser findByName(String username) {
        return aclUserRepository.findByName(username);
    }

    /**
     * 返回该部门下包括和不包括在此角色下的用户
     */
    @Override
    public Map userInOrNotInOrg(Long roleId, Long orgId) {
        //已经关联的用户
        List<AclUser> ins = aclUserRepository.getAclUserByRoleAndOrg(roleId, orgId, UserType.USERTYPE_ADMINISTRATOR);
        List<UserJson> inJson = new ArrayList<>();
        for (AclUser in : ins) {
            UserJson userJson = new UserJson();
            BeanUtil.setBean2Bean(in, userJson);
            inJson.add(userJson);
        }

        //没有关联的用户
        List<AclUser> notIns = aclUserRepository.getNotInAclUserByRoleAndOrg(roleId, orgId, UserType.USERTYPE_ADMINISTRATOR);
        List<UserJson> notInJson = new ArrayList<>();
        for (AclUser notIn : notIns) {
            UserJson userJson = new UserJson();
            BeanUtil.setBean2Bean(notIn, userJson);
            notInJson.add(userJson);
        }


        Map map = new HashMap<>();
        map.put("in", inJson);
        map.put("notIn", notInJson);
        map.put("state", "success");

        return map;
    }

    @Override
    public void updatePwd(String password, Long id) {
        aclUserRepository.updatePwd(password, id);
    }

    /**
     * 分页列表
     */
    @Override
    public Page<AclUser> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter) {

        Specification<AclUser> spec = new Specification<AclUser>() {
            public Predicate toPredicate(Root<AclUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //组装查询条件
                if (!filter.isEmpty()) {
                    for (Map.Entry<Object, Object> entry : filter.entrySet()) {
                        if (entry.getValue() != null) {
                            if (!entry.getValue().equals("") && !entry.getValue().equals("null")) {
                                list.add(cb.like(root.get(entry.getKey().toString()).as(String.class), "%" + entry.getValue().toString() + "%"));

                            }
                        }

                    }
                }

                list.add(cb.notEqual(root.get("username").as(String.class), "administrator"));

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        Page<AclUser> rolePage = aclUserRepository.findAll(spec, buildPageRequest(pageNumber, pageSize, "auto"));

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
