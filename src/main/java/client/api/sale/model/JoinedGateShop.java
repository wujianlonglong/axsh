package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参与门店对象
 * Created by kimiyu on 15/9/6.
 */
@Data
public class JoinedGateShop implements Serializable {

    private String id;
    /**
     * 用户ID
     */
    private Long userId = 0L;

    /**
     * 促销类型
     */
    private Integer saleType = 0;

    /**
     * 促销ID
     */
    private String saleId = "";

    /**
     * 赠品列表
     */
    private Map<String, JoinedGift> joinedGifts = new HashMap<>();

    /**
     * 门店编号
     */
    private String serialNumber = "";

    /**
     * 门店名称
     */
    private String shopName = "";

    /**
     * 县市区名称
     */
    private String areaName;

    /**
     * 门店状态【默认正常】
     */
    private Boolean status = SaleConstant.NORMAL;


    private List<GateShopGift> gateShopGifts;

}
