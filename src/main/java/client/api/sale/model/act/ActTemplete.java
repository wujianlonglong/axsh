package client.api.sale.model.act;

import client.api.sale.util.ActProductClassEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaoqichao on 15-9-19.
 */
@Data
public class ActTemplete implements Serializable {
    /**
     * 活动模板id
     */
    private String actTempleteId;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 活动模板名称
     */
    private String actTempleteName;

    /**
     * 活动模板类型
     */
    private Integer type;

    /**
     * 头图图片路径
     */
    private String headImagePath;

    /**
     * 头图图片描述
     */
    private String headImageDescription;

    /**
     * 底图图片路径
     */
    private String footImagePath;

    /**
     * 时间轴图片路径
     */
    private String timeLineImgPath;

    /**
     * 活动说明图片路径
     */
    private String actIntroImgPath;


    /**
     * 促销商品id列表
     */
    private List<PromotionProduct> promotionProducts;

    /**
     * 优惠券入口列表
     */
    private List<CouponEntry> couponEntryList;

    /**
     * 券说明
     */
    private String couponIntro;

    /**
     * 楼层数量
     */
    private Integer floorCount;

    /**
     * 楼层列表
     */
    private List<Floor> floors;

    /**
     * 浮动导航图片路径
     */
    private String navigationImgPath;

    /**
     * 浮动导航链接
     */
    private String navigationImgLinked;

    /**
     * 活动入口列表
     */
    List<ActEntry> actEntries;

    /**
     * 选中商品样式,默认为晃动效果
     */
    private Integer chosedProductClass = ActProductClassEnum.ROCK.value();

    /**
     * 图片组
     */
    List<FootInfo> imageGroup;

    /**
     * 底部信息
     */
    List<FootInfo> footInfoList;


    @Data
    public static class CouponEntry implements Serializable {
        /**
         * 优惠券id
         */
        private String couponId;

        /**
         * 优惠券名称
         */
        private String couponName;

        /**
         * 优惠券入口图片路径
         */
        private String entryImagePath;

        /**
         * 补充图片链接
         */
        private String extraImagePath;

        /**
         * 排序
         */
        private Integer sort;
    }

    @Data
    public static class ActEntry implements Serializable {
        /**
         * 顺序
         */
        private Integer sort;

        /**
         * 图片路径
         */
        private String imagePath;

        /**
         * 链接地址
         */
        private String linkedUrl;
    }

    @Data
    public static class FootInfo implements Serializable {
        /**
         * 排序
         */
        private int sort;
        /**
         * 图片路径
         */
        private String imagePath;
        /**
         * 广告名称
         */
        private String advName;
        /**
         * 链接地址
         */
        private String linkedUrl;
    }

    @Data
    public static class PromotionProduct implements Serializable {
        /**
         * 排序
         */
        private int sort;

        /**
         * 促销商品名称
         */
        private String name;

        /**
         * 图片链接
         */
        private String imageLink;

        /**
         * 链接地址
         */
        private String linkUrl;
    }
}
