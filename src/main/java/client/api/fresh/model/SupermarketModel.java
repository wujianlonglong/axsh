package client.api.fresh.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-13.
 */
@Data
public class SupermarketModel extends Supermarket {

    /**
     * 分类列表
     */
    private List<SupermarketCategory> supermarketCategories;

    /**
     * 首焦
     */
    private List<SupermarketAdvertisement> headerAds;

    /**
     * 右焦
     */
    private List<SupermarketAdvertisement> rightAds;

    /**
     * 特价商品
     */
    private SpecialAdvertisementModel specialAd;

    /**
     * 楼层
     */
    private List<SupermarketFloorModel> floors;


}
