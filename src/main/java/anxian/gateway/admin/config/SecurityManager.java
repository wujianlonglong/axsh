package anxian.gateway.admin.config;

/**
 * Created by jiangzhe on 15-11-17.
 */
public interface SecurityManager {

    NewUserContext getNewUserContext(String username);

}
