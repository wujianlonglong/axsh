package anxian.gateway.admin.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件操作类ß
 * Created by Jianghe on 15/12/10.
 */
public class FileUtil {

    /**
     * 把网络上的图片上传
     *
     * @param urlFilePath
     * @param imageFile
     */
    public static void urlFileUpload(String urlFilePath, File imageFile) {
        try {
            //new一个URL对象
            URL url = new URL(urlFilePath);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);

            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过本地上传控件上传文件
     *
     * @param stream
     */
    public static void saveFileFromInputStream(InputStream stream, File imageFile) {
        try {
            FileOutputStream fs = new FileOutputStream(imageFile);
            byte[] buffer = new byte[1024 * 1024];
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = stream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
                fs.flush();
            }
            fs.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回二进制封装得到数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
