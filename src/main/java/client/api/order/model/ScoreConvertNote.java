package client.api.order.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhangyunan on 2017/7/28.
 */
@Data
public class ScoreConvertNote {
    /***
     * 兑换时间
     */
    private String convertDate;

    /***
     * 门店编号
     */
    private String shopId;

    /***
     * 门店名称
     */
    private String shopName;

    /***
     * 会员卡号
     */
    private String cardNum;

    /***
     * 会员姓名
     */
    private String memberName;

    /***
     * 会员联系方式
     */
    private String mobile;

    /***
     * 商品管理码
     */
    private Long itemId;

    /***
     * 商品名称
     */
    private String itemName;

    /***
     * 商品总金额
     */
    private BigDecimal retailAmount;

    /***
     * 消耗积分
     */
    private Long scoreAmount;

    /***
     * 兑换数量
     */
    private Integer purchasNum;

}
