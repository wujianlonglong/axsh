package client.api.user.domain;

import lombok.Data;

/**
 * Created by gaoqichao on 16-4-12.
 */
@Data
public class FeedbackModel {
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
    private String mobile;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createdDate;

    /**
     * 处理时间
     */
    private String dealDate;

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
    private String isSolved;

    /**
     * 处理意见
     */
    private String dealSuggestion;
}
