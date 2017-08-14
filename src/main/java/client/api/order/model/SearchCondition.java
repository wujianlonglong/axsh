package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 订单查询对象
 * Created by kimiyu on 15/8/31.
 */
@Data
public class SearchCondition implements Serializable {

    private Date payStartDate;  // 开始支付时间
    private Date payEndDate;    // 结束支付时间
    private Integer payType;        // 支付类型
    private Integer orderStatus;    // 订单状态
    private Integer logisticStatus; // 物流状态
    private Integer orderSoure;     // 下单来源
    private Long orderId;       // 订单号
    private Long parentId;      // 关联订单号
    private String telphone;    // 手机号
    private Long memberId;      // 会员ID
    private String marketCode;     // 所属商场
    private String consignee;   // 收货人
    private Integer page;
    private Integer size;
}
