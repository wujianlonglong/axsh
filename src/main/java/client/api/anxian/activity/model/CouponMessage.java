package client.api.anxian.activity.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wangdinglan on 2017/10/16
 */
@Data
public class CouponMessage implements Serializable {
    /**
     * 优惠券内容
     */
    private String couponDes;

    /**
     * 优惠券图片
     */
    private String couponImage = "";

    /**
     * 优惠券券阶梯
     */
    private Integer couponLevel;

    /**
     * 获取优惠券链接
     */
    private String couponUrl = "";
}
