package anxian.gateway.admin.module.base.domain;

import anxian.gateway.admin.module.common.domain.BaseObj;
import lombok.Data;

/**
 * Created by jiangzhe on 15-11-17.
 * 用户与角色
 */
@Data
public class UserRole extends BaseObj {

    private AclUser securityuser;

}
