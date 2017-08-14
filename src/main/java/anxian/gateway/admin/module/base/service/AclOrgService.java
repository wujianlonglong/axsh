package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.AclOrg;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-16.
 */
public interface AclOrgService {

    /**
     * 根据ID查询出部门
     */
    AclOrg findById(Long id);

    /**
     * 查询出所有部门
     */
    List<AclOrg> findAll();

    /**
     * 查询处所有父部门
     */
    List<AclOrg> findAllParent();

    /**
     * 保存部门
     */
    @PreAuthorize("hasAuthority('ORG_SAVE')")
    AclOrg save(AclOrg aclOrg);

    /**
     * 根据ID删除部门
     */
    @PreAuthorize("hasAuthority('ORG_DELETE')")
    void del(Long id);

    /**
     * 分页列表
     */
    @PreAuthorize("hasAuthority('ORG_LIST')")
    Page<AclOrg> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter);
}
