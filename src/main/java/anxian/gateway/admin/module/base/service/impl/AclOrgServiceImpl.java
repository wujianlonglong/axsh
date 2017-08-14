package anxian.gateway.admin.module.base.service.impl;

import anxian.gateway.admin.module.base.domain.AclOrg;
import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.base.repository.AclOrgRepository;
import anxian.gateway.admin.module.base.repository.AclUserRepository;
import anxian.gateway.admin.module.base.service.AclOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-16.
 */
@Service
public class AclOrgServiceImpl implements AclOrgService {

    @Autowired
    private AclOrgRepository aclOrgRepository;

    @Autowired
    private AclUserRepository aclUserRepository;

    /**
     * 根据ID查询出部门
     */
    @Override
    public AclOrg findById(Long id) {
        return aclOrgRepository.findOne(id);
    }

    /**
     * 查询出所有部门
     */
    @Override
    public List<AclOrg> findAll() {
        return aclOrgRepository.findAll();
    }

    /**
     * 查询处所有父部门
     */
    @Override
    public List<AclOrg> findAllParent() {
        return aclOrgRepository.findAllParent();
    }

    /**
     * 保存部门
     */
    @Override
    public AclOrg save(AclOrg aclOrg) {
        return aclOrgRepository.save(aclOrg);
    }

    /**
     * 根据ID删除部门
     */
    @Override
    public void del(Long id) {

        List<AclUser> aclUserByOrg = aclUserRepository.getAclUserByOrg(id);
        if (!CollectionUtils.isEmpty(aclUserByOrg)) {
            for (AclUser aclUser : aclUserByOrg) {
                aclUser.setOrg(null);
                aclUserRepository.save(aclUser);
            }
        }

        aclOrgRepository.remove(id);
    }

    /**
     * 分页列表
     */
    @Override
    public Page<AclOrg> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter) {

        Specification<AclOrg> spec = new Specification<AclOrg>() {
            public Predicate toPredicate(Root<AclOrg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //组装查询条件
                if (!filter.isEmpty()) {
                    for (Map.Entry<Object, Object> entry : filter.entrySet()) {
                        if (entry.getValue() != null) {
                            if (!entry.getValue().equals("") && !entry.getValue().equals("null")) {
                                Predicate p = cb.like(root.get(entry.getKey().toString()).as(String.class), "%" + entry.getValue().toString() + "%");
                                query.where(cb.and(p));
                            }
                        }

                    }
                }


                return query.getRestriction();
            }
        };

        Page<AclOrg> rolePage = aclOrgRepository.findAll(spec, buildPageRequest(pageNumber, pageSize, "auto"));

        return rolePage;
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "id");
        } else {
            sort = new Sort(Sort.Direction.ASC, sortType);
        }

        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }
}
