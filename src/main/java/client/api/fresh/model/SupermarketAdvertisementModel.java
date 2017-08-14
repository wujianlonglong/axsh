package client.api.fresh.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-13.
 */
@Data
public class SupermarketAdvertisementModel {

    /**
     * 首焦
     */
    private List<SupermarketAdvertisement> headerAdvertisementDetail;

    /**
     * 中焦
     */
    private List<SupermarketAdvertisement> middleAdvertisementDetail;

    /**
     * 右焦
     */
    private List<SupermarketAdvertisement> rightAdvertisementDetail;

}
