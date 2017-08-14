package anxian.gateway.admin.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by Jiangzhe on 2015/12/21.
 */
public class JsonMsg implements Serializable {

    @JsonIgnore
    public static final JsonMsg SUCCESS = new JsonMsg(true, false, null, null, null);

    @JsonIgnore
    public static final JsonMsg FAILURE = new JsonMsg(false, true, null, null, null);

    private static final long serialVersionUID = 222093315645074812L;

    private boolean success;

    private boolean failure;

    private Object data;

    private String error;

    private String msg;

    public JsonMsg() {
    }

    public JsonMsg(boolean success, boolean failure, Object data, String error, String msg) {
        this.success = success;
        this.failure = failure;
        this.data = data;
        this.error = error;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回成功信息
     *
     * @param msg 成功信息
     * @return JsonMsg
     */
    public static JsonMsg success(String msg) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setMsg(msg);
        return jsonMsg;
    }

    /**
     * 返回成功信息
     *
     * @param data 成功信息
     * @return JsonMsg
     */
    public static JsonMsg success(Object data) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(data);
        return jsonMsg;
    }

    /**
     * 返回错误信息
     *
     * @param msg 错误信息
     * @return JsonMsg
     */
    public static JsonMsg failure(String msg) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setFailure(true);
        jsonMsg.setMsg(msg);
        return jsonMsg;
    }
}
