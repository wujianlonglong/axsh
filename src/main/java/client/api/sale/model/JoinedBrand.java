package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 参与品牌
 * Created by kimiyu on 15/10/12.
 */
@Data
public class JoinedBrand implements Serializable {

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
     * 参与品牌ID
     */
    private Long brandId;

}
