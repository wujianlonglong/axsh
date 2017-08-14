package client.api.fresh.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-23.
 */
@Data
public class SpecialAdvertisementModel extends SupermarketAdvertisement {

    /**
     * 特价商品列表
     */
    private List<SupermarketAdvertisement> specialAds;

}
