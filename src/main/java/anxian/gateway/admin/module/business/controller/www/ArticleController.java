package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.www.feign.ArticleFeign;
import client.api.www.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 15-12-31.
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleFeign articleFeign;


    /**
     * 新增公告
     *
     * @param article 公告
     * @return 公告
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(Article article) {
        articleFeign.save(article);
        return JsonMsg.success("保存成功");
    }

    /**
     * 根据id查询公告
     *
     * @param id 主键id
     * @return 公告
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg findOne(@PathVariable("id") Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(articleFeign.findOne(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 更新公告
     *
     * @param article 公告
     * @return 更新数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(Article article) {
        articleFeign.update(article);
        return JsonMsg.success("更新成功");
    }

    /**
     * 分页查询公告列表
     *
     * @param article 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public PageModel<Article> search(Article article, int page, int limit) {
        SearchCoditionModel<Article> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setSearchCodition(article);
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        return articleFeign.search(searchCoditionModel);
    }

}
