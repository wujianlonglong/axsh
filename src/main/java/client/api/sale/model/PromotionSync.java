package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 促销同步
 * Created by byinbo on 2016/12/20.
 */
@Data
public class PromotionSync implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * ERP_ID
     */
    private String sheetId;

    /**
     * 促销类型
     */
    private String promotionType;

    /**
     * 促销名称
     */
    private String promotionName;

    /**
     * 使用场景
     */
    private int[] scenes;

    /**
     * 会员级别
     */
    private int[] memberLevels;

    /**
     * 广告语
     */
    private String slogan;

    /**
     * 商品促销信息
     */
    private String promotionInfo;

    /**
     * 优惠金额上限
     */
    private double discountLimit;

    /**
     * 手机号
     */
    private String[] mobiles;

    /**
     * 提示内容
     */
    private String content;

    /**
     * 门店编码
     */
    private List<String> shopIds;

    /**
     * 商品编码
     */
    private List<String> gdIds;

    /**
     * 图片
     */
    private String imageUrl;

}
