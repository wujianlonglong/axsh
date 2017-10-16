package client.api.sale.model.secKill;

import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SecKillMongo implements Serializable {

    private String id;
    /**
     * 促销名称
     */
    private String name = "";

    /**
     * 促销类型
     */
    private Integer saleType = 5;

    /**
     * 开始日期
     */
    private String startDateTime;


    private LocalDateTime startTime;

    /**
     * 结束日期
     */
    private String endDateTime;

    private LocalDateTime endTime;

    /**
     * 使用场景
     */
    private List<Integer> envType = new ArrayList<>();

    /**
     * 促销总笔数
     */
    private Integer totalNumber = 0;

    /**
     * 参与门店状态: 1 所有门店;2 部分门店
     */
    private Integer joinGateShopType = 0;

    /**
     * 参与门店列表
     */
    private List<String> joinedGateShops = new ArrayList<>();

    /**
     * 用户每天限买秒杀商品次数
     */
    private Integer moreBuyNum = 1;

    /**
     * 商品ID
     */
    private Long productId;

    private BigDecimal salePrice;

    /**
     * 备注
     */
    private String memo = "";

    /**
     * 要发消息的手机号
     */
    private List<String> telephoneList = new ArrayList<>();

    /**
     * 消息发送的内容
     */
    private String smsContent = "";

    private String itemAd;                      // 商品广告语
    private String itemDetonation;              // 商品爆照贴

    /**
     * 促销状态
     */
    private Integer status = SaleConstant.notBegin;

    /**
     * 是否同步
     */
    private Boolean isSync;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    private Integer saleOrderNum;

    private String seckillIllustration;
}
