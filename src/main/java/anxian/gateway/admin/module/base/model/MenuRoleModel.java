package anxian.gateway.admin.module.base.model;

import anxian.gateway.admin.module.base.domain.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuRoleModel implements Serializable {

    private String label;

    private String value;

    private String key;

    private List<MenuRoleModel> children;
}
