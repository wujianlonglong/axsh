package client.api.app.floor.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by gaoqichao on 15-9-30.
 */
@Data
public class Floor implements Serializable {

    private Long id;

    /**
     * 楼层序号
     */
    private Integer floorNum;

    /**
     * 楼层名称
     */
    private String floorName;

    /**
     * 展示形式
     */
    private Integer displayType;

    /**
     * 字体颜色
     */
    private String fontColor;

    /**
     * 专区id
     */
    private String zoneId;


    private String more;
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
     * 终端类型枚举列表
     */
    private String terminalList;

    /**
     * 创建人员
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 修改时间
     */
    private Date lastModifiedDate;

    private String shopId;
    private String shopName;
}