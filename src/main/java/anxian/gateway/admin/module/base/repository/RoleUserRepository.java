package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.base.domain.Role;
import anxian.gateway.admin.module.base.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-19.
 */
public interface RoleUserRepository extends JpaRepository<UserRole, Long> {

    /**
     * 根据角色查询出关联记录
     */
    @Query("from UserRole where role = :role")
    List<UserRole> findByRole(@Param("role") Role role);

    /**
     * 根据用户查询出关联记录
     */
    @Query("from UserRole where securityuser = :securityuser")
    List<UserRole> findByUser(@Param("securityuser") AclUser securityuser);

    /**
     * 根据角色和用户删除关联记录
     */
    @Modifying
    @Transactional
    @Query("delete from UserRole as ur where  ur.role = :role and ur.securityuser = :securityuser")
    void removeByRoleAndUser(@Param("role") Role role, @Param("securityuser") AclUser securityuser);
}
