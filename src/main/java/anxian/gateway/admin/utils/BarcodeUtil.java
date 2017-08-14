package anxian.gateway.admin.utils;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 商品条形码生成器
 * Created by JiangZhe on 16/2/6.
 */
public class BarcodeUtil {

    public static boolean generateBarCode(String codeOutPath, String codeString) {

        try {
            //Create the barcode bean
//            Code39Bean bean = new Code39Bean();
            Code128Bean bean = new Code128Bean();


//            final int dpi = 200;
            final int dpi = 150;
            bean.setCodeset(Code128Constants.CODESET_B);

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
            //width exactly one pixel
//            bean.setWideFactor(3);
            bean.doQuietZone(false);
            //Open output file
            File outputFile = new File(codeOutPath);
            OutputStream out = new FileOutputStream(outputFile);
            try {
                //Set up the canvas provider for monochrome JPEG output
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                        out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

                //Generate the barcode
                bean.generateBarcode(canvas, codeString);

                //Signal end of generation
                canvas.finish();
            } finally {
                out.close();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
