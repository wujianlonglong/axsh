package client.api.sale.model.secKill;

import lombok.Data;

import java.io.Serializable;

/**
 * 秒杀模型
 */
@Data
public class SecKillParamDTO implements Serializable {

    /**
     * 促销ID
     */
    private String id;

    /**
     * 促销广告
     */
    private String itemAd;

    /**
     * 联系方式
     */
    private String telephoneStr;

    /**
     * 短信提示内容
     */
    private String smsContent;

    /**
     * 商品爆炸贴
     */
    private String itemDetonation;

    private String operator;

    private Integer saleOrderNum;

    private String seckillIllustration;

}
