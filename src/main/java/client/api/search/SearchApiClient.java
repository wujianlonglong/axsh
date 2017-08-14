package client.api.search;

import client.api.item.domain.Product;
import client.api.item.model.PageModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 搜索服务
 * Created by kimiyu on 16/3/9.
 */
@FeignClient("sjes-elasticsearch")
@RequestMapping("tinysearchs")
public interface SearchApiClient {

    /**
     * 根据相关条件获取商品
     *
     * @param id       编号
     * @param keyword  关键字
     * @param saleType 促销类型
     * @param page     页面
     * @param size     页面大小
     * @return
     */
    @RequestMapping(value = "searchs", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Product> getProductsByIdOrName(@RequestParam("id") Long id,
                                             @RequestParam("keyword") String keyword,
                                             @RequestParam("saleType") Integer saleType,
                                             @RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size);

    /**
     * 根据相关条件获取商品
     *
     * @param id      编号
     * @param keyword 关键字
     * @param page    页面
     * @param size    页面大小
     * @return
     */
    @RequestMapping(value = "listProducts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel listProducts(@RequestParam("id") Long id, @RequestParam("keyword") String keyword, @RequestParam("page") Integer page, @RequestParam("size") Integer size);
}
