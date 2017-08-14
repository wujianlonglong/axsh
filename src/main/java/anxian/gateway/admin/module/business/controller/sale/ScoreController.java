package anxian.gateway.admin.module.business.controller.sale;

import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.ProductFeign;
import client.api.sale.ScoreApiClient;
import client.api.sale.model.ResponseMessage;
import client.api.sale.model.ScoreAdmin;
import client.api.sale.model.ScoreItem;
import client.api.sale.model.ScoreSearch;
import client.api.user.utils.page.SjesPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyunan on 2017/5/15.
 */
@RestController
@RequestMapping(value = "/admin/scores")
public class ScoreController extends BaseController {

    @Override
    protected void initBinder(WebDataBinder binder) {
        super.initBinder(binder);
    }

    @Autowired
    private ScoreApiClient scoreApiClient;

    @Autowired
    private ProductFeign productFeign;

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping(value = "/searchList", method = RequestMethod.GET)
    public SjesPage<ScoreItem> pageList(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10")
                    int size,
            @RequestParam(name = "beginDate", required = false) String beginDateTime,
            @RequestParam(name = "endDate", required = false) String endDateTime,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "erpGoodsId", required = false) Long erpGoodsId) {
        ScoreSearch scoreSearch = new ScoreSearch();
//        scoreSearch.setStatus(status);
        scoreSearch.setPage(page);
        scoreSearch.setSize(size);
        if (status != null && status != 0) {
            scoreSearch.setStatus(status);
        }

        if (StringUtils.isNotEmpty(beginDateTime)) {
            scoreSearch.setBeginDateTime(LocalDateTime.parse(beginDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        if (StringUtils.isNotEmpty(endDateTime)) {
            scoreSearch.setEndDateTime(LocalDateTime.parse(endDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        scoreSearch.setErpGoodsId(erpGoodsId);
        if (size != 0 && page != 0) {
            Pageable pageable = new PageRequest(page, size);
            scoreSearch.setPage(page - 1);
            scoreSearch.setSize(size);
            SjesPage<ScoreItem> scoreItems = scoreApiClient.search(scoreSearch).getData();
            for (ScoreItem scoreItem : scoreItems.getContent()) {
                scoreItem.setActivityDate(scoreItem.getStartDate().toLocalDate().toString() + "至" + scoreItem.getEndDate().toLocalDate().toString());
            }
            return scoreItems;
        }
        return null;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMsg save(ScoreAdmin scoreAdmin) {
        try {

            ResponseMessage responseMessage = scoreApiClient.save(scoreAdmin);
            if (responseMessage.getCode() == 1) {
                if (scoreAdmin.getId() != null) {
                    return JsonMsg.success("修改积分兑换活动成功");
                } else {
                    return JsonMsg.success("新增积分兑换活动成功");
                }

            } else {
                if (scoreAdmin.getId() != null) {
                    return JsonMsg.failure("修改积分兑换活动失败");
                } else {
                    return JsonMsg.failure(responseMessage.getCodeMessage());
                }
            }
        } catch (Exception e) {

            return JsonMsg.failure("网络错误");
        }
    }

    @RequestMapping(value = "/stop/{id}", method = RequestMethod.DELETE)
    public JsonMsg manualStop(@PathVariable("id") String id) {
        try {
            ResponseMessage responseMessage = scoreApiClient.manualStop(id);
            if (responseMessage.getCode() == 1) {

                return JsonMsg.success("强停积分兑换活动成功");
            } else {
                return JsonMsg.failure("强停积分兑换活动失败");
            }
        } catch (Exception e) {

            return JsonMsg.failure("网络错误");
        }
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public JsonMsg getOne(@RequestParam("id") String id) {
        ResponseMessage<ScoreItem> item = scoreApiClient.getOne(id);
        List<Long> erpGoodsIds = new ArrayList<Long>();
        erpGoodsIds.add(item.getData().getErpGoodsId());
        item.getData().setProductList(productFeign.findByErpGoodsIdIn(erpGoodsIds));
        JsonMsg jsonMsg = new JsonMsg();
        if (item == null) {
            jsonMsg.setFailure(true);
            return jsonMsg;
        } else {
            jsonMsg.setSuccess(true);
            jsonMsg.setData(item.getData());
            return jsonMsg;
        }
    }


}
