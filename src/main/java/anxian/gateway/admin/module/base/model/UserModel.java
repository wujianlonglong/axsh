package anxian.gateway.admin.module.base.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class UserModel implements Serializable {

    private String id;

    private String username;

    private String roleId;

    private String fullName;

    private Boolean isValid = true;

    private String password;

    private String mobile;

    private String shopId;
}
