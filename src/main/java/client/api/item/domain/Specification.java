package client.api.item.domain;


import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by qinhailong on 15-8-22.
 */
@Data
public class Specification implements Serializable {

    private Long id; // 主键

    private String name; // 规格名称

    private Integer orders; // 排序

    private Long goodsId; // 主商品Id

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

    private boolean isSelected;
}
