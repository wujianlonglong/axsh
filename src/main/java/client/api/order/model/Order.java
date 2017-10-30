package client.api.order.model;

import anxian.gateway.admin.module.business.controller.order.model.CancelReason;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by kimiyu on 15/7/20.
 */
@Data
public class Order implements Serializable {

    private Long id;

    private Long parentId; // 父订单号

    private String memberId; // 会员ID

    private Integer memberLevel;    //会员等级:1 惠用户;2 会员;

    private Integer orderType = 1; // 订单类型:1 正常下单; 2 秒杀下单;

    private Integer exceptionOrder = 0; // 异常订单: 1 异常订单;0 正常订单;

    private String telphone; // 手机号

    private String shopId;  // 门店编号

    private String shopName; // 门店名称

    private String areaId;  // 区域ID

    private String areaName; // 区域名称

    private String pickShopId; // 自提商场

    private String pickAddress; // 自提商场地址

    private String sendAddress; // 送货地址

    private Integer orderSource; //订单来源

    private BigDecimal retailAmount; // 商品总金额

    private BigDecimal benefitAmount; // 优惠总金额

    private BigDecimal cashAmount;  // 现金券总金额

    private BigDecimal sellAmount;  // 零售总金额

    private BigDecimal memberAmount;    // 会员总金额

    private BigDecimal memberReduceAmount;  // 会员减免金额

    private Integer platForm;     // 支付平台

    private BigDecimal transportAmount; // 运费总金额

    private BigDecimal payAmount; // 支付金额

    private BigDecimal actualAmount;    // 实际付款金额

    private Long scoreAmount;

    private Integer quantity; // 商品数量

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime payDate; // 支付时间

    /**
     * 电商银交易号
     */
    private String batchNo;
    /**
     * 支付宝交易号
     */
    private String tradeNo;

    private BigDecimal totalWeight; // 订单总重量

    private Integer orderStatus; // 订单状态

    private String logisticStatus; //物流状态

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime logisticDate; // 物流状态修改时间

    private Integer payType; // 支付类型

    private BigDecimal rejectAmount; // 舍弃金额

    private Integer modified; // 是否修改:[0 不需要修改;1 待修改;2 已修改]

    private Long modifier;  // 修改人

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime modifiDate;  // 修改日期

    private Integer updateType; // 修改类型

    private String updateReason;  // 修改原因

    private String dealer; // 订单处理人

    private String dealDate; // 订单处理日期

    private Integer unusualReasonType; // 异常原因类型,默认为0[无异常]

    private String unusualReason;   // 异常原因

    private Integer verificationReason; // 待审核原因类型

    private Integer verifiStatus; //审核状态

    private Integer verifiResult;   // 审核结果:0 不通过;1 通过

    private Integer cancelReason; //取消理由选项

    private String note; // 备注

    /**
     * 大客户名称
     */
    private String vipCustomer;

    private Long creatBy; // 订单创建人IDC

    private String creater; // 订单创建人

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDate; // 订单创建时间

    private Long verifier; // 审核人

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime verifiDate; // 审核时间

    private Long spliter; // 拆单人

    private String spliterName;    // 拆单人姓名

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime splitDate; // 拆单时间

    /**
     * 收货方式
     */
    private Integer consigneeType;

    private String consignee; // 收货人

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime cancelOrderDate; // 取消订单时间


    /**
     * 微信订单号
     */
    private String transactionId = "";
    /**
     * 微信退款单号
     */
    private String refundId = "";

    /**
     * 过机款机号
     */
    private String machineNumber;

    /**
     * 过机流水号
     */
    private Long lineNumber;

    /**
     * 过机操作人
     */
    private String operator;

    /**
     * 过机操作时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime operatDate;

    /**
     * 退货款机号
     */
    private String refundMachineNumber;
    /**
     * 退货流水号
     */
    private Long refundLineNumber;
    /**
     * 退货操作人
     */
    private String refundOperator;
    /**
     * 退货操作时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime refundOperatDate;

    /**
     * 门店取货时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime takeDate;

    /**
     * 订单完成时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime completeDate;

    /**
     * 商品出库时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime leaveDate;

    /**
     * 门店收货时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime receiveDate;

    /**
     * 退货成功时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime returnCompleteDate;

    /**
     * 支付取消时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime payCancelDate;

    /**
     * 拣货员ID
     */
    private Long pickingPerson;
    /**
     * 拣货员姓名
     */
    private String pickingPersonName;

    /**
     * 开始拣货时间[打印时间]
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startPickingDate;

    /**
     * 打印编号
     */
    private Integer printNumber;

    /**
     * 客服姓名
     */
    private String customer;
    /**
     * 客服ID.
     */
    private Long customerId;

    /**
     * 客服修改时间.
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    /**
     * 京东众包退款金额
     */
    private BigDecimal jdRefundAmount;  // 京东众包退款

