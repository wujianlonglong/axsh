package client.api.fresh.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 16-1-11.
 */
@Data
public class SupermarketDetailModel extends Supermarket {

    /**
     * 分类列表
     */
    private List<SupermarketCategoryModel> supermarketCategories;

    /**
     * 首焦
     */
    private List<SupermarketAdvertisement> headerAds;

    /**
     * 右焦
     */
    private List<SupermarketAdvertisement> rightAds;

    /**
     * 特价商品(中焦)
     */
    private SpecialAdvertisementModel specialAd;

    /**
     * 楼层
     */
    private List<SupermarketFloor> floors;
}
