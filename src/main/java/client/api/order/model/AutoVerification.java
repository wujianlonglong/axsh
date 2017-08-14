package client.api.order.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by kimiyu on 15/7/17.
 */
@Data
public class AutoVerification implements Serializable {

    private String id;

    @NotNull
    private int rule; // 审核原则

    private Boolean isLargeCustomer; // 大客户是否需要审核

    @Digits(integer = 5, fraction = 0)
    @Range(min = 1, max = 99999)
    private int itemNum; // 单个订单同一商品数量

    @Digits(integer = 5, fraction = 0)
    @Range(min = 1, max = 99999)
    private int itemTotalNum; //单个订单商品总数量

    @Digits(integer = 5, fraction = 0)
    @Range(min = 1, max = 99999)
    private Double itemTotalAmount; // 单个订单总金额

    @Digits(integer = 5, fraction = 0)
    @Range(min = 1, max = 99999)
    private Double orderTotalAmount; // 用户当天购物订单总金额

    @Digits(integer = 5, fraction = 0)
    @Range(min = 1, max = 99999)
    private int orderTotalNum; // 用户当天购买订单总数量

    private Long createdBy;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;

    private Long updatedBy;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedDate;

}
