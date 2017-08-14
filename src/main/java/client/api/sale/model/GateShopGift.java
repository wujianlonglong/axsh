package client.api.sale.model;

import lombok.Data;

/**
 * Created by Jianghe on 16/1/16.
 */
@Data
public class GateShopGift {
    /**
     * 赠品名称
     */
    private String giftName;
    /**
     * 每单赠送数量
     */
    private Integer sendNumber;
}
