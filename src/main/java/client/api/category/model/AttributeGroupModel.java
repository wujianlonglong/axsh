package client.api.category.model;

import lombok.Data;

import java.util.List;

/**
 * Created by mac on 15/9/19.
 */
@Data
public class AttributeGroupModel {

    private Long categoryId;

    private AttributeGroupEnum attributeGroupEnum;

    private List<AttributeModel> attributeModels;
}
