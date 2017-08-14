package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.AclOrg;
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
public interface AclOrgRepository extends JpaRepository<AclOrg, Long>, JpaSpecificationExecutor<AclOrg> {

    /**
     * 查询出所有父部门
     */
    @Query("from AclOrg where parentOrg is null")
    List<AclOrg> findAllParent();

    @Transactional
    @Modifying
    @Query("delete from AclOrg where id= :id")
    void remove(@Param("id") Long id);
}
