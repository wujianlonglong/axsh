package client.api.customerComplain.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by byinbo on 2017/3/24.
 */
@Data
public class ShopSearch implements Serializable {
    /**
     * 门店编码
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 区总名称
     */
    private String areaOwner;

    /**
     * 当前页数
     */
    private Integer page;
    /**
     * 每页显示几号
     */
    private Integer size;
}
