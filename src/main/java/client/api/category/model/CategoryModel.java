package client.api.category.model;

import client.api.category.domain.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by mac on 15/8/24.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryModel extends Category {

    private List<CategoryModel> categoryModels;

}
