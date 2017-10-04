package com.harium.etyl.networking.codec.image.awt;

import com.harium.etyl.networking.codec.image.ImageEncoder;

import java.awt.*;
import java.awt.image.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class BufferedImageDirectEncoder implements ImageEncoder<BufferedImage> {

    private int width;
    private int height;

    public BufferedImageDirectEncoder(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public byte[] encode(BufferedImage image) {
        DataBuffer buffer = image.getData().getDataBuffer();
        if (buffer.getDataType() == DataBuffer.TYPE_BYTE) {
            return ((DataBufferByte) buffer).getData();
        } else if (buffer.getDataType() == DataBuffer.TYPE_INT) {
            int[] data = ((DataBufferInt) buffer).getData();

            ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);
            IntBuffer intBuffer = byteBuffer.asIntBuffer();
            intBuffer.put(data);

            return byteBuffer.array();
        }

        return null;
    }

    public BufferedImage decode(byte[] data) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        IntBuffer intBuf = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
        int[] array = new int[intBuf.remaining()];
        intBuf.get(array);

        image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferInt(array, array.length), new Point()));

        return image;
    }

}
