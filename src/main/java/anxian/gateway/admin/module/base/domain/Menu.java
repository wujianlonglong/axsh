package anxian.gateway.admin.module.base.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiangzhe on 15-11-18.
 * 菜单
 */
@Data
public class Menu implements Serializable {

    private Long id;

    /**
     * 菜单名称
     */
    private String text;

    /**
     * 是否打开，在菜单树中使用
     */
    private boolean expanded;

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
    private Menu parentMenu;

    /**
     * 子菜单
     */
    private List<Menu> children;


    private Integer sort;

}
