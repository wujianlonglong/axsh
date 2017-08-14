package anxian.gateway.admin.module.base.service.impl;

import anxian.gateway.admin.module.base.domain.Authority;
import anxian.gateway.admin.module.base.domain.Menu;
import anxian.gateway.admin.module.base.model.MenuJson;
import anxian.gateway.admin.module.base.repository.AuthorityRepository;
import anxian.gateway.admin.module.base.repository.MenuRepository;
import anxian.gateway.admin.module.base.service.MenuService;
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
 * Created by jiangzhe on 15-11-20.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Menu findById(Long id) {
        return menuRepository.findOne(id);
    }

    @Override
    public Menu save(Menu menu) {
        if (menu.getId() == null) {//新增
            // 如果是父菜单
            if (menu.isParent()) {
                List<Menu> parentMenuList = menuRepository.findIsParent();
                menu.setSort(parentMenuList.size() + 1);

            } else { // 是子菜单
                Long parentMenuId = menu.getParentMenu().getId();
                menu.setSort(menu.getParentMenu().getSort() * 10 + menuRepository.findByParentMenu(parentMenuId).size
                        () + 1);
            }
        } else {
            Menu oldMenu = menuRepository.findOne(menu.getId());
            menu.setSort(oldMenu.getSort());
        }
        return menuRepository.save(menu);
    }

    @Override
    public void del(Long id) {
        Menu menu = menuRepository.findOne(id);

        List<Authority> byMenu = authorityRepository.findByMenu(menu);
        authorityRepository.delete(byMenu);

        menuRepository.remove(id);
    }

    @Override
    public Page<Menu> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter) {
        Specification<Menu> spec = new Specification<Menu>() {
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //组装查询条件
                if (!filter.isEmpty()) {
                    for (Map.Entry<Object, Object> entry : filter.entrySet()) {
                        if (entry.getValue() != null) {
                            if (!entry.getValue().equals("") && !entry.getValue().equals("null")) {
                                Predicate p = cb.like(root.get(entry.getKey().toString()).as(String.class), "%" +
                                        entry.getValue().toString() + "%");
                                query.where(cb.and(p));
                            }
                        }

                    }
                }


                return query.getRestriction();
            }
        };

        Page<Menu> menuPage = menuRepository.findAll(spec, buildPageRequest(pageNumber, pageSize, "auto"));

        return menuPage;
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

    /**
     * 返回菜单树
     */
    @Override
    public List<MenuJson> loadTree() {

        List<Menu> isParent = menuRepository.findIsParent();//返回所有父菜单

        //循环父菜单，取出子菜单
        List<MenuJson> menuJsons = new ArrayList<>();
        for (Menu parentMenu : isParent) {
            MenuJson parentJson = new MenuJson();
            parentJson.setId(parentMenu.getId());
            parentJson.setText(parentMenu.getText());
            parentJson.setLeaf(false);

            //循环子菜单
            List<MenuJson> children = new ArrayList<>();
            for (Menu childMenu : parentMenu.getChildren()) {
                MenuJson childJson = new MenuJson();
                childJson.setId(childMenu.getId());
                childJson.setText(childMenu.getText());
                childJson.setLeaf(true);
                children.add(childJson);
            }
            parentJson.setChildren(children);
            menuJsons.add(parentJson);
        }

        return menuJsons;
    }
}
