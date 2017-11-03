package anxian.gateway.admin.module.base.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class NewAuthorityModel implements Serializable {

    @Id
    private String id;

    /**
     * 权限种类 1:列表 2:保存  3:删除
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用
     */
    private Boolean isValid;


    private String validStr;

    public String getValidStr() {
        return null == isValid || !isValid ? "无效" : "有效";
    }
}
