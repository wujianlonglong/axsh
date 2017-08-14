package client.api.sale.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 满赠促销
 * Created by kimiyu on 15/9/7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionGiftViewModel extends PromotionViewModel implements Serializable {
    private Integer joinedItemType;     // 商品参与方式
    private String allocationModel;     // 配送方式

    private List<FullGift> fullGifts;//阶梯信息

    private List<JoinedItem> joinedItems;//参与商品列表
}
