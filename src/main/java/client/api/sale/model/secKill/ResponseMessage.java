package client.api.sale.model.secKill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gaoqichao on 15-11-18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息提示
     */
    private String codeMessage;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功过
     *
     * @param data
     * @return
     */
    public static <T> ResponseMessage success(T data) {
        return new ResponseMessage(1, "", data);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static ResponseMessage failure(int code, String message) {
        return new ResponseMessage(code, message, null);
    }
}
