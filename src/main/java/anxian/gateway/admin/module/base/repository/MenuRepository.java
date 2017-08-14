package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-19.
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    /**
     * 查询所有父菜单
     */
    @Query("from Menu where isParent = 1")
    List<Menu> findIsParent();

    /**
     * 根据父菜单id取得子菜单列表
     *
     * @param parentMenuId 父菜单id
     * @return 子菜单列表
     */
    @Query(value = "select *  from t_menu where parent_menu = :parentMenuId", nativeQuery = true)
    List<Menu> findByParentMenu(@Param("parentMenuId") long parentMenuId);

    @Transactional
    @Modifying
    @Query("delete from Menu where id = :id")
    void remove(@Param("id") Long id);
}