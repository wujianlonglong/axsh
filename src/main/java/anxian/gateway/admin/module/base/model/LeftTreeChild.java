package anxian.gateway.admin.module.base.model;

import lombok.Data;

/**
 * Created by jiangzhe on 15-11-18.
 */
@Data
public class LeftTreeChild {

    private Long id;

    private String text;

    private boolean leaf;

    private String url;

    private Integer sort;

}
