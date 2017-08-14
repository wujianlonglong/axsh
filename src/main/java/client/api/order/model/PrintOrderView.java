package client.api.order.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 小票打印对象 Created by kimiyu on 16/1/15.
 */
@Data
@ToString
public class PrintOrderView implements Serializable {

    /**
     * 今天第几单[当日19点到次日19点,从1开始]
     */
    private Integer printIndex;
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 赠品名称
     */
    private String saleName;

    /**
     * 预计送达时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime transportTime;
    /**
     * 下单时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDate;
    /**
     * 订单备注
     */
    private String comment;
    /**
     * 配送方式
     */
    private String allocationMode = "";
    /**
     * 所属门店
     */
    private String shopName;
    /**
     * 商品金额
     */
    private BigDecimal retailAmount;
    /**
     * 优惠金额
     */
    private BigDecimal benefitAmount;
    /**
     * 运费金额
     */
    private BigDecimal transportAmount;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    /**
     * 商品数量
     */
    private Integer totalNumber;
    /**
     * 应收金额
     */
    private BigDecimal actualAmount;
    /**
     * 订单总积分
     */
    private Long scoreAmount;
    /**
     * 配送人员
     */
    private String allocationName;
    /**
     * 配送电话
     */
    private String allocationTelephone;
    /**
     * 打印商品列表
     */
    private List<PrintOrderItem> printOrderItems;

    /**
     * 条形码
     */
    private String barcode;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 现金券总金额
     */
    private BigDecimal cashAmount;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 送货地址
     */
    private String sendAddress;
}
