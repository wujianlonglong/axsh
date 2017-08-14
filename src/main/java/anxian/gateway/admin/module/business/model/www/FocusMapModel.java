package anxian.gateway.admin.module.business.model.www;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jianghe on 16/1/4.
 */
@Data
public class FocusMapModel implements Serializable {

    private Long id;

    private Integer position; // 区域 1:顶通,2:首焦,3:中焦,4:右焦,5:2屏推荐,6:半通

    private String scope; //适用范围

    private String scopeName; //适用范围名称

    private Integer pcOrders; //pc排序

    private String pcImg; //pc图片

    private String pcHref; //pc链接地址

    private Integer appOrders; //app排序

    private String appImg; //app图片

    private String appHref; //app链接地址

    private Integer wOrders; //微商城排序

    private String wImg; //微商城图片

    private String wHref; //微商城链接地址

    private Integer appPromotionOrders;//APP促销排序

    private String appPromotionImg;//APP促销图片

    private String appPromotionHref;//APP促销链接地址

    private String startDate; //开始时间

    private String endDate; //结束时间

    private Integer status; //状态 1:正常,2:不展示

    private String remarker; //备注
}
