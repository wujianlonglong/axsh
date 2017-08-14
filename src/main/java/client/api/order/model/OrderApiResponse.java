package client.api.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单API响应对象
 * Created by kimiyu on 15/12/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderApiResponse<T> {

    /**
     * 返回结果
     */
    private Integer return_code;
    /**
     * 错误消息提示
     */
    private String return_msg;
    /**
     * 返回对象
     */
    private T data;

    public OrderApiResponse(int code, String message) {
        this.return_code = code;
        this.return_msg = message;
    }

    public OrderApiResponse(int code, String message, T data) {
        this.return_code = code;
        this.return_msg = message;
        this.data = data;
    }

    public static <T> OrderApiResponse<T> getResponse(
            int code, String
            message,
            T data) {
        if (data == null) {
            return new OrderApiResponse<T>(code, message);
        } else {
            return new OrderApiResponse<T>(code, message, data);
        }
    }
}
