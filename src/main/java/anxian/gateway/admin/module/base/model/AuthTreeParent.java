package anxian.gateway.admin.module.base.model;

import lombok.Data;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-19.
 */
@Data
public class AuthTreeParent {
    private Long id;

    private String text;

    private boolean expanded;

    private boolean isParent;

    private boolean checked;

    private List<AuthTreeChild> children;
}
