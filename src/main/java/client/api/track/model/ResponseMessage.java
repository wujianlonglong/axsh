package client.api.track.model;

import lombok.Data;

/**
 * Created by kimiyu on 16/9/18.
 */
@Data
public class ResponseMessage {

    int code = 1;

    String msg = "success";

    ResponseMessage() {
    }

    ResponseMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    static ResponseMessage success() {
        return new ResponseMessage(1, "");
    }

    static ResponseMessage success(String msg) {
        return new ResponseMessage(1, msg);
    }

    static ResponseMessage fail(int code, String msg) {
        return new ResponseMessage(code, msg);
    }
}
