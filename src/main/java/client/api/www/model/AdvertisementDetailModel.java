package client.api.www.model;

import lombok.Data;

/**
 * Created by mac on 15/9/17.
 */
@Data
public class AdvertisementDetailModel extends Advertisement {


    private String imageUrl; // 图片路径

    private String hrefUrl; // 链接路径

}
