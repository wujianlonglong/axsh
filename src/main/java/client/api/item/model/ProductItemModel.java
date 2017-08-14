package client.api.item.model;

import client.api.item.domain.Product;
import client.api.item.domain.SpecificationValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by mac on 15/9/11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductItemModel extends Product {

    private String specifications1;

    private String specifications2;

    /**
     * 商品所属规格值
     */
    private List<SpecificationValue> specificationValues;

}
