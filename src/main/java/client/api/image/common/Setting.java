package client.api.image.common;

/**
 * 系统设置
 * Created by mac on 15/9/6.
 */
public class Setting {

    /**
     * 上传文件最大限制
     */
    public static final int UPLOAD_MAX_SIZE = 10;

    /**
     * 图片扩展符
     */
    public static final String[] UPLOAD_EXTENSIONS = {"jpg", "jpeg", "bmp", "gif", "png"};

    /**
     * 产品图片上传路径
     */
    public static final String PRODUCT_IMAGE_UPLOAD_PATH = "/image/";

    /**
     * 头像图片上传路径
     */
    public static final String PORTRAIT_IMAGE_UPLOAD_PATH = "/portrait/";


}
