package anxian.gateway.admin.module.base.model;

import lombok.Data;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-20.
 */
@Data
public class MenuJson {
    private Long id;

    private String text;

    private boolean isParent;

    private boolean expanded;

    private boolean leaf;

    private Long parentMenuId;

    private String parentMenu;

    private String url;

    private List<MenuJson> children;
}
