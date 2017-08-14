package anxian.gateway.admin.module.base.domain;

import anxian.gateway.admin.module.common.domain.BaseObj;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * Created by jiangzhe on 15-11-17.
 * 用户与角色
 */
@Data
@Entity
@Table(name = "t_user_role")
@DynamicInsert
public class UserRole extends BaseObj {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "securityuser")
    private AclUser securityuser;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role")
    private Role role;
}
