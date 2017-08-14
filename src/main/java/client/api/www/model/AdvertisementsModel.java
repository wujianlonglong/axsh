package client.api.www.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-10.
 */
@Data
public class AdvertisementsModel {

    /**
     * 顶通
     */
    private List<AdvertisementDetailModel> topAdvertisementDetail;

    /**
     * 半通
     */
    private List<AdvertisementDetailModel> halfAdvertisementDetail;

    /**
     * 首焦
     */
    private List<AdvertisementDetailModel> headerAdvertisementDetail;

    /**
     * 中焦
     */
    private List<AdvertisementDetailModel> middleAdvertisementDetail;

    /**
     * 右焦
     */
    private List<AdvertisementDetailModel> rightAdvertisementDetail;

    /**
     * 2屏推荐
     */
    private List<AdvertisementDetailModel> recommendedAdvertisementDetail;

    /**
     * app促销
     */
    private List<AdvertisementDetailModel> appAdvertisementDetail;
}
