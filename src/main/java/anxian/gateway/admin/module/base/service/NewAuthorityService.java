package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.model.NewAuthorityModel;
import anxian.gateway.admin.module.base.model.NewMenuModel;
import anxian.gateway.admin.module.base.repository.NewAuthorityRepository;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewAuthorityService {

    @Autowired
    private NewAuthorityRepository newAuthorityRepository;

    /**
     * 获取列表
     *
     * @param authorityName
     * @param pageable
     * @return
     */
    public ResponseMessage list(String authorityName, Pageable pageable) {
        Page<NewAuthority> newAuthorityPage = null;
        if (StringUtils.isEmpty(authorityName)) {
            newAuthorityPage = newAuthorityRepository.findAll(pageable);
        } else {
            newAuthorityPage = newAuthorityRepository.findByNameLike(authorityName, pageable);
        }

        List<NewAuthority> newAuthorities = newAuthorityPage.getContent();
        List<NewAuthorityModel> newAuthorityModels = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(newAuthorities)) {
            for (NewAuthority newAuthority : newAuthorities) {
                NewAuthorityModel newAuthorityModel = new NewAuthorityModel();
                newAuthorityModel.setId(newAuthority.getId());
                newAuthorityModel.setCode(newAuthority.getCode());
                newAuthorityModel.setName(newAuthority.getName());
                newAuthorityModel.setDescription(newAuthority.getDescription());
                newAuthorityModel.setIsValid(newAuthority.getIsValid());
                newAuthorityModels.add(newAuthorityModel);
            }
        }

        return ResponseMessage.success(new PageImpl<>(newAuthorityModels, pageable, newAuthorityPage.getTotalElements()));
    }

    /**
     * 根据id获取权限对象
     *
     * @param id
     * @return
     */
    public ResponseMessage getById(String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseMessage.error("权限ID为空！");
        }

        NewAuthority newAuthority = newAuthorityRepository.findOne(id);
        if (null == newAuthority) {
            return ResponseMessage.error("没有对应的权限！");
        }

        return ResponseMessage.success(newAuthority);
    }

    /**
     * 保存角色信息
     *
     * @param newAuthority
     * @return
     */
    public ResponseMessage save(NewAuthority newAuthority, String username) {

        if (null == newAuthority) {
            return ResponseMessage.error("保存菜单信息失败！");
        }

        String id = newAuthority.getId();
        NewAuthority newAuthorityResult = null;
        if (StringUtils.isEmpty(id)) {
            newAuthorityResult = new NewAuthority();
            newAuthorityResult.setCreator(username);
            newAuthorityResult.setCreateDateTime(LocalDateTime.now());
        } else {
            newAuthorityResult = newAuthorityRepository.findOne(id);
            newAuthorityResult.setUpdator(username);
            newAuthorityResult.setUpdateDateTime(LocalDateTime.now());
        }

        newAuthorityResult.setCode(newAuthority.getCode());
        newAuthorityResult.setName(newAuthority.getName());
        newAuthorityResult.setDescription(newAuthority.getDescription());
        newAuthorityResult.setIsValid(newAuthority.getIsValid());

        return ResponseMessage.success(newAuthorityRepository.save(newAuthorityResult));
    }
}
