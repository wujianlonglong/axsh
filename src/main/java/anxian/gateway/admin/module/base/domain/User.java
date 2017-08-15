package anxian.gateway.admin.module.base.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private ObjectId id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐户是否锁住
     */
    private boolean accountLocked;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 所属角色
     */
    @DBRef
    private NewRole newRole;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 描述
     */
    private String description;

    @CreatedBy
    private String creater;

    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedBy
    private String updater;

    @LastModifiedDate
    private LocalDateTime updateDateTime;
}
