package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.AclUser;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

/**
 * Created by jiangzhe on 15-11-16.
 */
public interface AclUserService {

    /**
     * 保存用户
     */
    @PreAuthorize("hasAuthority('USER_SAVE')")
    AclUser save(AclUser aclUser);

    /**
     * 根据ID删除用户
     */
    @PreAuthorize("hasAuthority('USER_DELETE')")
    void del(Long id);

    /**
     * 分页列表
     */
    @PreAuthorize("hasAuthority('USER_LIST')")
    Page<AclUser> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter);

    void updatePwd(String password, Long id);

    /**
     * 根据用户名返回用户
     */
    AclUser findByName(String username);

    /**
     * 返回该部门下包括和不包括在此角色下的用户
     */
    Map userInOrNotInOrg(Long roleId, Long orgId);
}
