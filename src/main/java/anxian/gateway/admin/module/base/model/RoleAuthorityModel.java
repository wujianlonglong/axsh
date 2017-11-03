package anxian.gateway.admin.module.base.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleAuthorityModel implements Serializable {

    private String label;

    private String value;
}
