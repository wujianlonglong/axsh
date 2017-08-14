package client.api.fresh.model;

import lombok.Data;

/**
 * Created by qinhailong on 16-1-4.
 */
@Data
public class Supermarkets {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 超市名称
     */
    private String supermarketName;

    /**
     * 超市页分类
     */
    private String supermarketCategoryName;

    /**
     * 超市页楼层名称
     */
    private String supermarketFloorName;

}
