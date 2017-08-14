package client.api.www.feign;

import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.www.model.HotGoods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mac on 15/9/22.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("hotGoodses")
public interface HotGoodsFeign {

    /**
     * 新增热销好货
     *
     * @param hotGoods 热销好货
     * @return 热销好货
     */
//    @PreAuthorize("hasAuthority('HOTGOODS_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    HotGoods save(HotGoods hotGoods);

    /**
     * 修改热销好货
     *
     * @param hotGoods 热销好货
     * @return 热销好货
     */
//    @PreAuthorize("hasAuthority('HOTGOODS_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(HotGoods hotGoods);


    /**
     * 删除热销好货
     *
     * @param id 主键Id
     */
//    @PreAuthorize("hasAuthority('HOTGOODS_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

    /**
     * 分页查询热销好货
     *
     * @param searchCoditionModel 热销好货
     * @return 分页
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<HotGoods> search(SearchCoditionModel<HotGoods> searchCoditionModel);

}
