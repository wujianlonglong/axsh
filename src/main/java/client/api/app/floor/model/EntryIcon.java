package client.api.app.floor.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by gaoqichao on 15-9-28.
 * 入口活动ICON维护
 */
@Data
public class EntryIcon implements Serializable {

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

    /**
     * 开始时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate = LocalDate.of(1, 1, 1);

    /**
     * 结束时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate = LocalDate.of(9999, 12, 31);

    /**
     * 展示终端列表
     */
    private String terminalList;

    /**
     * 创建人员id
     */
    private String userId;

    /**
     * 创建人员名称
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 修改时间
     */
    private Date lastModifiedDate;
}
