package client.api.utils;

import lombok.Data;

/**
 * Created by gaoqichao on 15-11-18.
 */
@Data
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
}
