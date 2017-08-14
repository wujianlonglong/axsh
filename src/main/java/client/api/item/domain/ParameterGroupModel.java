package client.api.item.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 15/8/29.
 */
@Data
public class ParameterGroupModel implements Serializable {

    /**
     * 参数组名称
     */
    private String name;

    /**
     * 商品参数列表
     */
    private List<ParameterModel> parameters;
}
