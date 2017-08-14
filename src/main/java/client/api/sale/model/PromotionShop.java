package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by byinbo on 2016/12/23.
 */
@Data
public class PromotionShop implements Serializable {

    private String shopId;

    private String fgs;             // 县市区

    private String serialNumber; // 门店编号

    private String name;         // 门店名称
}
