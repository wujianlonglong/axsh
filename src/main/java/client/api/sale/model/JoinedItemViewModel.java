package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kimiyu on 15/9/29.
 */
@Data
public class JoinedItemViewModel implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 卷类型
     */
    private Integer saleType;
    /**
     * 参与方式类型
     */
    private Integer participationMode;
    /**
     * 商品列表信息
     */
    private List<Long> productList;
}
