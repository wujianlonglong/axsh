package anxian.gateway.admin.module.business.controller.anxian.app;

import client.api.image.common.Setting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by wangdinglan on 2017/10/13
 */
@Controller
public class AnxianApkController {
    @Value("${picture.application.address}")
    private String pictureApplicationAddress;

    @RequestMapping(value = "admin/apkDownload", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws IOException {

        BufferedInputStream dis = null;
        BufferedOutputStream fos = null;

        String urlString = pictureApplicationAddress + Setting.APK_DOWNLOAD_UPLOAD_PATH + "anxian.apk";
        String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);

        try {

            URL url = new URL(urlString);
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(url.openConnection().getContentLength()));

            dis = new BufferedInputStream(url.openStream());
            fos = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = dis.read(buff, 0, buff.length))) {
                fos.write(buff, 0, bytesRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dis != null)
                dis.close();
            if (fos != null)
                fos.close();
        }
    }
}
