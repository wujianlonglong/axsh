package client.api.www.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mac on 15/9/17.
 */
@Data
public class Article implements Serializable {

    private Long id; // 主键

    private String name; // 公告名称

    private Integer orders; // 排序

    private Boolean isTop; // 是否置顶

    private Integer type; // 跳转类型?0:内容 ; 1:链接跳转

    private String href; // 跳转链接

    private String contents; // 内容

    private Integer status; // 状态

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

    public String getExtViewCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getCreateDate() != null ? formatter.format(createDate) : "";
    }

    public String getExtViewUpdateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getUpdateDate() != null ? formatter.format(updateDate) : "";
    }
}
