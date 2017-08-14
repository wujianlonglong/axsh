package client.api.fresh.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-12-3.
 */
@Data
public class SupermarketCategoryModel extends SupermarketCategory {

    /**
     * 子分类列表
     */
    private List<SupermarketCategory> childSupermarketCategories;

}
