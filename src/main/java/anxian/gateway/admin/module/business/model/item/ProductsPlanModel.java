package anxian.gateway.admin.module.business.model.item;

import client.api.item.domain.Product;
import client.api.item.domain.ProductStatusPlan;
import client.api.item.domain.ProductsPlan;
import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 17-1-11.
 */
@Data
public class ProductsPlanModel extends ProductsPlan {

    private ProductStatusPlan productStatusPlan;

    private List<Product> products;

}
