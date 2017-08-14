package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券列表对象
 * Created by kimiyu on 16/1/5.
 */
@Data
public class BenefitListView implements Serializable {
    /**
     * 优惠券ID
     */
    private String id;
    /**
     * 优惠券名称
     */
    private String saleName;
    /**
     * 优惠券类型
     */
    private Integer saleType;

    /**
     * 发券量
     */
    private Integer sendNumber;
    /**
     * 领券量
     */
    private Integer receiveNumber;

    /**
     * 用券量
     */
    private Integer useNumber;
    /**
     * 领券方式
     */
    private Integer receiveType;
    /**
     * 活动状态
     */
    private Integer status;
    /**
     * 有效期
     */
    private String validity;
}
