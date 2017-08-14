package anxian.gateway.admin.utils;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * 密码操作工具类
 * Created by jiangzhe on 15-11-26.
 */
public class PasswordUtil {

    /**
     * 密码加密
     *
     * @param express    密码明文
     * @param saltSource 盐值
     * @return
     */
    public static String generatePassword(String express, String saltSource) {
        MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");
        return passwordEncoder.encodePassword(express, saltSource);
    }
}
