package client.api.image;

import client.api.image.common.ImageType;
import client.api.image.util.AjaxResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jianghe on 15/12/8.
 */
@FeignClient("sjes-api-image")
@RequestMapping(value = "image")
public interface ImageApiClient {

    /**
     * 文件上传
     *
     * @param imageType     上传图片类型
     * @param multipartFile 文件
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    AjaxResponse uploadImage(HttpServletRequest request, @RequestParam("imageType") ImageType imageType, @RequestParam("file") MultipartFile multipartFile);


}
