package client.api.www.model;

import client.api.item.model.ProductImageModel;
import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-11.
 */
@Data
public class CategoryProductModel extends FloorCategory {

    private List<ProductImageModel> products;
}
