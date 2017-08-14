package client.api.item.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mac on 15/8/29.
 */
@Data
public class ParameterModel implements Serializable {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数值
     */
    private String value;

}
