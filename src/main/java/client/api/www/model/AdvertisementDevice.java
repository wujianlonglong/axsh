package client.api.www.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mac on 15/9/16.
 */
@Data
public class AdvertisementDevice implements Serializable {

    private Long id; // 主键

    private Long advertisementId; // 广告Id

    private Integer deviceId; // 设备名称

    private String deviceName; // 设备名称

    private String imageUrl; // 图片路径

    private String hrefUrl; // 链接路径

}
