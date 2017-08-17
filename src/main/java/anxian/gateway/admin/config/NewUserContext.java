package anxian.gateway.admin.config;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import anxian.gateway.admin.module.base.domain.NewRole;
import anxian.gateway.admin.module.base.domain.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by 傅东伟 on 15-11-17.
 * 系统中需要用到的用户信息，用户登陆后才能获取到
 */
public class NewUserContext implements UserDetails {

    private User user;
    private List<NewAuthority> userAuthorities;
    private NewRole role;

    public NewUserContext(User user) {
        String username = user.getUsername();
        if ((username == null) || ("".equals(username)) || (user.getPassword() == null) || (user.getPassword().equals(""))) {
            throw new BadCredentialsException("Cannot pass null or empty values to constructor");
        }

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NewAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<NewAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public NewRole getRole() {
        return role;
    }

    public void setRole(NewRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority[] authorities = new GrantedAuthority[this.userAuthorities.size()];
        for (int i = 0; i < this.userAuthorities.size(); ++i) {
            authorities[i] = this.userAuthorities.get(i);
        }

        return Arrays.asList(authorities);
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户是否被锁住
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    /**
     * 用户凭证是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否使用中
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
