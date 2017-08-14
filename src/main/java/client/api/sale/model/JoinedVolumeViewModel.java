package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wangdinglan on 2017/05/27
 */
@Data
public class JoinedVolumeViewModel implements Serializable {
    /**
     * 优惠券ID
     */
    private String saleId;
    /**
     * 优惠券名称
     */
    private String saleName;
    /**
     * 优惠券内容
     */
    private String saleContent;
    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 卷数量
     */
    private Integer totalNumber;

    /**
     * 券等级
     */
    private Integer volumeLevel;

    /**
     * 促销类型
     */
    private Integer saleType;

    /**
     * 有效期
     */
    private String validity;
}
