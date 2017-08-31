package anxian.gateway.admin.module.common.controller.anxian;

import client.api.image.common.Setting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 负责文件操作的控制器
 * Created by Jianghe on 15/12/11.
 */
@RestController
@RequestMapping("/anxian/fileOperation")
public class AnxianFileOperationController {


    @Value("${picture.application.address}")
    private String pictureApplicationAddress;

    @Value("${picture.upload.path}")
    private String pictureUploadPath;

    /**
     * 上传操作
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object upload(HttpServletRequest request, HttpServletResponse response) {

        Map map = new HashMap<>();//存放操作结果,返回到前端

        //把request请求进行升级，request有的，它都有
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        response.setHeader("X-Frame-Options", "SAMEORIGIN");//添加了文件上传后跨域问题解决办法

        //获得上传文件的名称
        Iterator<String> iter = multiRequest.getFileNames();

        while (iter.hasNext()) {
            //包装过的文件流
            MultipartFile file = multiRequest.getFile(iter.next());

            //这里要进行判断，即使是空值，没有上传内容，file都是有值（空值）的，而文件流大小要大于0才是有上传的东西
            if (file.getSize() > 0) {
                //获得上传文件原始名
                String imagename = file.getOriginalFilename();
                //算出后缀名
                String ext = imagename.substring(imagename.lastIndexOf("."));
                //对文件类型进行判断，这个操作也可以在前台进行处理，在前台进行处理比较好，前后台都进行处理最稳妥
                List<String> fileTypes = new ArrayList<>();
                fileTypes.add(".jpg");
                fileTypes.add(".jpeg");
                fileTypes.add(".bmp");
                fileTypes.add(".gif");
                fileTypes.add(".png");

                //是图片再进行处理
                if (fileTypes.contains(ext.toLowerCase())) {
                    //文件名为：image + 系统时间 + 后缀
                    String fileName = "image" + System.currentTimeMillis() + ext;

                    LocalDateTime timePoint = LocalDateTime.now();

                    String imageLocalPath = Setting.PRODUCT_IMAGE_UPLOAD_PATH + timePoint.getYear() + timePoint.getMonth().getValue() + "/" + timePoint.getDayOfMonth() + "/" + fileName;

                    //生成的图片路径是:file://${HOME}/sjes/sjes-app-admin/upload/image/201512/17/image1450317671943.jpg
                    String imageUploadPath = pictureUploadPath.substring(pictureUploadPath.indexOf("//") + 2) + imageLocalPath;

                    //new一个文件对象用来保存图片
                    File imageFile = new File(imageUploadPath);
                    if (!imageFile.getParentFile().exists()) {
                        imageFile.getParentFile().mkdirs();
                    }

                    try {
                        file.transferTo(imageFile);

                        map.put("success", true);
                        map.put("img", pictureApplicationAddress + imageLocalPath);
                    } catch (IOException e) {
                        return null;
                    }

                }
            }

        }
        return map;
    }
}
