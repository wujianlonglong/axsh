package client.api.anxian.app;

import client.api.app.floor.model.EntryIconModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wangdinglan on 2017/09/14
 */
@FeignClient("sjes-api-app")
@RequestMapping("/anxian/appMenus")
public interface AnXianAppMenuFeign {
    /**
     * 楼层列表
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    List<EntryIconModel> list();

    /**
     * 根据id得到指定的 EntryIconModel
     * @param id 主键id
     * @return EntryIconModel
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    EntryIconModel getEntryIconModel(@PathVariable("id") Long id);

    /**
     * 更新菜单
     * @param entryIconModel
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateEntryIcon(@RequestBody EntryIconModel entryIconModel);

    /**
     * 新增菜单
     * @param entryIconModel
     */
    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void addEntryIcon(@RequestBody EntryIconModel entryIconModel);

    /**
     * 删除菜单
     * @param id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    void deleteEntryIcon(@PathVariable("id") Long id);

}
