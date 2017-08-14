package client.api.item.domain;

import lombok.Data;

import java.util.List;


/**
 * Created by mac on 15/8/29.
 */
@Data
public class ProductModel extends Product {

    /**
     * 主商品信息
     */
    private Goods goods;

    /**
     * 品牌信息
     */
    private Brand brand;

    /**
     * 商品属性选项
     */
    private List<ProductAttributeValue> productAttributeValues;

    /**
     * 商品参数组列表
     */
    private List<ParameterGroupModel> parameterGroupModels;

    /**
     * 商品图片
     */
    private List<ProductImage> productImages;

}
