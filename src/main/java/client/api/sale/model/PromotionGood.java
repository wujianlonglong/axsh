package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by byinbo on 2016/12/23.
 */
@Data
public class PromotionGood implements Serializable {

    /**
     * 商品编码
     */
    private String sn;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 会员价
     */
    private BigDecimal memberPrice;

    /**
     * 零售价
     */
    private BigDecimal price;


}
