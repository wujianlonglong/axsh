package client.api.pay.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 支付查询 Created by kimiyu on 16/4/15.
 */
@Data
public class PayCondition implements Serializable {

    private int page = 1;

    private int size = 10;

    private int orderStatus;

    private Date startDateTime;

    private Date endDateTime;

    private Long orderId;

    private String shopId;

    private String payMethod;

    private Integer platForm;


}
