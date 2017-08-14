package anxian.gateway.admin.module.common.domain;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 数据表基类
 * Created by jiangzhe on 15-11-16.
 */

@Data
@MappedSuperclass
public abstract class BaseObj implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

}
