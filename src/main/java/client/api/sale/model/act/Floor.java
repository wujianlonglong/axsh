package client.api.sale.model.act;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaoqichao on 15-9-18.
 */
@Data
public class Floor implements Serializable {
    /**
     * id
     */
    private String floorId;

    /**
     * 楼层名称
     */
    private String floorName;

    /**
     * 模板id
     */
    private String actTemplateId;

    /**
     * 行
     */
    private Integer span;

    /**
     * 列
     */
    private Integer column;

    /**
     * 排序顺序
     */
    private Integer sort;

    /**
     * 分栏图链接
     */
    private String cloumnsImagePath;

    /**
     * 更多链接
     */
    private String moreLink;

    /**
     * 商品id列表
     */
    private List<String> snList;

    /**
     * 活动id列表
     */
    private List<String> saleIdList;

    /**
     * 补充图片
     */
    private String extraImgPath;

    /**
     * 补充图片链接
     */
    private String extraLinkedUrl;

    /**
     * 楼层广告图片
     */
    private String floorAdvImgPath;

    /**
     * 楼层广告链接url
     */
    private String floorAdvImgLinkedUrl;
}
