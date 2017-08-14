package client.api.www.feign;

import client.api.constants.Constants;
import client.api.www.model.FloorKeyword;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by mac on 15/9/23.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("floorKeywords")
public interface FloorKeywordFeign {


    /**
     * 新增楼层关键词
     *
     * @param floorKeyword 楼层关键词
     * @return 楼层关键词
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    FloorKeyword save(FloorKeyword floorKeyword);

    /**
     * 更新楼层关键词
     *
     * @param floorKeyword 楼层关键字
     * @return 修改数目
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(FloorKeyword floorKeyword);

    /**
     * 根据id删除指定的关键词
     *
     * @param id 主键
     */
//    @PreAuthorize("hasAuthority('FLOOR_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);
}
