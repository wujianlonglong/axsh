package client.api.app.floor.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by baoyinbo on 15-9-30.
 */
@Data
public class FloorContentModel implements Serializable {

    private Long id;
    /**
     * 楼层id
     */
    private Long floorId;
    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 开始时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate = LocalDate.of(1, 1, 1);

    private String startDateStr; // 开始时间字符串

    private String endDateStr; // 结束时间字符串
    /**
     * 结束时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate = LocalDate.of(9999, 12, 31);

    /**
     * 对应内容类型：0--活动；1--url
     */
    private Integer linkedType;

    /**
     * 类型为活动时对应专题id；类型为url时对应url地址
     */
    private String linkedContent;

    /**
     * 主标题
     */
    private String mainTitle;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 展示顺序
     */
    private int sort;
}
