package anxian.gateway.admin.module.base.model;

import lombok.Data;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-19.
 */
@Data
public class AuthTreeChild {
    private String id;

    private String text;

    private boolean leaf;

    private boolean checked;

    private List<AuthTreeChild> children;
}
