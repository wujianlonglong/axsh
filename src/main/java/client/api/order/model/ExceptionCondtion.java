package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 异常订单查询对象
 * Created by kimiyu on 15/9/1.
 */
@Data
public class ExceptionCondtion implements Serializable {

    private Long orderId; // 订单ID
    private Date payStartDate;  // 支付开始时间
    private Date payEndDate;    // 支付结束时间
    private Integer page;
    private Integer size;

}
