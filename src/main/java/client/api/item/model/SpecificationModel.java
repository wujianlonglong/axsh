package client.api.item.model;

import client.api.item.domain.Specification;
import client.api.item.domain.SpecificationValue;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by qinhailong on 15-8-22.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpecificationModel extends Specification {

    /**
     * 规格值列表
     */
    private List<SpecificationValue> specificationValues = Lists.newArrayListWithCapacity(1);


}
