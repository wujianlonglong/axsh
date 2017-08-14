package anxian.gateway.admin.module.business.controller.sale;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.sale.IntegalApiClient;
import client.api.sale.model.IntegalCondition;
import client.api.sale.model.ResponseMessage;
import client.api.sale.model.SaleConstant;
import client.api.sale.model.integal.Integal;
import client.api.sale.model.integal.IntegalViewModel;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jianghe on 16/1/25.
 */
@RestController
@RequestMapping("/integal")
public class IntegalController extends BaseController {
    @Autowired
    private IntegalApiClient integalApiClient;

    /**
     * 显示积分列表
     *
     * @return 积分类别
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public SjesPage<Integal> getList(IntegalCondition integalCondition, int page, int limit) {
        integalCondition.setPage(page - 1);
        integalCondition.setSize(limit);
        return integalApiClient.getList(integalCondition);
    }

    /**
     * 新增
     *
     * @param integalViewModel
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(IntegalViewModel integalViewModel) {
        AclUser user = UserContextHelper.getUser();
        try {
            integalViewModel.setUserId(user.getId());
            ResponseMessage<Integal> responseMessage = integalApiClient.save(integalViewModel);
            if (responseMessage.getCode() == SaleConstant.successCode) {
                return JsonMsg.success("保存成功");
            } else {
                return JsonMsg.failure(responseMessage.getCodeMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("保存失败");
        }
    }

    /**
     * 更新积分对象
     *
     * @param integalViewModel
     * @return
     */
    @RequestMapping(value = "/updateIntegal", method = RequestMethod.POST)
    public JsonMsg updateInteal(IntegalViewModel integalViewModel) {
        AclUser user = UserContextHelper.getUser();
        integalViewModel.setUserId(user.getId());
        ResponseMessage<Integal> integalResponseMessage = integalApiClient.updateInteal(integalViewModel);
        if (integalResponseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("修改成功");
        } else {
            return JsonMsg.failure("修改失败");
        }
    }

    /**
     * 根据积分换券ID返回积分换券对象
     *
     * @param integalId 积分换券ID
     * @return
     */
    @RequestMapping(value = "/{integalId}", method = RequestMethod.GET)
    public JsonMsg getIntegalById(@PathVariable("integalId") String integalId) {
        JsonMsg jsonMsg = new JsonMsg();
        ResponseMessage<Integal> integalById = integalApiClient.getIntegalById(integalId);
        if (integalById.getCode() == SaleConstant.successCode) {
            jsonMsg.setSuccess(true);
            jsonMsg.setData(integalById.getData());
        }
        return jsonMsg;
    }


    /**
     * 删除积分活动
     *
     * @param id 主键
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonMsg delete(@PathVariable("id") String id) {
        if (integalApiClient.delete(id)) {
            return JsonMsg.success("删除成功");
        }
        return JsonMsg.failure("删除失败");
    }

    @RequestMapping(value = "/stop/{id}", method = RequestMethod.GET)
    public JsonMsg stopIntegal(@PathVariable("id") String id) {
        Boolean result = integalApiClient.stopIntegal(id);
        if (result) {
            return JsonMsg.success("强停成功");
        }
        return JsonMsg.failure("强停失败");
    }


}
