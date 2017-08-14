package anxian.gateway.admin.module.base.domain;

import java.io.Serializable;

/**
 * 用户类型
 *
 * @author JiangZ
 */
public class UserType implements Serializable {
    public static final int USERTYPE_ADMINISTRATOR = 1400; //超级管理员
    public static final int USERTYPE_MANAGER = 1401;      //普通管理员
    public static final int USERTYPE_MENUAL = 1402;       //普通用户
}
