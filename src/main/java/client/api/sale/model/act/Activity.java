package client.api.sale.model.act;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by gaoqichao on 15-9-17.
 */
@Data
public class Activity implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 专区id
     */
    private String zoneId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 开始时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate beginDate;

    /**
     * 结束时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    /**
     * 促销id列表
     */
    private List<String> promotions;

    /**
     * 活动说明
     */
    private String description;

    /**
     * 0-为开始；1-已开始；-1-已结束
     */
    private Integer flag = 0;

    /**
     * pc模板
     */
    private ActTemplete pcTemplete;

    /**
     * pc模板类型
     */
    private Integer pcTempleteType;

    /**
     * APP模板
     */
    private ActTemplete appTemplete;

    /**
     * app模板类型
     */
    private Integer appTempleteType;

    /**
     * 微商城模板
     */
    private ActTemplete wechatTemplete;

    /**
     * 微商城模板类型
     */
    private Integer wechatTempleteType;

    /**
     * 平板模板
     */
    private ActTemplete tabletTemplete;

    /**
     * 平板模板类型
     */
    private Integer tabletTempleteType;
    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;

    /**
     * 修改时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastModifiedDate;
}
