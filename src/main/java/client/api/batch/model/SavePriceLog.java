package client.api.batch.model;

import lombok.Data;

/**
 * Created by qinhailong on 16-6-20.
 */
@Data
public class SavePriceLog {

    private Long ErpGoodsId; // 商品管理码

    private String shopId; // 门店Id

}
