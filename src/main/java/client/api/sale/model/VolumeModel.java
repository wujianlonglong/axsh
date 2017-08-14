package client.api.sale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 优惠劵
 * Created by kimiyu on 15/10/22.
 */
@Data
public class VolumeModel implements Serializable {

    private Long id;

    /**
     * 促销ID
     */
    private String saleId;

    /**
     * 促销名称
     */
    private String saleName;

    /**
     * 卷类型
     */
    private Integer saleType;

    /**
     * 促销卷码
     */
    private String volumeNumber;

    /**
     * 参与方式
     */
    private Integer particationModel;

    /**
     * 现金卷
     */
    private Double cash = 0.00;

    /**
     * 满减符合金额
     */
    private Double orderMoney = 0.00;

    /**
     * 领用人的ID
     */
    private Long userId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 优惠劵层级
     */
    private Integer volumeLevel;

    /**
     * 卷领用状态
     */
    private boolean getState = false;

    /**
     * 卷使用状态
     */
    private Integer usedStatus;

    /**
     * 领卷时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime receiveDate;

    /**
     * 用卷时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime usedDate;

    /**
     * 是否选中
     */
    private Integer isSelected;

    /**
     * 使用说明
     */
    private String useMemo;
}
