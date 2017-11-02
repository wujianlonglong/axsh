package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.domain.NewRole;
import anxian.gateway.admin.module.base.model.NewMenuModel;
import anxian.gateway.admin.module.base.model.NewRoleModel;
import anxian.gateway.admin.module.base.repository.NewRoleRepository;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewRoleService {

    @Autowired
    private NewRoleRepository newRoleRepository;

    @Autowired
    private NewMenuService newMenuService;


    /**
     * 角色列表
     *
     * @param roleName 角色名称
     * @param pageable 分页
     * @return
     */
    public ResponseMessage list(String roleName, Pageable pageable) {

        Page<NewRole> newRolePage = null;
        if (StringUtils.isEmpty(roleName)) {
            newRolePage = newRoleRepository.findAll(pageable);
        } else {
            newRolePage = newRoleRepository.findByDisplayNameLike(roleName, pageable);
        }

        List<NewRole> newRoles = newRolePage.getContent();
        List<NewRoleModel> newRoleModels = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(newRoles)) {
            for (NewRole newRole : newRoles) {
                NewRoleModel newRoleModel = new NewRoleModel();
                newRoleModel.setId(newRole.getId());
                newRoleModel.setCode(newRole.getCode());
                newRoleModel.setDisplayName(newRole.getDisplayName());
                newRoleModel.setValid(newRole.getIsValid());
                String[] platforms = newRole.getPlatforms();
                if (platforms != null && platforms.length > 0) {
                    List<String> platformList = Arrays.asList(platforms);
                    if (CollectionUtils.isNotEmpty(platformList)) {
                        StringBuilder platformsb = new StringBuilder();
                        for (String platform : platforms) {
                            if ("10006".equalsIgnoreCase(platform)) {
                                platformsb.append("淘鲜达")
                                        .append(";");
                            } else if ("10005".equalsIgnoreCase(platform)) {
                                platformsb.append("安鲜生活")
                                        .append(";");
                            } else if ("10004".equalsIgnoreCase(platform)) {
                                platformsb.append("三江购物")
                                        .append(";");
                            }
                        }
                        newRoleModel.setPlatformName(platformsb.toString());
                    }
                }
                newRoleModels.add(newRoleModel);
            }
        }

        return ResponseMessage.success(new PageImpl<>(newRoleModels, pageable, newRolePage.getTotalElements()));
    }

    /**
     * 根据id查询角色详情
     *
     * @param id
     * @return
     */
    public ResponseMessage getById(String id) {

        if (StringUtils.isEmpty(id)) {
            return ResponseMessage.error("角色id为空！");
        }

        NewRole newRole = newRoleRepository.findOne(id);
        if (null == newRole) {
            return ResponseMessage.error("角色对象为空！");
        }

        NewRoleModel newRoleModel = new NewRoleModel();
        newRoleModel.setId(newRole.getId());
        newRoleModel.setCode(newRole.getCode());
        newRoleModel.setDisplayName(newRole.getDisplayName());
        newRoleModel.setValid(newRole.getIsValid());
        newRoleModel.setPlatforms(newRole.getPlatforms());
        List<NewMenu> newMenus = newRole.getNewMenus();
        newRoleModel.setMenuIds(newMenus.stream().map(NewMenu::getId).collect(Collectors.toList()).toArray(new String[]{}));
        newRoleModel.setDescription(newRole.getDescription());
        return ResponseMessage.success(newRoleModel);
    }


    public NewRole getOne(String id) {
        return newRoleRepository.findOne(id);
    }

    /**
     * 保存角色信息
     *
     * @param newRoleModel
     * @param username
     * @return
     */
    public ResponseMessage save(NewRoleModel newRoleModel, String username) {

        String id = newRoleModel.getId();
        NewRole newRole = null;
        if (!StringUtils.isEmpty(id)) {
            newRole = newRoleRepository.findOne(id);
            newRole.setUpdator(username);
            newRole.setUpdateDateTime(LocalDateTime.now());
        } else {
            newRole = new NewRole();
            newRole.setCreator(username);
            newRole.setCreateDateTime(LocalDateTime.now());
        }

        newRole.setCode(newRoleModel.getCode());
        newRole.setDisplayName(newRoleModel.getDisplayName());
        newRole.setDescription(newRole.getDescription());
        newRole.setPlatforms(newRoleModel.getPlatforms());
        newRole.setIsValid(newRoleModel.isValid());
        newRole.setNewMenus(getNewMenus(newRoleModel.getMenuIds()));
        newRoleRepository.save(newRole);

        return ResponseMessage.success("保存成功！");
    }

    public ResponseMessage listByUser() {

        List<NewRole> newRoles = newRoleRepository.findByIsValid(true);
        if (CollectionUtils.isEmpty(newRoles)) {
            return ResponseMessage.error("请先创建角色！");
        }

        return ResponseMessage.success(newRoles);
    }

    private List<NewMenu> getNewMenus(String[] menuIds) {
        List<NewMenu> returndMenus = new ArrayList<>();
        List<NewMenu> newMenus = newMenuService.getMenusByList(menuIds);
        List<NewMenu> parentMenus = newMenus.stream().filter(NewMenu::isParent).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(parentMenus)) {
            for (NewMenu parentMenu : parentMenus) {
                String parentId = parentMenu.getId();
                returndMenus.addAll(newMenuService.getByParentId(parentId));
            }
        }
        List<NewMenu> childMenus = newMenus.stream().filter(NewMenu::isLeaf).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(childMenus)) {
            for (NewMenu childMenu : childMenus) {
                String parentId = childMenu.getParentId();
                returndMenus.add(newMenuService.getOne(parentId));
            }
        }

        // 防止重复加入菜单
        for (NewMenu newMenu : newMenus) {
            String id = newMenu.getId();
            boolean notAdd = returndMenus.stream().anyMatch(newMenu1 -> newMenu1.getId().equalsIgnoreCase(id));
            if (!notAdd) {
                returndMenus.add(newMenu);
            }
        }

        return returndMenus;
    }
}
