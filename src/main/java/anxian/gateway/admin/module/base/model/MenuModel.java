package anxian.gateway.admin.module.base.model;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuModel implements Serializable {

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
     * 权限列表
     */
    private List<String> authorityList;

    /**
     * 子菜单
     */
    private List<MenuModel> children;


    private Integer sort;
}
