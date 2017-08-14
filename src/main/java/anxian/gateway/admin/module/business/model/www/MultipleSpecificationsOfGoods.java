package anxian.gateway.admin.module.business.model.www;

import client.api.item.model.SpecificationModel;
import lombok.Data;

import java.util.List;

/**
 * Created by Jianghe on 15/12/25.
 */
@Data
public class MultipleSpecificationsOfGoods {

    private List<SpecificationModel> specificationModels;
}
