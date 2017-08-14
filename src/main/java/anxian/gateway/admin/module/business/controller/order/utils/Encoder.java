package anxian.gateway.admin.module.business.controller.order.utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * @author :   Yoojia.Chen (yoojia.chen@gmail.com)
 * @since 1.0
 * 将文本信息生成QRCode图片
 */
public final class Encoder {


    private final Builder mConfigBuilder;
    private final MultiFormatWriter mMultiFormatWriter;

    private Encoder(Builder configBuilder) {
        mConfigBuilder = configBuilder;
        mMultiFormatWriter = new MultiFormatWriter();
    }

    /**
     * 将文本信息生成二维码图片
     *
     * @param content 文本内容
     * @return Bitmap对象，如果生成失败，返回null。
     */
    public BufferedImage encodeQr(final String content) {
        return encode(content, mConfigBuilder.mOutputBitmapWidth, mConfigBuilder.mOutputBitmapHeight, BarcodeFormat.QR_CODE);
    }

    public BufferedImage encodeBar(final String content) {
        return encode(content, mConfigBuilder.mOutputBitmapWidth, mConfigBuilder.mOutputBitmapHeight, BarcodeFormat.CODE_128);
    }

    public static String formatBarCode(String code, String space) {
        //12345
        int length = code.length();
        int span = 4;
        int spanCount = length % 4 == 0 ? length / span : length / span + 1; //2
        if (spanCount > 1) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < spanCount; i++) {
                if (i == spanCount - 1) {
                    buffer.append(code.subSequence(i * span, length));
                } else {
                    buffer.append(code.subSequence(i * span, span * (i + 1)) + space);
                }
            }
            return buffer.toString();
        }
        return code;
    }

    /**
     * 将文本信息生成二维码图片，指定输出图片宽度和高度
     *
     * @param content 文本内容
     * @param width   输出图片宽度
     * @param height  输出图片高度
     * @param format
     * @return Bitmap对象，如果生成失败，返回null。
     */
    public BufferedImage encode(String content, int width, int height, BarcodeFormat format) {


        final Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        // 字符编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, mConfigBuilder.mCharset);
//        if (mConfigBuilder.mHintMargin >= 0){
//            // 输出图片外边距
//            hints.put(EncodeHintType.MARGIN, 2);
//        }
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix result;
        try {
            result = mMultiFormatWriter.encode(content, format, width, height, hints);
        } catch (Exception e) {
            return null;
        }


//        final int[] pixels = new int[width * height];
//        for (int y = 0; y < width; y++) {
//            int offset = y * width;
//            for (int x = 0; x < height; x++) {
//                try {
//                    pixels[offset + x] = result.get(y, x) ? mConfigBuilder.mCodeColor : mConfigBuilder.mBackgroundColor;
//                }catch (Exception e)
//                {
//                    System.out.println(x);
//                }
//
//            }
//        }
//
//
//        BufferedImage image = new BufferedImage(width, height,
//                BufferedImage.TYPE_INT_RGB);
//
//        image.setRGB(0,0,width,height,pixels,0,width);


        BufferedImage image = new BufferedImage(width, height,
                TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, result.get(x, y) ? mConfigBuilder.mCodeColor : mConfigBuilder.mBackgroundColor);
            }
        }


        int newheight = height + 30;

        BufferedImage newimage = new BufferedImage(width, newheight, BufferedImage.TYPE_INT_RGB);
        //得到图片对象
        Graphics2D g = (Graphics2D) newimage.getGraphics();

        Font font = new Font("宋体", Font.PLAIN, 13);
        g.setFont(font);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, newheight);
        g.drawImage(image, 0, 0, width, height, null);

        g.setColor(Color.black);

        FontRenderContext context = g.getFontRenderContext();
        content = formatBarCode(content, " ");
        Rectangle2D bounds = font.getStringBounds(content, context);

        double x = (width - bounds.getWidth()) / 2;
        double y = height + bounds.getHeight() + 8;
//        double ascent = -bounds.getY();
//        double baseY = y + ascent;
        g.drawString(content, (int) x, (int) y);


        g.dispose();
        newimage.flush();
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);


        return newimage;
    }

    public static class Builder {

        private int mBackgroundColor = 0xFFFFFFFF;
        private int mCodeColor = 0xFF000000;
        private String mCharset = "UTF-8";
        private int mOutputBitmapWidth;
        private int mOutputBitmapHeight;
        private int mHintMargin = -1;

        /**
         * 设置生成二维码图片的背景色
         *
         * @param backgroundColor 背景色，如 0xFFFFFFFF
         * @return Builder，用于链式调用
         */
        public Builder setBackgroundColor(int backgroundColor) {
            mBackgroundColor = backgroundColor;
            return this;
        }

        /**
         * 设置二维码的编码块颜色
         *
         * @param codeColor 编码块颜色，如 0xFF000000
         * @return Builder，用于链式调用
         */
        public Builder setCodeColor(int codeColor) {
            mCodeColor = codeColor;
            return this;
        }

        /**
         * 设置文本编码格式
         *
         * @param charset 字符编码格式
         * @return Builder，用于链式调用
         */
        public Builder setCharset(String charset) {

            mCharset = charset;
            return this;
        }

        /**
         * 设置输出图片的宽度
         *
         * @param outputBitmapWidth 宽度，单位：px
         * @return Builder，用于链式调用
         */
        public Builder setOutputBitmapWidth(int outputBitmapWidth) {
            mOutputBitmapWidth = outputBitmapWidth;
            return this;
        }

        /**
         * 设置输出图片的高度
         *
         * @param outputBitmapHeight 高度，单位：px
         * @return Builder，用于链式调用
         */
        public Builder setOutputBitmapHeight(int outputBitmapHeight) {
            mOutputBitmapHeight = outputBitmapHeight;
            return this;
        }

        /**
         * 设置输出二维码与图片边缘的距离
         *
         * @param hintMargin 距离值，正整数。
         */
        public Builder setOutputBitmapPadding(int hintMargin) {
            mHintMargin = hintMargin;
            return this;
        }

        /**
         * @return QRCode生成器对象
         */
        public Encoder build() {
            return new Encoder(this);
        }
    }
}
