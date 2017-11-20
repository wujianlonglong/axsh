package client.api.customerComplain.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhangyunan on 17/3/20.
 */

@Data
public class NewShop implements Serializable{

    private Long id;
    /**
     * 门店所属区域编号
     */
    private String areaId;

    /**
     * 门店所属区域
     */
    private String areaName;

    /**
     * 门店编号
     */
    private String shopId;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 门店状态
     */
    private Integer isDeleted;

}
