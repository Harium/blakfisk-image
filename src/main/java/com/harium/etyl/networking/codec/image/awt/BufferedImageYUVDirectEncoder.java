package com.harium.etyl.networking.codec.image.awt;

import com.harium.etyl.networking.codec.image.CommonImageEncoder;
import com.harium.etyl.networking.codec.image.ImageEncoder;
import com.harium.etyl.networking.util.compression.ImageUtils;

import java.awt.*;
import java.awt.image.*;

public class BufferedImageYUVDirectEncoder extends CommonImageEncoder implements ImageEncoder<BufferedImage> {

    public BufferedImageYUVDirectEncoder(int width, int height) {
        super(width, height);
    }

    public byte[] encode(BufferedImage image) {
        DataBuffer buffer = image.getData().getDataBuffer();
        if (buffer.getDataType() == DataBuffer.TYPE_BYTE) {
            return ((DataBufferByte) buffer).getData();
        } else if (buffer.getDataType() == DataBuffer.TYPE_INT) {
            int[] data = ((DataBufferInt) buffer).getData();

            //Additional step
            byte[] yuv = ImageUtils.convertRGBtoYUV420(width, height, data);
            return yuv;
        }

        return null;
    }

    public BufferedImage decode(byte[] data) {
        int[] array = ImageUtils.convertYUVtoARGB(width, height, data);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferInt(array, array.length), new Point()));

        return image;
    }

}
