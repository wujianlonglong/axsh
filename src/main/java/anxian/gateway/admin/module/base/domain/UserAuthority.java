package anxian.gateway.admin.module.base.domain;

import anxian.gateway.admin.module.common.domain.BaseObj;
import lombok.Data;

/**
 * Created by jiangzhe on 15-11-17.
 * 用户与权限资源
 */
@Data
public class UserAuthority extends BaseObj {
    private AclUser securityuser;
    private Authority authority;
}
