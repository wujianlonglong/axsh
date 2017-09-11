package anxian.gateway.admin.module.business.controller.sale;


import client.api.sale.SecKillSyncApiClient;
import client.api.sale.model.secKill.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/admin/secKillSycn")
public class SecKillSyncController {
    private static final Logger log= LoggerFactory.getLogger(SecKillSyncController.class);

    @Autowired
    private SecKillSyncApiClient secKillSyncApiClient;


    /**
     * 保存促销秒杀信息
     *
     * @param secKillParamDTO 秒杀对象
     * @return 保存成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseMessage save(@RequestBody SecKillParamDTO secKillParamDTO){
        return secKillSyncApiClient.save(secKillParamDTO);
    }


    /**
     * 促销管理
     *
     * @param saleManageCondition 促销管理查询条件
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseMessage<SjesPage<SecKillMongo>> list(@RequestBody SaleManageCondition saleManageCondition){
        ResponseMessage<SjesPage<SecKillMongo>> result=secKillSyncApiClient.list(saleManageCondition);
        return result;
    }


    /**
     * 根据ID获取促销信息
     *
     * @param id 促销ID
     * @return 促销对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public SecKillMongo get(@PathVariable("id") String id){
        return secKillSyncApiClient.get(id);
    }

}
