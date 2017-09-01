package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 保存参与活动分类对象
 * Created by kimiyu on 15/9/29.
 */
@Data
public class JoinedCategory implements Serializable {
    private String id;
    /**
     * 卷类型
     */
    private Integer saleType;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 参与活动的分类ID
     */
    private Long categoryId;
    /**
     * 参与活动的分类层级
     */
    private Integer grade;
}
