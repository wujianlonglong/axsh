package anxian.gateway.admin.module.business.model.item;

import client.api.item.domain.Product;
import client.api.item.domain.ProductSpecificationValue;
import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-12-24.
 */
@Data
public class ProductSpecificationValueModel extends Product {

    /**
     * 商品规则值
     */
    private List<ProductSpecificationValue> productSpecificationValues;

}
