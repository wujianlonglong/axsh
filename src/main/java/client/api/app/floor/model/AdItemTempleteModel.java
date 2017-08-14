package client.api.app.floor.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gaoqichao on 15-9-7.
 * 广告模块
 */
@Data
public class AdItemTempleteModel implements Serializable {

    private Long id;

    /**
     * 模板名称
     */
    private String templeteName;

    /**
     * 专区id
     */
    private String zoneId;

    /**
     * 商品id
     */
    private String sns;

}
