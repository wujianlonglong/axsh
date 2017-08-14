package client.api.www.constants;

/**
 * Created by qinhailong on 15/9/1.
 */
public final class Constants {

    /**
     * 定义缓存的常量
     */
    public class CacheConstants {

        /**
         * 广告端口缓存常量
         */
        public static final String CACHE_ADVERTISEMENT_DEVICES = "cacheAdvertisementDevices";

        /**
         * 广告缓存常量
         */
        public static final String CACHE_ADVERTISEMENT = "cacheAdvertisement";

        /**
         * 分类广告缓存常量
         */
        public static final String CACHE_CATEGORY_ADVERTISEMENT = "cacheCategoryAdvertisement";

        /**
         * 热门搜索词缓存常量
         */
        public static final String CACHE_HOT_SEARCH = "cacheHotSearch";

        /**
         * 公告缓存常量
         */
        public static final String CACHE_ARTICLE = "cacheArticle";

        /**
         * 热销好货缓存常量
         */
        public static final String CACHE_HOT_GOODS = "cacheHotGoods";

        /**
         * 首行缓存常量
         */
        public static final String CACHE_TOP_NAVIGATION = "cacheTopNavigation";

        /**
         * 楼层缓存常量
         */
        public static final String CACHE_FLOOR = "cacheFloor";

        /**
         * 楼层分类缓存常量
         */
        public static final String CACHE_FLOOR_CATEGORY = "cacheFloorCategory";

        /**
         * 楼层广告缓存常量
         */
        public static final String CACHE_FLOOR_ADVERTISEMENT = "cacheFloorAdvertisement";

        /**
         * 楼层关键字缓存常量
         */
        public static final String CACHE_FLOOR_KEYWORD = "cacheFloorKeyword";

        /**
         * 分类商品对应关系缓存常量
         */
        public static final String CACHE_CATEGORY_PRODUCT = "cacheCategoryProduct";

    }

    /**
     * 分类广告类型
     */
    public class CategoryAdvertisementTypeConstants {

        /**
         * 品牌广告
         */
        public static final int BRAND = 0;

        /**
         * 大图广告
         */
        public static final int BIG_PICTURE = 1;

    }

    /**
     * 楼层分类
     */
    public class FloorCategoryType {

        /**
         * 左侧分类
         */
        public static final int LEFT_CATEGORY = 0;

        /**
         * 顶层分类
         */
        public static final int TOP_CATEGORY = 1;

    }

    /**
     * 楼层分类
     */
    public class FloorAdvertisementType {

        /**
         * 右侧广告
         */
        public static final int RIGHT_ADVERTISE = 0;

        /**
         * 品牌广告
         */
        public static final int BRAND_ADVERTISE = 1;

    }

    /**
     * 分隔符常量
     */
    public class SeparatorConstans {

        /**
         * 顿号分隔符
         */
        public static final String SEPARATOR_COMMA = "、 ";

        /**
         * 指向分隔符
         */
        public static final String SEPARATOR_POINT = " -> ";
    }

    /**
     * 状态常量
     */
    public class StatusConstants {

        /**
         * 正常状态常量
         */
        public static final int NOMAL = 0;

        /**
         * 锁住状态常量
         */
        public static final int LOCK = 1;

        /**
         * 删除状态常量
         */
        public static final int DELETE = 2;

    }

    /**
     * 热销好货一屏显示数目
     */
    public static final int HOT_GOODS_NUM = 8;

    /**
     * 楼层广告limit
     */
    public class FloorAdvertisementLimit {

        /**
         * 商品数目
         */
        public static final int TOP_PRODUCT_LIMIT = 8;

        /**
         * 右侧广告
         */
        public static final int RIGHT_LIMIT = 2;

        /**
         * 品牌广告
         */
        public static final int BRAND_LIMIT = 2;

    }

    /**
     * 焦点图limit
     */
    public class AdvertisementLimit {

        /**
         * 右焦
         */
        public static final int RIGHT_LIMIT = 1;

        /**
         * 2屏推荐
         */
        public static final int RECOMMENDED_LIMIT = 4;


    }

}
