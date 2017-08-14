package anxian.gateway.admin.module.base.service;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

/**
 * Created by jiangzhe on 15-11-20.
 */
public interface RoleAuthorityService {
    /**
     * 关联角色与权限
     */
    @PreAuthorize("hasAuthority('ROLE_CONFIGURE')")
    Map relation(boolean checked, Long roleId, Long authorityId);
}
