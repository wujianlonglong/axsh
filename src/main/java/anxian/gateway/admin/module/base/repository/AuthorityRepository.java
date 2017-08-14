package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.Authority;
import anxian.gateway.admin.module.base.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-18.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {

    /**
     * 修改权限资源
     */
    @Modifying
    @Transactional
    @Query("update Authority set authorityname = :authorityname,authoritytype = :authoritytype,description = :description,displayref = :displayref where id = :id")
    void update(@Param("id") Long id, @Param("authorityname") String authorityname, @Param("authoritytype") String authoritytype, @Param("description") String description, @Param("displayref") String displayref);

    /**
     * 根据菜单查询出权限资源
     */
    @Query("from Authority where menu = :menu")
    List<Authority> findByMenu(@Param("menu") Menu menu);
}
