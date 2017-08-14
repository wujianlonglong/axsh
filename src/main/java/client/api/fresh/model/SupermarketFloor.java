package client.api.fresh.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by mac on 15/9/23.
 */
@Data
public class SupermarketFloor implements Serializable {

    private Long id; // 主键

    private Long supermarketId; // 超市页id

    private String name; // 楼层名称

    private String moreHref; // 更多链接

    private String imgSrc; // 图片路径

    private String hrefUrl; // 链接路径

    private Integer status; // 状态

    private Integer orders; // 排序

    private String remarker; // 备注

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

}
