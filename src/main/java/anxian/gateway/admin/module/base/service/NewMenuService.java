package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.model.MenuModel;
import anxian.gateway.admin.module.base.repository.NewMenuRepository;
import org.apache.commons.collections.CollectionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单业务逻辑处理
 */
@Service
public class NewMenuService {

    @Autowired
    private NewMenuRepository newMenuRepository;

    // TODO 菜单保存，列表表示

    // 菜单展示
    public List<MenuModel> showMenu(List<NewMenu> newMenus) {
        // 获取一级菜单
        List<NewMenu> firstMenus = null;
        boolean isAdmin = CollectionUtils.isEmpty(newMenus);
        if (isAdmin) {
            firstMenus = newMenuRepository.findByIsParentOrderBySortAsc(true);
        } else {
            Set<ObjectId> parentIdSet = newMenus.stream().map(NewMenu::getParentId)
                    .collect(Collectors.toSet());
            firstMenus = newMenuRepository.findByIdInOrderBySortAsc(parentIdSet);
        }

        List<MenuModel> menuList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(firstMenus)) {
            for (NewMenu firstMenu : firstMenus) {
                MenuModel parentMenu = new MenuModel();
                parentMenu.setSort(firstMenu.getSort());
                parentMenu.setExpanded(firstMenu.getExpanded());
                parentMenu.setUrl(firstMenu.getUrl());
                parentMenu.setText(firstMenu.getText());
                ObjectId parentId = firstMenu.getId();
                List<NewMenu> childrenMenus = null;
                if (isAdmin) {
                    childrenMenus = newMenuRepository.findByParentIdAndLeafOrderBySortAsc(parentId, true);
                } else {
                    childrenMenus = newMenus.stream().filter(newMenu -> newMenu.getParentId() != null && newMenu.getParentId().compareTo(parentId) == 0).collect(Collectors.toList());
                }
                if (CollectionUtils.isNotEmpty(childrenMenus)) {
                    List<MenuModel> children = new ArrayList<>();
                    for (NewMenu childMenu : childrenMenus) {
                        MenuModel menuModel = new MenuModel();
                        menuModel.setExpanded(childMenu.getExpanded());
                        menuModel.setSort(childMenu.getSort());
                        menuModel.setText(childMenu.getText());
                        menuModel.setUrl(childMenu.getUrl());
                        children.add(menuModel);
                    }
                    parentMenu.setChildren(children);
                }
                menuList.add(parentMenu);
            }
        }
        return menuList;
    }

}
