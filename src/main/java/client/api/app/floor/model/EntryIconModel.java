package client.api.app.floor.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by byinbo on 2016/12/6.
 */
@Data
public class EntryIconModel implements Serializable {

    private Long id;

    /**
     * icon类型：１－８
     */
    private int type;

    /**
     * 入口名称
     */
    private String name;

    /**
     * 入口图片
     */
    private String imagePath;

    /**
     * 对应内容类型：0--活动；1--url
     */
    private Integer linkedType;

    /**
     * 类型为活动时对应专题id；类型为url时对应url地址
     */
    private String linkedContent;

    private String startDateStr; // 开始时间字符串

    private String endDateStr; // 结束时间字符串

    private String shopId;

    private String shopName;
}
