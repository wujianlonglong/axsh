package client.api.sale.model;

import client.api.item.domain.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangyunan on 2017/5/15.
 */
@Data
public class ScoreItem extends BaseModel {
    // 管理编码
    private Long erpGoodsId;

    // 会员金额
    private BigDecimal memberPrice;

    // 所需积分
    private Long score;

    // 活动商品
    private List<Product> productList;
}
