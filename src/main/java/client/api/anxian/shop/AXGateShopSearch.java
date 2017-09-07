package client.api.anxian.shop;

import lombok.Data;

@Data
public class AXGateShopSearch {

    private String shopName;

    private String shopId;

    private Integer state;

    private Integer page = 0;

    private Integer size = 10;

}
