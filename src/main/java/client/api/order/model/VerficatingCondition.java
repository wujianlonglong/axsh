package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 待审核订单查询条件
 * Created by kimiyu on 15/9/1.
 */
@Data
public class VerficatingCondition implements Serializable {

    private Integer payType;        // 支付类型
    private Long orderId;           // 订单ID
    private Date payStartDate;      // 支付开始时间
    private Date payEndDate;        // 支付结束时间
    private String payStartStr;
    private String payEndStr;
    private String telphone;        // 手机号
    private Long memberId;          // 会员ID
    private Integer verficationType;// 待审核原因
    private Integer page;
    private Integer size;
}
