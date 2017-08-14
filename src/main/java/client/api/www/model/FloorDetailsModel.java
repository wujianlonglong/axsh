package client.api.www.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-11.
 */
@Data
public class FloorDetailsModel extends Floor {


    private List<FloorCategory> leftFloorCategorys; // 左侧分类

    private List<FloorKeyword> leftFloorKeyword; // 左侧关键词

    private List<FloorCategoryModel> topFloorCategoryModels; // 顶层分类

    private List<FloorAdvertisement> rightFloorAdvertisements; // 右侧广告

    private List<FloorAdvertisement> bottomFloorAdvertisements; // 底部品牌广告

}
