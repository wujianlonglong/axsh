package client.api.order.model;

/**
 * 订单来源
 * Created by kimiyu on 15/11/4.
 */
public interface OrderSource {

    /**
     * 网站
     */
    int WEBSITE = 1;
    String WEBSITE_STR = "网站";
    /**
     * APP
     */
    int APP = 2;
    String APP_STR = "移动端";
    /**
     * 微商城
     */
    int WEB_CHAT = 3;
    String WEB_CHAT_STR = "微商城";

    int ANDROID = 6;
    String ANDROID_STR = "安卓端";

    int IOS = 7;
    String IOS_STR = "IOS端";
}
