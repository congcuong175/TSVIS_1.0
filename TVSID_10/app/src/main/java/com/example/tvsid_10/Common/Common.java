package com.example.tvsid_10.Common;

import android.graphics.Bitmap;

import com.example.tvsid_10.Entity.SinhVien;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class Common {
    public static SinhVien sinhVien;
    public static List<Integer> predicts;
    public static ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        //convert bitmap to bytebuffer
        float IMAGE_MEAN = 127.5f;
        float IMAGE_STD = 127.5f;
        //reize 160*160
        bitmap = Bitmap.createScaledBitmap(bitmap, 160, 160, false);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 160 * 160 * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[160 * 160];
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;
        for (int i = 0; i < 160; i++) {
            for (int j = 0; j < 160; j++) {
                int input = intValues[pixel++];

                byteBuffer.putFloat((((input >> 16 & 0xFF) - IMAGE_MEAN) / IMAGE_STD));
                byteBuffer.putFloat((((input >> 8 & 0xFF) - IMAGE_MEAN) / IMAGE_STD));
                byteBuffer.putFloat((((input & 0xFF) - IMAGE_MEAN) / IMAGE_STD));
            }
        }
        return byteBuffer;

    }
}
