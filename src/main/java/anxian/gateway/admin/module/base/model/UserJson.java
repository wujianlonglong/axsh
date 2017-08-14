package anxian.gateway.admin.module.base.model;

import lombok.Data;

/**
 * Created by jiangzhe on 15-11-16.
 */
@Data
public class UserJson {

    private Long id;

    private String username;

    private String password;

    private Integer accountEnabled;

    private Integer accountLocked;

    private String expiredDate;

    private Integer credentialsExpired;

    private String fullName;

    private Long orgId;

    private String org;

    private String mobile;

    private Integer userMgrType;

    private String description;
}
