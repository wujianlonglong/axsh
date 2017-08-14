package client.api.www.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by mac on 15/9/22.
 */
@Data
public class FloorCategory implements Serializable {

    private Long id; // 主键

    private String name; // 分类名称

    private Long floorId; // 楼层id

    private Integer type; // 分类类型

    private Integer orders; // 排序

    private String href; // 链接

    private String classes; // 样式

    private String remarker; // 描述

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

}
