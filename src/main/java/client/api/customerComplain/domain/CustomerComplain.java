package client.api.customerComplain.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by byinbo on 2017/3/16.
 */
@Data
public class CustomerComplain implements Serializable {

    /**
     * 投诉编号
     */
    private Long id;

    /**
     * 受理部门
     */
    private String receiveDept;

    /**
     * 受理部门编号
     */
    private String receiveDeptNum;

    /**
     * 购买平台
     */
    private String platform;

    /**
     * 购买门店
     */
    private String gateShop;

    /**
     * 客户电话
     */
    private String mobile;

    /**
     * 投诉类型
     */
    private String complainType;

    /**
     * 投诉时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime complainTime;

    /**
     * 受理时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime acceptTime;

    /**
     * 处理时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime handleTime;

    /**
     * 处理完成时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime finishTime;

    /**
     * 是否超时
     * 1是，2否'
     */
    private int overTime;

    /**
     * 投诉内容
     */
    private String complainContent;

    /**
     * 投诉状态
     * 2-已完成；3-已关闭；1-待处理'
     */
    private int complainStat;

    /**
     * 定责状态
     * 1-已定责；2-未定责'
     */
    private int responStat;

    /**
     * 定责内容
     */
    private String responStrs;

    /**
     * 照片集
     */
    private String imagePaths;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否转单 1:不转单 2:转单
     */
    private int turn;

    /**
     * 是否会员 1:会员 2：非会员
     */
    private int member;

    /**
     * 投诉客户端类型：10  Android  20 ios   30 网站   40  小程序   50 微商城
     */
    private int clientType;

}
