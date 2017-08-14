package anxian.gateway.admin.module.business.controller.order.model;

/**
 * 取消理由
 * Created by Jianghe on 16/1/25.
 */
public enum CancelReason {

    amountIsTooLarge(1, "金额过大"), tooMuchWeight(2, "重量过大");

    private Integer value;

    private String text;

    CancelReason(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
