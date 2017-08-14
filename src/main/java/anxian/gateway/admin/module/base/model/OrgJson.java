package anxian.gateway.admin.module.base.model;

import lombok.Data;

/**
 * Created by jiangzhe on 15-11-16.
 */
@Data
public class OrgJson {

    private Long id;

    private String orgName;

    private String orgNum;

    private String manager;

    private Long parentOrgId;

    private String parentOrg;

    private String description;
}
