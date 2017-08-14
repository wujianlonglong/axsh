package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 积分查询
 * Created by kimiyu on 16/2/26.
 */
@Data
public class IntegalCondition implements Serializable {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private Integer status;
    private Integer page;
    private Integer size;
}
