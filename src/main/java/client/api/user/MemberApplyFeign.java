package client.api.user;

import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.user.domain.MemberApply;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by qinhailong on 16-11-7.
 */
@FeignClient("sjes-api-user")
@RequestMapping("memberApply")
public interface MemberApplyFeign {

    /**
     * 分页查询会员申请列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<MemberApply> search(@RequestBody SearchCoditionModel<MemberApply> searchCoditionModel);

    /**
     * 根据指定id查询会员申请
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    MemberApply findById(@RequestParam("id") Long id);

    /**
     * 更新会员申请信息
     *
     * @param memberApply 更新会员申请信息
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(@RequestBody MemberApply memberApply);
}
