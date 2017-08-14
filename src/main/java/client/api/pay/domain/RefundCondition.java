package client.api.pay.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 退款查询条件 Created by kimiyu on 16/4/15.
 */
@Data
public class RefundCondition implements Serializable {

    private int page;

    private int size;

    private Long orderId;

    private Date startDateTime;

    private Date endDateTime;

    private String refundStatus;

    private String shopId;

    private Integer platForm;
}
