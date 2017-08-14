package client.api.user.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by gaoqichao on 15-12-1.
 */
@Data
public class Feedback implements Serializable {
    private Long id;

    /**
     * 用户id;
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 联系方式
     */
    private String contactInfo;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate = LocalDateTime.now();

    /**
     * 处理时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dealDate;

    /**
     * 处理人员id
     */
    private Long operatorId;

    /**
     * 处理人员名称
     */
    private String operatorName;

    /**
     * 是否已经解决
     */
    private boolean isSolved = false;

    /**
     * 处理意见
     */
    private String dealSuggestion;
}
