package anxian.gateway.admin.module.base.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "menu")
public class NewMenu implements Serializable {

    private String id;

    /**
     * 菜单名称
     */
    private String text;

    /**
     * 是否打开，在菜单树中使用
     */
    private Boolean expanded;

    /**
     * 是否是父菜单
     */
    private boolean isParent;

    /**
     * 是否是子菜单
     */
    private boolean leaf;

    /**
     * 功能模块地址
     */
    private String url;

    /**
     * 父菜单
     */
    private String parentId;


    private Integer sort;

    private Boolean isValid = true;

    @DBRef
    private List<NewAuthority> authorities;

    private String creator;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDateTime;

    private String updator;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateDateTime;
}
