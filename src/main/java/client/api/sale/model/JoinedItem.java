package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 参与活动的商品列表
 * Created by kimiyu on 15/9/29.
 */
@Data
public class JoinedItem implements Serializable {

    private String id;
    /**
     * 用户名
     */
    private Long userId;
    /**
     * 参与方式类型
     */
    private Integer participationMode;
    /**
     * 卷类型
     */
    private Integer saleType;
    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品Sn编码
     */
    private String sn;

    /**
     * 是否为特例商品[默认否]
     */
    private Boolean specProduct = Boolean.FALSE;

    /**
     * 商品名称
     */
    private String productName;


}
