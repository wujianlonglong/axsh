package client.api.order.model;

import lombok.Data;

import java.util.List;

/**
 * 订单取消条件 Created by kimiyu on 16/4/11.
 */
@Data
public class CancelOrderView {

    /**
     * 取消理由
     */
    private Integer cancelReason;
    /**
     * 取消理由内容
     */
    private String cancelReasonContent;
    /**
     * 备注
     */
    private String memo = "";
    /**
     * 取消人
     */
    private String userId;
    /**
     * 取消人姓名
     */
    private String customer;
    /**
     * 订单ID
     */
    private List<Long> orderIds;
}
