package anxian.gateway.admin.module.base.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
public class NewMenuModel implements Serializable {

    private String id;

    /**
     * 菜单名称
     */
    private String text;

    /**
     * 是否打开，在菜单树中使用
     */
    private Boolean expanded;

    public String getExpanded() {
        return expanded ? "是" : "否";
    }

    /**
     * 是否是父菜单
     */
    private boolean isParent;

    public String getIsParent() {
        return isParent ? "是" : "否";
    }

    /**
     * 是否是子菜单
     */
    private boolean leaf;

    public String getLeaf() {
        return leaf ? "是" : "否";
    }

    /**
     * 功能模块地址
     */
    private String url;

    /**
     * 父菜单
     */
    private String parentId;

    private String parentMenu;

    private Integer sort;

}
