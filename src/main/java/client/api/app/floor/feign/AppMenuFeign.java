package client.api.app.floor.feign;

import client.api.app.floor.model.EntryIconModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by byinbo on 2016/12/6.
 */
@FeignClient("sjes-api-app")
@RequestMapping("appMenus")
public interface AppMenuFeign {

    /**
     * 楼层列表
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    List<EntryIconModel> list();

    /**
     * 根据id得到指定的 EntryIconModel
     *
     * @param id 主键id
     * @return EntryIconModel
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    EntryIconModel getEntryIconModel(@PathVariable("id") Long id);

    /**
     * 更新菜单
     *
     * @param entryIconModel
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateEntryIcon(@RequestBody EntryIconModel entryIconModel);

}
