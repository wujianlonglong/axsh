package client.api.www.feign;

import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.www.model.Article;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mac on 15/9/17.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("articles")
public interface ArticleFeign {

    /**
     * 新增公告
     *
     * @param article 公告
     * @return 公告
     */
//    @PreAuthorize("hasAuthority('ARTICLE_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Article save(Article article);

    /**
     * 根据id查询公告
     *
     * @param id 主键id
     * @return 公告
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Article findOne(@PathVariable("id") Long id);

    /**
     * 更新公告
     *
     * @param article 公告
     * @return 更新数目
     */
//    @PreAuthorize("hasAuthority('ARTICLE_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(Article article);

    /**
     * 分页查询公告列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Article> search(SearchCoditionModel<Article> searchCoditionModel);

    /**
     * 根据状态查询公告列表
     *
     * @return 公告列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Article> list();
}
