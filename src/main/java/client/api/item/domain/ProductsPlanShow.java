package client.api.item.domain;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by qinhailong on 17-1-10.
 */
@Data
public class ProductsPlanShow implements Serializable {

    private Long id; // 主键

    private Long planId; // 计划id

    private Long erpGoodsId; // 商品管理码

    private String name;

    private String sn;

    private Integer status;

    private BigDecimal memberPrice;

    private BigDecimal salePrice;


    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    private String shopId;
}
