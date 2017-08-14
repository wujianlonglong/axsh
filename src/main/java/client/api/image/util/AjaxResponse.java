package client.api.image.util;

import lombok.Data;

/**
 * Ajax 响应消息
 * Created by mac on 15/8/29.
 */
@Data
public class AjaxResponse {

    /**
     * 类型
     */
    public enum Type {
        /**
         * 成功
         */
        sucess,

        /**
         * 警告
         */
        warn,

        /**
         * 错误
         */
        error
    }

    /**
     * 类型
     */
    private Type type;

    /**
     * 内容
     */
    private String content;

    /**
     * 初始化一个新建的 AjaxResponse 对象，使其表示一个空消息
     */
    public AjaxResponse() {

    }

    /**
     * 初始化一个新创建的 AjaxResponse 对象
     *
     * @param type    类型
     * @param content 内容
     */
    public AjaxResponse(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * 初始化一个新创建的 AjaxResponse 对象
     *
     * @param type    类型
     * @param content 内容
     * @param args    参数
     */
    public AjaxResponse(Type type, String content, Object... args) {
        this.type = type;
        this.content = SpringUtils.getMessage(content, args);
    }

    /**
     * 返回成功消息
     *
     * @param content 类型
     * @param args    内容
     * @return 成功消息
     */
    public static AjaxResponse success(String content, Object... args) {
        return new AjaxResponse(Type.sucess, content, args);
    }

    /**
     * 返回警告消息
     *
     * @param content 内容
     * @param args    参数
     * @return 警告消息
     */
    public static AjaxResponse warn(String content, Object... args) {
        return new AjaxResponse(Type.warn, content, args);
    }

    /**
     * 返回错误消息
     *
     * @param content 内容
     * @param args    参数
     * @return 错误消息
     */
    public static AjaxResponse error(String content, Object... args) {
        return new AjaxResponse(Type.error, content, args);
    }
}
