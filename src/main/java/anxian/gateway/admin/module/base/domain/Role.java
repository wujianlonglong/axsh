package anxian.gateway.admin.module.base.domain;

import anxian.gateway.admin.module.common.domain.BaseObj;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by jiangzhe on 15-11-13.
 * 角色
 */
@Data
@Entity
@Table(name = "t_role")
@DynamicInsert
public class Role extends BaseObj {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 显示名称
     */
    private String displayref;

    /**
     * 描述
     */
    private String description;

}
