package client.api.item.domain;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by qinhailong on 16-2-18.
 */
@Data
public class PackageModel implements Serializable {

    private Long id; // 主键

    private Long erpGoodsId; // 对应ERP GoodsID

    private Integer packageNo; // 包装顺序号(0=销售）

    private String barCode; // 多条码/包装条码

    private String specificationName; // 规格名称（件、包、箱等）

    private String packageSpecification; // 包装规格( nn*nn)

    private Integer packageNumber; // 件装数(本包装含SKU的数量)

    private Integer packageWeight; // 包装重量

    private String place; // 产地

    private String virtualSn; // 三江虚拟商品编码

    private String shelfHeight; // 陈列规格（宽）

    private String shelfWeight; // 陈列规格（高）

    private Integer largeShopReplen; // 包装重量

    private Integer smallShopReplen; // 包装重量

    private Boolean isOrderCode; // 是否订货条码 (0=否 1=是)

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间
}
