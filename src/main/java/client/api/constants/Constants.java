package client.api.constants;

/**
 * Created by qinhailong on 15-12-21.
 */
public class Constants {

    /**
     * 首页
     */
    public static final String SJES_API_WWW = "sjes-api-www";

    /**
     * 商品
     */
    public static final String SJES_API_ITEM = "sjes-api-item";

    public static final String ANXIAN_SJES_API_ITEM = "anxian-sjes-api-item";



    /**
     * 分类
     */
    public static final String SJES_API_CATEGORY = "sjes-api-category";

    /**
     * 超市页
     */
    public static final String SJES_API_FRESH = "sjes-api-fresh";

    /**
     * 批量更新
     */
    public static final String SJES_BATCH_ITEM = "sjes-batch-item";

    /**
     * App
     */
    public static final String SJES_API_APP = "sjes-api-app";

    /**
     * APP热销专区id
     */
    public static final String APP_HOTGOODS_ZONEID = "10002";

    /**
     * 分类级别常量
     */
    public class CategoryGradeConstants {

        /**
         * 一级分类
         */
        public static final int GRADE_ONE = 1;

        /**
         * 二级分类
         */
        public static final int GRADE_TWO = 2;

        /**
         * 三级分类
         */
        public static final int GRADE_THREE = 3;
    }

    /**
     * 修改价格类型
     */
    public class ModifyPriceLogType {
        /**
         * 销售价类型
         */
        public static final int SALE_PRICE_TYPE = 0;

        /**
         * 会员价类型
         */
        public static final int MEMBER_PRICE_TYPE = 1;

    }

    /**
     * 类型
     */
    public class SnType {

        /**
         * 商品
         */
        public static final int SN_TYPE_PRODUCT = 0;

    }

    /**
     * 属性类型
     */
    public class CategoryType {

        /**
         * 后台分类类型
         */
        public static final int CATEGORY_TYPE = 0;

        /**
         * 自定义类型
         */
        public static final int SELF_DEFINED_TYPE = 1;

    }

}
