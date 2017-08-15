package anxian.gateway.admin.module.base.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document(collection = "authority")
public class NewAuthority implements Serializable {

    @Id
    private ObjectId id;

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
    private Boolean enabled;
}
