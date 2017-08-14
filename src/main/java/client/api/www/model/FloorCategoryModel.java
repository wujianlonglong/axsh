package client.api.www.model;


import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-11.
 */
@Data
public class FloorCategoryModel extends FloorCategory {

    private List<CategoryProduct> categoryproducts;

}

