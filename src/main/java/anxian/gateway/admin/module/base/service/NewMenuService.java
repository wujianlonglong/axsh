package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.model.MenuModel;
import anxian.gateway.admin.module.base.model.MenuRoleModel;
import anxian.gateway.admin.module.base.model.NewMenuModel;
import anxian.gateway.admin.module.base.repository.NewMenuRepository;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单业务逻辑处理
 */
@Slf4j
@Service
public class NewMenuService {

    @Autowired
    private NewMenuRepository newMenuRepository;

    /**
     * 后台菜单列表
     *
     * @param menuName
     * @param pageable
     * @return
     */
    public ResponseMessage list(String menuName, Pageable pageable) {
        Page<NewMenu> newMenuPage = null;
        if (StringUtils.isEmpty(menuName)) {
            newMenuPage = newMenuRepository.findAll(pageable);
        } else {
            newMenuPage = newMenuRepository.findByTextLike(menuName, pageable);
        }

        List<NewMenu> newMenus = newMenuPage.getContent();
        List<NewMenuModel> newMenuModels = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(newMenus)) {
            for (NewMenu newMenu : newMenus) {
                NewMenuModel newMenuModel = new NewMenuModel();
                newMenuModel.setId(newMenu.getId());
                newMenuModel.setExpanded(newMenu.getExpanded());
                newMenuModel.setLeaf(newMenu.isLeaf());
                newMenuModel.setParent(newMenu.isParent());
                newMenuModel.setUrl(newMenu.getUrl());
                newMenuModel.setText(newMenu.getText());
                newMenuModel.setSort(newMenu.getSort());
                if (newMenu.isLeaf()) {
                    NewMenu parentNewMenu = newMenuRepository.findOne(newMenu.getParentId());
                    newMenuModel.setParentMenu(parentNewMenu.getText());
                }
                newMenuModels.add(newMenuModel);
            }
        }

        return ResponseMessage.success(new PageImpl<>(newMenuModels, pageable, newMenuPage.getTotalElements()));
    }

    // 菜单展示
    public List<MenuModel> showMenu(List<NewMenu> newMenus) {
        // 获取一级菜单
        List<NewMenu> firstMenus = null;
        boolean isAdmin = CollectionUtils.isEmpty(newMenus);
        if (isAdmin) {
            firstMenus = newMenuRepository.findByIsParentOrderBySortAsc(true);
        } else {
            Set<String> parentIdSet = newMenus.stream().map(NewMenu::getParentId)
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
                String parentId = firstMenu.getId();
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

    /**
     * 查询父菜单列表
     *
     * @return
     */
    public ResponseMessage getParentMenus() {
        List<NewMenu> parentMenus = newMenuRepository.findByIsParentAndLeafOrderBySortAsc(true, false);
        return CollectionUtils.isEmpty(parentMenus) ? ResponseMessage.success(null) : ResponseMessage.success(parentMenus);
    }

    /**
     * 根据id获取菜单对象
     *
     * @param id
     * @return
     */
    public ResponseMessage getById(String id) {

        if (StringUtils.isEmpty(id)) {
            return ResponseMessage.error("菜单ID为空！");
        }

        NewMenu newMenu = newMenuRepository.findOne(id);
        if (null == newMenu) {
            return ResponseMessage.error("没有对应的菜单！");
        }

        return ResponseMessage.success(newMenu);
    }

    public NewMenu getOne(String id) {
        return newMenuRepository.findOne(id);
    }

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 保存菜单信息
     *
     * @param newMenu
     * @return
     */
    public ResponseMessage save(NewMenu newMenu) {

        try {
            log.info(objectMapper.writeValueAsString(newMenu));
        } catch (IOException ex) {

        }

        if (null == newMenu) {
            return ResponseMessage.error("保存菜单信息失败！");
        }

        String id = newMenu.getId();
        NewMenu newMenuResult = null;
        if (StringUtils.isEmpty(id)) {
            newMenuResult = new NewMenu();
            newMenuResult.setCreator("");
            newMenuResult.setCreateDateTime(LocalDateTime.now());
        } else {
            newMenuResult = newMenuRepository.findOne(id);
            newMenuResult.setUpdator("");
            newMenuResult.setUpdateDateTime(LocalDateTime.now());
        }

        newMenuResult.setExpanded(newMenu.getExpanded());
        newMenuResult.setParent(newMenu.isParent());
        newMenuResult.setLeaf(newMenu.isLeaf());
        newMenuResult.setText(newMenu.getText());
        newMenuResult.setUrl(newMenu.getUrl());
        // TODO 处理序号排序
        newMenuResult.setSort(generateSort(newMenu.isParent(), newMenu.getParentId()));


        return ResponseMessage.success(newMenuRepository.save(newMenuResult));
    }

    /**
     * 排序
     *
     * @param isParent
     * @param id
     * @return
     */
    private Integer generateSort(boolean isParent, String id) {
        if (isParent) {
            NewMenu newMenu = newMenuRepository.findTopByIsParentOrderBySortDesc(true);
            return null == newMenu ? 1 : newMenu.getSort() + 1;
        } else {
            NewMenu newMenu = newMenuRepository.findTopByParentIdOrderBySortDesc(id);

            NewMenu parentNewMenu = newMenuRepository.findOne(id);

            return null == newMenu ? parentNewMenu.getSort() * 10 + 1 : newMenu.getSort() + 1;
        }
    }

    /**
     * 获取角色菜单列表
     *
     * @return
     */
    public ResponseMessage roleList() {
        List<MenuRoleModel> menuRoleModels = new ArrayList<>();
        List<NewMenu> parentMenus = newMenuRepository.findByIsParentAndLeafOrderBySortAsc(true, false);
        if (CollectionUtils.isNotEmpty(parentMenus)) {
            for (NewMenu parentMenu : parentMenus) {
                String id = parentMenu.getId();
                MenuRoleModel menuRoleModel = new MenuRoleModel();
                menuRoleModel.setLabel(parentMenu.getText());
                menuRoleModel.setKey(id);
                menuRoleModel.setValue(id);

                List<NewMenu> childrenNewMenus = newMenuRepository.findByParentId(id);
                if (CollectionUtils.isNotEmpty(childrenNewMenus)) {
                    List<MenuRoleModel> childMenuRoleModels = new ArrayList<>();
                    for (NewMenu childNewMenu : childrenNewMenus) {
                        MenuRoleModel childMenuRoleModel = new MenuRoleModel();
                        childMenuRoleModel.setLabel(childNewMenu.getText());
                        childMenuRoleModel.setKey(childNewMenu.getId());
                        childMenuRoleModel.setValue(childNewMenu.getId());
                        childMenuRoleModels.add(childMenuRoleModel);
                    }
                    menuRoleModel.setChildren(childMenuRoleModels);
                }
                menuRoleModels.add(menuRoleModel);
            }
        }

        return ResponseMessage.success(menuRoleModels);
    }

    public List<NewMenu> getMenusByList(String[] menuIds) {
        return newMenuRepository.findByIdIn(menuIds);
    }

    public List<NewMenu> getByParentId(String parentId) {
        return newMenuRepository.findByParentId(parentId);
    }
}