    /**
     * 京东众包退款时间.
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime jdRefundTime;

    /**
     * excel导出列表中显示用,订单来源
     */
    public String getOrderSourceView() {
        if (orderSource == OrderSource.WEBSITE) {
            return OrderSource.WEBSITE_STR;
        } else if (orderSource == OrderSource.WEB_CHAT) {
            return OrderSource.WEB_CHAT_STR;
        } else if (orderSource == OrderSource.APP) {
            return OrderSource.APP_STR;
        } else if (orderSource == OrderSource.ANDROID) {
            return OrderSource.ANDROID_STR;
        } else if (orderSource == OrderSource.IOS) {
            return OrderSource.IOS_STR;
        }
        return "";
    }

    /**
     * excel导出列表中显示用,支付方式
     */
    public String getPayTypeView() {
        if (payType == PayType.deliveryPayment) {
            return PayType.deliveryPaymentStr;
        } else if (payType == PayType.onlinePayment) {
            return PayType.onlinePaymentStr;
        } else if (payType == PayType.yongyaoPayment) {
            return PayType.yongyaoPaymentStr;
        }

        return "";
    }

    /**
     * 收货方式
     *
     * @return
     */
    public String getCongineeTypeView() {
        if (consigneeType == OrderConstant.sendForSJ) {
            if (orderType == 3 || orderType == 4) {
                return OrderConstant.sendForAnXianName;
            } else {
                return OrderConstant.sendForSJName;
            }
        } else if (consigneeType == OrderConstant.shopPicking) {
            return OrderConstant.shopPickingName;
        } else if (consigneeType == OrderConstant.SEND_FOR_VIPCUST) {
            return OrderConstant.sendVipCust;
        } else if (consigneeType == OrderConstant.QUICK_MEAL) {
            return OrderConstant.quickMealName;
        }
        return "";
    }

    /**
     * excel导出列表中显示用,订单状态
     */
    public String getOrderStatusView() {

        String orderStatusView = "";
        if (orderStatus != null) {
            switch (orderStatus) {
                case OrderStatus.allocateFail:
                    orderStatusView = "已取消[网站前台,web]";
                    break;
                case OrderStatus.customHasCancel:
                    orderStatusView = "已取消[客服取消,admin]";
                    break;
                case OrderStatus.submitCancel:
                    orderStatusView = "提交订单取消成功";
                    break;
                case OrderStatus.paidCancel:
                    orderStatusView = "已付款取消成功";
                    break;
                case OrderStatus.verificateCancel:
                    orderStatusView = "审核取消";
                    break;
                case OrderStatus.completeGoodsCancel:
                    orderStatusView = "拣货完成取消";
                    break;
                case OrderStatus.cancelByResolute:
                    orderStatusView = "拆分订单取消";
                    break;
                case OrderStatus.systemHasCancel:
                    orderStatusView = "已取消[系统自动取消]";
                    break;
                case OrderStatus.refundMoney:
                    orderStatusView = "已退款[admin]";
                    break;
                case OrderStatus.inReturn:
                    orderStatusView = "退货中";
                    break;
                case OrderStatus.hasCancel:
                    orderStatusView = "配送失败[admin]";
                    break;
                case OrderStatus.treatPayment:
                    orderStatusView = "待支付";
                    break;
                case OrderStatus.hasPayed:
                    orderStatusView = "已支付";
                    break;
                case OrderStatus.treatVerificate:
                    orderStatusView = "待审核";
                    break;
                case OrderStatus.treatSortGoods:
                    orderStatusView = "待拣货";
                    break;
                case OrderStatus.sortGoods:
                    orderStatusView = "拣货中";
                    break;
                case OrderStatus.completeGoods:
                    orderStatusView = "拣货完成,等待配送";
                    break;
                case OrderStatus.treatPick:
                    orderStatusView = "待自提";
                    break;
                case OrderStatus.undelivered:
                    orderStatusView = "待发货";
                    break;
                case OrderStatus.hasDeliverGoods:
                    orderStatusView = "待收货";
                    break;
                case OrderStatus.inAllocation:
                    orderStatusView = "配送中";
                    break;
                case OrderStatus.allocateSuccess:
                    orderStatusView = "配送成功";
                    break;
//            case OrderStatus.hasDeliverGoods:
//                orderStatusView = "待收货";
//                break;
                case OrderStatus.hasComplete:
                    orderStatusView = "已完成";
                    break;
                case OrderStatus.returnGoods:
                    orderStatusView = "全部退货完成";
                    break;
                case OrderStatus.returnGoodsForPart:
                    orderStatusView = "部分退货完成";
                    break;
            }
        }
        return orderStatusView;
    }

    /**
     * excel导出列表中显示用,审核结果
     */
    public String getVerifiResultView() {
        if (verifiResult != null) {
            if (verifiResult == 2) {
                return "不通过";
            } else {
                return "通过";
            }

        }
        return "";
    }

    /**
     * excel导出列表中显示用,取消理由
     */
    public String getCancelReasonContentView() {
        if (cancelReason != null) {
            if (cancelReason == CancelReason.amountIsTooLarge.getValue()) {
                return CancelReason.amountIsTooLarge.getText();
            } else if (cancelReason == CancelReason.tooMuchWeight.getValue()) {
                return CancelReason.tooMuchWeight.getText();
            }
        }

        return "";
    }


}
