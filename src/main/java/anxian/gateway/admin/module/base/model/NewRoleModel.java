package anxian.gateway.admin.module.base.model;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import anxian.gateway.admin.module.base.domain.NewMenu;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class NewRoleModel implements Serializable {

    private String id;

    /**
     * 角色名称
     */
    private String code;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 平台名称
     */
    private String platformName;

    private String[] platforms;


    private String[] menuIds;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否有效
     */
    private boolean isValid;

    private String validStr;

    public String getValidStr() {
        return isValid ? "有效" : "无效";
    }
}
