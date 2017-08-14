package anxian.gateway.admin.module.base.model;

import lombok.Data;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-18.
 */
@Data
public class LeftTreeParent {

    private Long id;

    private String text;

    private boolean expanded;

    private boolean isParent;

    private List<LeftTreeChild> children;

    private Integer sort;

}
