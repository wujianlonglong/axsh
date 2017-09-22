package anxian.gateway.admin.module.common.domain;


import lombok.Data;

import java.io.Serializable;

/**
 * 数据表基类
 * Created by jiangzhe on 15-11-16.
 */

@Data
public abstract class BaseObj implements Serializable {

    private Long id;

}
