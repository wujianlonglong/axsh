package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiangzhe on 15-11-13.
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 修改角色
     */
    @Modifying
    @Transactional
    @Query("update Role set name = :name,displayref = :displayref,description = :description where id=:id")
    void update(@Param("id") Long id, @Param("name") String name, @Param("displayref") String displayref, @Param("description") String description);
}
