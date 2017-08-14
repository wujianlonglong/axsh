package anxian.gateway.admin.module.base.service;


import anxian.gateway.admin.module.base.domain.Menu;
import anxian.gateway.admin.module.base.model.MenuJson;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-20.
 */
public interface MenuService {

    Menu findById(Long id);

    @PreAuthorize("hasAuthority('MENU_DELETE')")
    void del(Long id);

    @PreAuthorize("hasAuthority('MENU_SAVE')")
    Menu save(Menu menu);

    /**
     * 返回菜单树
     */
    List<MenuJson> loadTree();

    /**
     * 分页列表
     */
    @PreAuthorize("hasAuthority('MENU_LIST')")
    Page<Menu> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter);
}
