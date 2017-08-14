package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-13.
 */
public interface RoleService {

    /**
     * 保存角色
     */
    @PreAuthorize("hasAuthority('ROLE_SAVE')")
    Role save(Role role);

    /**
     * 根据ID返回角色
     */
    Role findById(Long id);

    /**
     * 根据ID删除角色
     */
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    void del(Long id);

    /**
     * 返回所有角色
     */
    List<Role> findAll();

    /**
     * 修改角色
     */
    @PreAuthorize("hasAuthority('ROLE_SAVE')")
    void update(Long id, String name, String displayref, String description);

    /**
     * 分页列表
     */
    @PreAuthorize("hasAuthority('ROLE_LIST')")
    Page<Role> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter);
}
