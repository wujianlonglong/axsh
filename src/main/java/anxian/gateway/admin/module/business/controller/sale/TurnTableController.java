package anxian.gateway.admin.module.business.controller.sale;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.sale.TurnTableApiClient;
import client.api.sale.UserDrawingApiClient;
import client.api.sale.model.ResponseMessage;
import client.api.sale.model.SaleConstant;
import client.api.sale.model.turnTable.DrawPrize;
import client.api.sale.model.turnTable.Prize;
import client.api.sale.model.turnTable.TurnTable;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jianghe on 16/1/25.
 */
@RestController
@RequestMapping(value = "/turntable")
public class TurnTableController extends BaseController {
    @Autowired
    private TurnTableApiClient turnTableApiClient;

    @Autowired
    private UserDrawingApiClient userDrawingApiClient;

    /**
     * 查询
     *
     * @return 返回分页数据
     */
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public SjesPage<TurnTable> findTurnTables(String startDate, String endDate, Integer page, Integer limit) {
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            return turnTableApiClient.findTurnTables("", "", new PageRequest(page - 1, limit));
        } else {
            return turnTableApiClient.findTurnTables(startDate, endDate, new PageRequest(page - 1, limit));
        }

    }

    /**
     * 保存或更新大转盘
     *
     * @param turnTable
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public JsonMsg save(TurnTable turnTable) {
        try {
            AclUser user = UserContextHelper.getUser();
            if (StringUtils.isEmpty(turnTable.getId())) {
                turnTable.setCreater(user.getId());
            } else {
                turnTable.setUpdater(user.getId());
            }

            ResponseMessage<TurnTable> responseMessage = turnTableApiClient.save(turnTable);
            if (responseMessage.getCode() == SaleConstant.successCode) {
                return JsonMsg.success("操作成功");
            } else {
                return JsonMsg.failure("操作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("操作失败");
        }

    }

    /**
     * 查看大转盘明细
     *
     * @param id 大转盘ID
     * @return 指定ID的大转盘
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public JsonMsg get(@PathVariable("id") String id) {
        TurnTable turnTable = turnTableApiClient.get(id);

        List<DrawPrize> drawPrizeList = userDrawingApiClient.getDrawPrizeList(id);//大转盘中奖情况

        List<Prize> prizeList = turnTable.getPrizeList();
        for (Prize prize : prizeList) {
            int winNumber = 0;
            for (DrawPrize drawPrize : drawPrizeList) {
                if (prize.getName().equals(drawPrize.getPrizeName())) {//设置的奖品中奖数量+1
                    winNumber++;
                }
            }
            prize.setWinNumber(winNumber);
        }
        turnTable.setDrawPrizeList(prizeList);//设置中奖列表

        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(turnTable);
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 删除指定的大转盘
     *
     * @param id 大转盘ID
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonMsg del(@PathVariable("id") String id) {
        try {
            turnTableApiClient.del(id);
            return JsonMsg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("删除失败");
        }
    }

}
