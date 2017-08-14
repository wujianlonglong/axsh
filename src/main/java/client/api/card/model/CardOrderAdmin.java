package client.api.card.model;

import client.api.order.model.OrderStatus;
import client.api.pay.domain.PlatForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 会员卡订单列表
 * Created by kimiyu on 16/12/20.
 */
@Data
public class CardOrderAdmin implements Serializable {

    /**
     * 订单编号
     */
    private Long id;

    // 申请人会员id
    private Long userId;

    // 操作类型［续卡、申请］
    private String type;

    // 会员卡编号
    private String cardNum;

    // 支付平台
    private Integer platForm;

    // 支付时间
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime payDateTime;


    // 创建时间
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDateTime;

    // 手机号
    private String telephone;

    // 订单状态
    private Integer orderStatus;

    // 用户id
    private String creater;

    // 实际支付金额
    private BigDecimal actualAmount;

    public String getCreateDateView() {
        return createDateTime == null ? "" : createDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getPayDateView() {
        return payDateTime == null ? "" : payDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * excel导出列表中显示用, 支付平台
     *
     * @return
     */
    public String getPlatFormView() {
        String platFormView = "";
        if (platForm != null) {
            switch (platForm) {
                case PlatForm.AliPay:
                case PlatForm.AliPayDirect:
                    platFormView = "ALIPAY";
                    break;
                case PlatForm.webchatForApp:
                case PlatForm.webChatForSweepCode:
                    platFormView = "WECHAT";
                    break;
                default:
                    platFormView = "";
                    break;
            }
        }
        return platFormView;
    }

    /**
     * excel导出列表中显示用,订单状态
     */
    public String getOrderStatusView() {

        String orderStatusView = "";
        if (orderStatus != null) {
            switch (orderStatus) {
                case OrderStatus.hasCancel:
                    orderStatusView = "已取消";
                    break;
                case OrderStatus.treatPayment:
                    orderStatusView = "待支付";
                    break;
                case OrderStatus.hasPayed:
                    orderStatusView = "已支付";
                    break;
                case OrderStatus.hasComplete:
                    orderStatusView = "已完成";
                    break;
            }
        }
        return orderStatusView;
    }


}
