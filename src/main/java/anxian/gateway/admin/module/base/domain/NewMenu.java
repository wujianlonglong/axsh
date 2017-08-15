package anxian.gateway.admin.module.base.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document(collection = "menu")
public class NewMenu implements Serializable {

    @Id
    private ObjectId id;

    /**
     * 菜单名称
     */
    private String text;

    /**
     * 是否打开，在菜单树中使用
     */
    private Boolean expanded;

    /**
     * 功能模块地址
     */
    private String url;

    /**
     * 父菜单
     */
    private ObjectId parentId;


    private Integer sort;
}
