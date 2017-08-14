package anxian.gateway.admin.module.business.controller.order.utils;

public class EncoderHelper {

    public static Encoder buildEncoder() {
        Encoder mEncoder = new Encoder.Builder().setBackgroundColor(0xFFFFFFFF).setCodeColor(0x00000000)
                .setOutputBitmapPadding(0).setOutputBitmapWidth(180).setOutputBitmapHeight(60).build();
        return mEncoder;
    }

}