package anxian.gateway.admin.module.business.service;

import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.user.MemberApplyFeign;
import client.api.user.domain.MemberApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qinhailong on 16-11-7.
 */
@Service
public class MemberApplyService {

    @Autowired
    private MemberApplyFeign memberApplyFeign;


    /**
     * 分页查询会员申请列表
     *
     * @param memberApply 分页查询条件
     * @return 分页列表
     */
    public PageModel<MemberApply> search(MemberApply memberApply, int page, int limit) {
        SearchCoditionModel<MemberApply> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(memberApply);
        return memberApplyFeign.search(searchCoditionModel);
    }

    /**
     * 根据指定id查询会员申请
     *
     * @param id
     * @return
     */
    public MemberApply findById(Long id) {
        return memberApplyFeign.findById(id);
    }

    /**
     * 更新会员申请信息
     *
     * @param memberApply 更新会员申请信息
     */
    public int update(MemberApply memberApply) {
        return memberApplyFeign.update(memberApply);
    }
}
