package client.api.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单取消查询对象 Created by kimiyu on 15/9/1.
 */
@Data
public class CancelCondition implements Serializable {

    private Long orderId;
    private String telphone;
    private String platformId;
    private Integer page;
    private Integer size;
}
