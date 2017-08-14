package client.api.item.model;

import client.api.item.domain.Product;
import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-12-25.
 */
@Data
public class ProductCategoryModel extends Product {

    /**
     * 商品多分类列表
     */
    private List<ProductCategory> productCategories;

}
