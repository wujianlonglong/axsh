package anxian.gateway.admin.module.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> implements Serializable {

    private int code = 0;

    private String errMessage;

    private T data;

    public static <T> ResponseMessage defaultSuccess(T data) {
        return new ResponseMessage(1, "", data);
    }

    public static <T> ResponseMessage defaultFailure(String errMessage, T data) {
        return new ResponseMessage(0, errMessage, data);
    }

}
