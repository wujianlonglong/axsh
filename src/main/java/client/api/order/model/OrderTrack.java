package client.api.order.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by gaoqichao on 15-11-4.
 */
@Data
public class OrderTrack {
    private String id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 京东众包运单号
     */
    private String deliveryOrderNo;

    /**
     * 城市ｉｄ
     */
    private String cityId;

    /**
     * 物流状态
     */
    private int trackStatus;


    /**
     * 更新订单转台时间
     */
    private Date updateStatusTime;

    /**
     * 配送员姓名
     */
    private String deliveryManName;

    /**
     * 配送员手机号
     */
    private String deliveryManMobile;

    /**
     * 失败原因码
     */
    private String failReasonCode;

    /**
     * 失败原因描述
     */
    private String failReasonMsg;
}
