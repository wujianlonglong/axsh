package client.api.anxian.activity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangdinglan on 2017/10/16
 */
@Data
public class AnxianActivityCoupon implements Serializable {

    @Id
    private String id;

    /**
     * 优惠券活动id
     */
    private String promotionId;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 参与的优惠券
     */
    private List<CouponMessage> joinedVolumes;

    /**
     * 优惠券说明图
     */
    private String couponDescImage;
}
