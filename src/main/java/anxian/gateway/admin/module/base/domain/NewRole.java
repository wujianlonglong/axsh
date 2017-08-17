package anxian.gateway.admin.module.base.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "role")
public class NewRole implements Serializable {

    @Id
    private ObjectId id;

    /**
     * 角色名称
     */
    private String code;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 描述
     */
    private String description;

    @DBRef
    private List<NewAuthority> newAuthorities;

    @DBRef
    private List<NewMenu> newMenus;
}
