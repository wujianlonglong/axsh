package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.Authority;
import anxian.gateway.admin.module.base.domain.Role;
import anxian.gateway.admin.module.base.domain.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-19.
 */
public interface RoleAuthRepository extends JpaRepository<RoleAuthority, Long> {

    /**
     * 根据角色查询出相关的权限
     */
    @Query("from RoleAuthority where role.id = :role")
    List<RoleAuthority> findByRole(@Param("role") Long role);

    /**
     * 删除角色和权限的关联记录，根据角色和权限
     */
    @Modifying
    @Transactional
    @Query("delete from RoleAuthority as ra where ra.role = :role and ra.authority = :authority")
    void removeByRoleAndAuth(@Param("role") Role role, @Param("authority") Authority authority);

    /**
     * 删除角色和权限的关联记录，根据权限
     */
    @Modifying
    @Transactional
    @Query("delete from RoleAuthority as ra where  ra.authority = :authority")
    void removeByAuth(@Param("authority") Authority authority);
}
