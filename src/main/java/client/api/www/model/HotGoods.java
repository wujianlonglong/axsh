package client.api.www.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * 热销好货
 * Created by mac on 15/9/18.
 */
@Data
public class HotGoods implements Serializable {

    private BigInteger id;

    private Integer type; // 类型

    private String imgSize; // 图片尺寸

    private Long erpGoodsId; // 商品管理码

    private String productSn; // 商品编码

    private String productName; // 商品名称

    private String productDisplayName; // 商品显示名称

    private BigDecimal salePrice; // 销售价

    private BigDecimal memberPrice; // 会员价

    private Integer orders; // 排序

    private String imgSrc; // 图片路径

    private String promotion; // 促销语

    private String promotionBgColor; // 促销背景色选择

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

}
