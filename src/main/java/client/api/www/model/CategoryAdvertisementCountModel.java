package client.api.www.model;

import lombok.Data;

/**
 * Created by mac on 15/9/17.
 */
@Data
public class CategoryAdvertisementCountModel {

    private Long categoryId; // 分类Id

    private String categoryName;//分类名称

    private Long brandCount; // 品牌广告个数

    private Long bigPictureCount; // 大图广告个数
}
