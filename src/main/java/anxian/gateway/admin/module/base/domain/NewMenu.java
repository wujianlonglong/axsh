package anxian.gateway.admin.module.base.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "menu")
public class NewMenu implements Serializable {

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
     * 是否是父菜单
     */
    private boolean isParent;

    /**
     * 是否是子菜单
     */
    private boolean leaf;

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
