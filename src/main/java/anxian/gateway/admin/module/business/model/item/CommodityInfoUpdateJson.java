package anxian.gateway.admin.module.business.model.item;

import client.api.item.domain.ParameterGroupModel;
import client.api.item.domain.ProductImage;
import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品修改数据加载类
 * Created by jiangzhe on 15-12-3.
 */
@Data
public class CommodityInfoUpdateJson implements Serializable {
    private Long id; // 主键

    private Long goodsId; // 主商品ID

    private String goodsName;//主商品名称

    private Long erpGoodsId; // 对应ERP GoodsID

    private String goodsCode; // 商品内码(顺序号)

    private String sn; // 商品编码

    private String barCode; // 商品条码

    private String name; // 商品名称

    private Double salePrice; // 销售价

    private Double memberPrice; // 会员价

    private String weight; // 商品重量

    private String place; // 商品产地

    private String source; // 商品来源, 枚举(自营/联营)

    private Long sales; //　销量

    private Boolean isBargains; // 是否是惠商品

    private Integer transportType; // 商品运输类型 常温:0 冷藏/冷冻:1 保热:2

    private Long categoryId; // 商品分类Id

    private Long brandId; // 品牌ID

    private String brandName;//品牌名称

    private Integer status; // 0/1/2; 正常销售/下架停售/未审核

    private Long displaySales; // 展示销量

    private Long erpProductId; // ERP产品ID

    private String adSlogan; // 广告语

    private Boolean isPromotionParticular; // 是否是促销例

    private String introduction; // 介绍

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

    /**
     * 商品参数组列表
     */
    private List<ParameterGroupModel> parameterGroupModels;

    /**
     * 商品图片
     */
    private List<ProductImage> productImages;

}
