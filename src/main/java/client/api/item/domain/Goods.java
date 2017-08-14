package client.api.item.domain;


import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity - 主商品
 * Created by qinhailong on 15-8-21.
 */
@Data
public class Goods implements Serializable {

    private Long id; // 主键

    private String code; // 商品内码(顺序号)

    private Long erpGoodsId; // 商品管理码

    private String goodsName; //商品名

    private Long categoryId; // 分类id

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

}
