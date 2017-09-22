package anxian.gateway.admin.module.base.domain;

import anxian.gateway.admin.module.common.domain.BaseObj;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jiangzhe on 15-11-16.
 * 部门
 */
@Data
public class AclOrg extends BaseObj {

    /**
     * 部门名称
     */
    private String orgName;

    /**
     * 部门编号
     */
    private String orgNum;

    /**
     * 部门管理员
     */
    private AclUser manager;

    /**
     * 父部门
     */
    private AclOrg parentOrg;

    /**
     * 子部门
     */
    private List<AclOrg> children;

    /**
     * 描述
     */
    private String description;
}
