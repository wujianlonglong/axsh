package anxian.gateway.admin.module.common.domain;

import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Ajax 响应消息
 * Created by mac on 15/8/29.
 */
@Data
@AllArgsConstructor
public class  ResponseMessage<T> implements Serializable {

    public Type getType() {
        return type;
    }

    /**
     * 类型
     */
    public enum Type {
        /**
         * 成功
         */
        success,

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

    private T data;

    /**
     * 初始化一个新建的 AjaxResponse 对象，使其表示一个空消息
     */
    public ResponseMessage() {

    }

    /**
     * 初始化一个新创建的 AjaxResponse 对象
     *
     * @param type    类型
     * @param content 内容
     */
    public ResponseMessage(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * 返回成功消息
     *
     * @param content 类型
     * @return 成功消息
     */
    public static ResponseMessage success(String content) {
        return new ResponseMessage(Type.success, content);
    }

    public static <T> ResponseMessage success(T data) {
        return new ResponseMessage(Type.success, "", data);
    }

    /**
     * 返回警告消息
     *
     * @param content 内容
     * @return 警告消息
     */
    public static ResponseMessage warn(String content) {
        return new ResponseMessage(Type.warn, content);
    }

    /**
     * 返回错误消息
     *
     * @param content 内容
     * @return 错误消息
     */
    public static ResponseMessage error(String content) {
        return new ResponseMessage(Type.error, content);
    }

    /**
     * 判断是够成功
     *
     * @param responseMessage
     * @return
     */
    public static boolean isSuccess(ResponseMessage responseMessage) {
        return "success".equals(responseMessage.getType().name());
    }
}
