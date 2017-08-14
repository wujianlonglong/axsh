package client.api.store.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by kimiyu on 16/1/5.
 */
@Data
public class GateShopSearch implements Serializable {
    /**
     * 区域
     */
    private String areaId;
    /**
     * 门店编码
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 当前页数
     */
    private Integer page;
    /**
     * 每页显示几号
     */
    private Integer size;
}
