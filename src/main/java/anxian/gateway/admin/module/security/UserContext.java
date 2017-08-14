package anxian.gateway.admin.module.security;

import anxian.gateway.admin.module.base.domain.*;
import anxian.gateway.admin.module.base.model.LeftTreeChild;
import anxian.gateway.admin.module.base.model.LeftTreeParent;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by jiangzhe on 15-11-17.
 * 系统中需要用到的用户信息，用户登陆后才能获取到
 */
public class UserContext implements UserDetails {

    private AclUser user;
    private List<UserAuthority> userAuthorities;
    private List<UserRole> userRoles;

    public UserContext(AclUser user) {
        String username = user.getUsername();
        if ((username == null) || ("".equals(username)) || (user.getPassword() == null) || (user.getPassword().equals(""))) {
            throw new BadCredentialsException("Cannot pass null or empty values to constructor");
        }

        this.user = user;
    }

    public AclUser getUser() {
        return user;
    }

    public void setUser(AclUser user) {
        this.user = user;
    }

    public List<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority[] authorities = new GrantedAuthority[this.userAuthorities.size()];
        for (int i = 0; i < this.userAuthorities.size(); ++i) {
            Authority authority = this.userAuthorities.get(i).getAuthority();

            authorities[i] = authority;
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

    /**
     * 用户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        Date expiredDate = this.user.getExpiredDate();

        if (expiredDate == null) {
            return true;
        }

        return expiredDate.getTime() >= System.currentTimeMillis();
    }

    /**
     * 用户是否被锁住
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        if (this.user.getAccountLocked() == null) {
            return true;
        }
        return this.user.getAccountLocked().intValue() == 0;
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
        if (this.user.getAccountEnabled() == null)
            return true;
        return this.user.getAccountEnabled().intValue() == 1;
    }

    /**
     * 是否是超级管理员
     *
     * @return
     */
    public boolean isSupperManager() {
        if (this.user.getUserMgrType() == null)
            return false;
        return this.user.getUserMgrType().equals(UserType.USERTYPE_ADMINISTRATOR);
    }

    /**
     * 是否是系统管理员
     *
     * @return
     */
    public boolean isManager() {
        if (this.user.getUserMgrType() == null)
            return false;
        return this.user.getUserMgrType().equals(UserType.USERTYPE_MANAGER);
    }

    /**
     * 返回用户可以操作的菜单
     *
     * @return
     */
    public Set<Menu> getUserMenus() {
        Set<Menu> menus = new HashSet<>();
        for (UserAuthority userAuthority : getUserAuthorities()) {
            menus.add(userAuthority.getAuthority().getMenu());
        }

        return menus;
    }

    /**
     * 根据用户可以操作的菜单生成前端需要的json
     *
     * @return
     */
    public List<LeftTreeParent> getLeftTree() {
        Set<Menu> menuSet = getUserMenus();

        List<LeftTreeParent> leftTreeParents = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuSet)) {//菜单不为空
            Set<Menu> parentMenus = new HashSet<>();
            for (Menu menu : menuSet) {
                if (menu.isLeaf()) {
                    parentMenus.add(menu.getParentMenu());
                }
            }

            //开始生成菜单json
            for (Menu parentMenu : parentMenus) {
                LeftTreeParent leftTreeParent = new LeftTreeParent();
                leftTreeParent.setId(parentMenu.getId());
                leftTreeParent.setText(parentMenu.getText());
                leftTreeParent.setParent(parentMenu.isParent());
                leftTreeParent.setExpanded(parentMenu.isExpanded());
                leftTreeParent.setSort(parentMenu.getSort());

                List<LeftTreeChild> children = new ArrayList<>();
                for (Menu childMenu : menuSet) {
                    if (parentMenu.getId() == childMenu.getParentMenu().getId()) {
                        LeftTreeChild treeChild = new LeftTreeChild();
                        treeChild.setId(childMenu.getId());
                        treeChild.setText(childMenu.getText());
                        treeChild.setLeaf(childMenu.isLeaf());
                        treeChild.setUrl(childMenu.getUrl());
                        treeChild.setSort(childMenu.getSort());
                        children.add(treeChild);

                    }
                }

                Collections.sort(children, new Comparator<Object>() {//对子菜单排序

                    public int compare(Object o1, Object o2) {
                        LeftTreeChild menuLink1 = (LeftTreeChild) o1;
                        LeftTreeChild menuLink2 = (LeftTreeChild) o2;
                        int sort1 = null == menuLink1 ? 0 : (null == menuLink1.getSort() ? 0 : menuLink1.getSort().intValue());
                        int sort2 = null == menuLink2 ? 0 : (null == menuLink2.getSort() ? 0 : menuLink2.getSort().intValue());
                        if (sort1 > sort2) return 1;
                        if (sort1 < sort2) return -1;

                        return 0;

                    }

                });

                leftTreeParent.setChildren(children);
                leftTreeParents.add(leftTreeParent);

            }

            Collections.sort(leftTreeParents, new Comparator<Object>() {//对父菜单排序

                public int compare(Object o1, Object o2) {
                    LeftTreeParent menuLink1 = (LeftTreeParent) o1;
                    LeftTreeParent menuLink2 = (LeftTreeParent) o2;

                    if (menuLink1.getSort() > menuLink2.getSort()) return 1;
                    if (menuLink1.getSort() < menuLink2.getSort()) return -1;

                    return 0;

                }

            });

        }

        return leftTreeParents;
    }

}
