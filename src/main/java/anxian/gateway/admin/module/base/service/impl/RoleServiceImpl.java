package anxian.gateway.admin.module.base.service.impl;

import anxian.gateway.admin.module.base.domain.Role;
import anxian.gateway.admin.module.base.domain.RoleAuthority;
import anxian.gateway.admin.module.base.domain.UserRole;
import anxian.gateway.admin.module.base.repository.RoleAuthRepository;
import anxian.gateway.admin.module.base.repository.RoleRepository;
import anxian.gateway.admin.module.base.repository.RoleUserRepository;
import anxian.gateway.admin.module.base.service.RoleService;
import anxian.gateway.admin.module.security.UserContext;
import anxian.gateway.admin.module.security.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-13.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAuthRepository roleAuthRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;

    /**
     * 保存角色
     */
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * 根据ID返回角色
     */
    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }

    /**
     * 根据ID删除角色
     */
    @Override
    public void del(Long id) {
        Role role = roleRepository.findOne(id);

        List<RoleAuthority> byRole = roleAuthRepository.findByRole(id);
        roleAuthRepository.delete(byRole);

        List<UserRole> byRole1 = roleUserRepository.findByRole(role);
        roleUserRepository.delete(byRole1);

        roleRepository.delete(id);
    }

    /**
     * 返回所有角色
     */
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * 修改角色
     */
    @Override
    public void update(Long id, String name, String displayref, String description) {
        roleRepository.update(id, name, displayref, description);
    }

    /**
     * 分页列表
     */
    @Override
    public Page<Role> selectPageList(int pageNumber, int pageSize, Map<Object, Object> filter) {

        Specification<Role> spec = new Specification<Role>() {
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<>();

                if (!filter.isEmpty()) {
                    for (Map.Entry<Object, Object> entry : filter.entrySet()) {
                        if (entry.getValue() != null) {
                            if (!entry.getValue().equals("") && !entry.getValue().equals("null")) {
                                list.add(cb.like(root.get(entry.getKey().toString()).as(String.class), "%" + entry.getValue().toString() + "%"));

                            }
                        }

                    }
                }

                UserContext userContext = UserContextHelper.getUserContext();
                for (UserRole userRole : userContext.getUserRoles()) {
                    list.add(cb.notEqual(root.get("name").as(String.class), userRole.getRole().getName()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));

            }
        };

        Page<Role> rolePage = roleRepository.findAll(spec, buildPageRequest(pageNumber, pageSize, "auto"));

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
