package anxian.gateway.admin.module.base.domain;

import anxian.gateway.admin.module.common.domain.BaseObj;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jiangzhe on 15-11-16.
 * 用户
 */
@Data
public class AclUser extends BaseObj {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐户是否过期
     */
    private Integer accountEnabled = 1;

    /**
     * 帐户是否锁住
     */
    private Integer accountLocked = 0;

    /**
     * 过期时间
     */
    private Date expiredDate;

    /**
     * 凭证是否过期
     */
    private Integer credentialsExpired = 0;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 所属部门
     */
    private AclOrg org;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户类型
     */
    private Integer userMgrType;

    /**
     * 描述
     */
    private String description;


}
