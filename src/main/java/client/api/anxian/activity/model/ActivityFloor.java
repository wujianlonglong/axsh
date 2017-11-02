package client.api.anxian.activity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by byinbo on 2017/10/17.
 */
@Data
public class ActivityFloor implements Serializable{

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 排版方式：0-默认样式，1-自定义图片
     */
    private Integer style;

    /**
     * 背景颜色
     */
    private String backgroudColor;

    /**
     * 背景图片
     */
    private String backgroudImage;

    /**
     * 标题名称
     */
    private String title;

    /**
     * 是否设置导航标题
     */
    private Boolean isNavigationTitle;

    /**
     * 标题图片
     */
    private String titleImage;

    /**
     * 导航标题对象
     */
    private NavigationTitle navigationTitle;

    /**
     * 标题链接
     */
    private String titleUrl;

    /**
     * 商品列数
     */
    private Integer goodsColumn;

    /**
     * 商品对象集合
     */
    private List<ActivityGoods> activityGoodsList;

    /**
     * 图片组
     */
    private List<ActivityImage> activityImageList;




}
