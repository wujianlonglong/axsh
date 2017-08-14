package client.api.item.model;

import client.api.item.domain.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by mac on 15/9/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductCategoryConditionModel extends Product {

    private Boolean isclassified; // 是否已分类

    private Long parentCategoryId; // 父分类

}
