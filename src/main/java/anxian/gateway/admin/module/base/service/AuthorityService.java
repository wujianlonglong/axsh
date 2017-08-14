package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.Authority;
import anxian.gateway.admin.module.base.domain.Menu;
import anxian.gateway.admin.module.base.model.AuthTreeParent;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-18.
 */
public interface AuthorityService {
    /**
     * 保存权限资源
     */
    @PreAuthorize("hasAuthority('AUTHORITY_SAVE')")
    Authority save(Authority authority);

    /**
     * 根据ID查询权限
     */
    Authority findById(Long id);

    /**
     * 根据ID删除权限
     */
    @PreAuthorize("hasAuthority('AUTHORITY_DELETE')")
    void remove(Long id);

    /**
     * 修改权限
     */
    @PreAuthorize("hasAuthority('AUTHORITY_SAVE')")
    void update(Long id, String authorityname, String authoritytype, String description, String displayref);

    /**
     * 分页列表
     */
    @PreAuthorize("hasAuthority('AUTHORITY_LIST')")
    Page<Authority> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter);

    /**
     * 根据菜单返回权限
     */
    List<Authority> findByMenu(Menu menu);

    /**
     * 根据角色返回关联的权限树
     */
    List<AuthTreeParent> authTreeForRole(Long role);
}
