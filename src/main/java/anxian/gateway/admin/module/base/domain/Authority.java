package anxian.gateway.admin.module.base.domain;

import anxian.gateway.admin.module.common.domain.BaseObj;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by jiangzhe on 15-11-17.
 * 权限资源
 */
@Data
public class Authority extends BaseObj implements GrantedAuthority {

    /**
     * 权限名称
     */
    private String authorityname;

    /**
     * 权限种类 1:列表 2:保存  3:删除
     */
    private Long authoritytype;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示名称
     */
    private String displayref;

    /**
     * 所属菜单
     */
    @JsonIgnore
    private Menu menu;

    @Override
    public String getAuthority() {
        return this.authorityname;
    }

    public Long getMenuId() {
        return menu.getId();
    }

    public String getMenuName() {
        return menu.getText();
    }
}
