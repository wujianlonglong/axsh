package anxian.gateway.admin.module.business.model.item;

import client.api.item.model.ProductCategoryModel;
import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-12-25.
 */
@Data
public class ProductCategoryDetailModel extends ProductCategoryModel {

    /**
     * 分类簇
     */
    private List<String> categorNames;

    List<String> categoryNames;

}
