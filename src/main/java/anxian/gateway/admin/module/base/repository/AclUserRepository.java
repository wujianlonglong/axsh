package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.AclUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-16.
 */
public interface AclUserRepository extends JpaRepository<AclUser, Long>, JpaSpecificationExecutor<AclUser> {

    /**
     * 根据用户名查询用户
     */
    @Query("from AclUser where username = :username")
    AclUser findByName(@Param("username") String username);

    /**
     * 根据角色和部门查询出此部门在这个角色中的所有用户
     */
    @Query("from AclUser u where u.id in (SELECT a.id FROM AclUser a,AclOrg b,UserRole c where a.org.id = b.id and a.id = c.securityuser.id and b.id = :orgId and c.role.id = :roleId ) and u.org.id = :orgId and u.userMgrType != :user_mgr_type")
    List<AclUser> getAclUserByRoleAndOrg(@Param("roleId") Long roleId, @Param("orgId") Long orgId, @Param("user_mgr_type") Integer user_mgr_type);

    /**
     * 根据角色和部门查询出此部门不在这个角色中的所有用户
     */
    @Query("from AclUser u where u.id not in (SELECT a.id FROM AclUser a,AclOrg b,UserRole c where a.org.id = b.id and a.id = c.securityuser.id and b.id = :orgId and c.role.id = :roleId ) and u.org.id = :orgId and u.userMgrType != :user_mgr_type")
    List<AclUser> getNotInAclUserByRoleAndOrg(@Param("roleId") Long roleId, @Param("orgId") Long orgId, @Param("user_mgr_type") Integer user_mgr_type);

    @Query("from AclUser where org.id = :orgId")
    List<AclUser> getAclUserByOrg(@Param("orgId") Long orgId);

    @Transactional
    @Modifying
    @Query("update AclUser set password = :password where id = :id ")
    void updatePwd(@Param("password") String password, @Param("id") Long id);

    @Query("from AclUser where description = :jobNum")
    List<AclUser> getAclUserByJobNum(@Param("jobNum") String jobNum);

   /* @Modifying
    @Query("update AclUser set username = :username,password = :password,expiredDate = :expiredDate,fullName = :fullName,org = :org,mobile = :mobile,userMgrType = :userMgrType,description = :description where id=:id")
    void update(@Param("id") Long id, @Param("username") String username, @Param("password") String password, @Param("expiredDate") String expiredDate, @Param("fullName") String fullName, @Param("org") String org, @Param("mobile") String mobile, @Param("userMgrType") String userMgrType, @Param("description") String description);
*/
}