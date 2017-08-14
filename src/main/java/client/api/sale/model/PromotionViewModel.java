package client.api.sale.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by kimiyu on 15/9/6.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionViewModel extends BaseViewModel implements Serializable {

    private Integer saleType;      // 促销类型
    private Integer promotionTotalNumber;   // 促销笔数上限
    private String itemDetonation;      // 商品爆炸贴

}
