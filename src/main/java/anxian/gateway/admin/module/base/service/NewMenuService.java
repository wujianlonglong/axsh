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

/**
 * 菜单业务逻辑处理
 */
@Service
public class NewMenuService {

    @Autowired
    private NewMenuRepository newMenuRepository;

    // TODO 菜单保存，列表表示

    // 菜单展示【高级管理员】
    public List<MenuModel> showMenu() {
        // 获取一级菜单
        List<NewMenu> firstMenus = newMenuRepository.findByIsParentOrderBySortAsc(true);
        List<MenuModel> menuList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(firstMenus)) {
            for (NewMenu firstMenu : firstMenus) {
                MenuModel parentMenu = new MenuModel();
                parentMenu.setSort(firstMenu.getSort());
                parentMenu.setExpanded(firstMenu.getExpanded());
                parentMenu.setUrl(firstMenu.getUrl());
                parentMenu.setText(firstMenu.getText());
                ObjectId parentId = firstMenu.getId();
                List<NewMenu> childrenMenus = newMenuRepository.findByParentIdAndLeafOrderBySortAsc(parentId, true);
                if (CollectionUtils.isNotEmpty(childrenMenus)) {
                    List<MenuModel> children = new ArrayList<>();
                    for (NewMenu childMenu : childrenMenus) {
                        MenuModel menuModel = new MenuModel();
                        // TODO 获取对应菜单的权限

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
