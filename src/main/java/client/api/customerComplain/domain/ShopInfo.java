package client.api.customerComplain.domain;

import lombok.Data;

/**
 * Created by zhangyunan on 17/3/20.
 */

@Data
public class ShopInfo {

    private Long id;
    /**
     * 门店所属大市编号
     */
    private String parentId;

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
     * 门店店长
     */
    private String shopOwner;

    /**
     * 店长联系方式
     */
    private String contactInfo;

    /**
     * 门店状态
     */
    private Integer state;

    /**
     * 区总编号
     */
    private String areaOwner;

    /**
     * 区总联系方式
     */
    private String mobile;
}
