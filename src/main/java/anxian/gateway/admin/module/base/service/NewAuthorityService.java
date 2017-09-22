package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import anxian.gateway.admin.module.base.repository.NewAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewAuthorityService {

    @Autowired
    private NewAuthorityRepository newAuthorityRepository;

    // TODO 权限维护、权限展示

    /**
     * 获取所有有效的权限列表
     *
     * @return
     */
    public List<NewAuthority> getAuthorities() {

        return newAuthorityRepository.findByEnabled(true);
    }
}
