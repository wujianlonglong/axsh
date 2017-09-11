package client.api.sale;


import client.api.sale.model.secKill.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="sjes-api-sale")
@RequestMapping(value = "/anxian/secKill/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface SecKillSyncApiClient {
    /**
     * 保存促销秒杀信息
     *
     * @param secKillParamDTO 秒杀对象
     * @return 保存成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    ResponseMessage save(@RequestBody SecKillParamDTO secKillParamDTO);


    /**
     * 促销管理
     *
     * @param saleManageCondition 促销管理查询条件
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    ResponseMessage<SjesPage<SecKillMongo>> list(@RequestBody SaleManageCondition saleManageCondition);

    /**
     * 根据ID获取促销信息
     *
     * @param id 促销ID
     * @return 促销对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    SecKillMongo get(@PathVariable("id") String id);

}
