package client.api.item.model;

import client.api.item.domain.Goods;
import client.api.item.domain.Specification;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * Created by mac on 15/9/9.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsModel extends Goods {

    private List<Specification> specifications;

    public String getDimension1() {
        if (!CollectionUtils.isEmpty(specifications)) {
            return specifications.get(0).getName();
        }
        return "";
    }

    public String getDimension2() {
        if (!CollectionUtils.isEmpty(specifications) && specifications.size() > 1) {
            return specifications.get(1).getName();
        }
        return "";
    }
}
