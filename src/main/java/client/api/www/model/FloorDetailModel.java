package client.api.www.model;

import lombok.Data;

import java.util.List;

/**
 * Created by mac on 15/9/23.
 */
@Data
public class FloorDetailModel extends Floor {

    private List<FloorCategory> leftFloorCategorys; // 左侧分类

    private List<FloorKeyword> leftFloorKeyword; // 左侧关键词

    private List<FloorCategory> topFloorCategorys; // 顶层分类

    private List<FloorAdvertisement> rightFloorAdvertisements; // 右侧广告

    private List<FloorAdvertisement> bottomFloorAdvertisements; // 底部品牌广告

}
