package client.api.fresh.feign;

import client.api.constants.Constants;
import client.api.fresh.model.Supermarket;
import client.api.fresh.model.SupermarketModel;
import client.api.fresh.model.Supermarkets;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by mac on 15/9/24.
 */
@FeignClient(Constants.SJES_API_FRESH)
@RequestMapping("supermarkets")
public interface SupermarketFeign {

    /**
     * 新增超市页
     *
     * @param supermarket 超市页信息
     * @return 超市页信息
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Supermarket save(Supermarket supermarket);

    /**
     * 更新超市页
     *
     * @param supermarket 超市页
     * @return 修改数目
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(Supermarket supermarket);

    /**
     * 根据指定主键id删除
     *
     * @param id 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

    /**
     * 超市页列表
     *
     * @return 超市业列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Supermarkets> list();

    /**
     * 根据主键id查询SupermarketModel
     *
     * @param id 主键id
     * @return SupermarketModel
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    SupermarketModel get(@PathVariable("id") Long id);
}
