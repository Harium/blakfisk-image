package com.harium.etyl.networking.codec.image;

import com.harium.etyl.networking.util.compression.ImageUtils;

public class YUV420Encoder extends CommonImageEncoder implements ImageEncoder<int[]> {

    public YUV420Encoder(int width, int height) {
        super(width, height);
    }

    @Override
    public byte[] encode(int[] data) {
        byte[] yuv = ImageUtils.convertRGBtoYUV420(width, height, data);
        return yuv;
    }

    @Override
    public int[] decode(byte[] buffer) {
        int[] rgb = ImageUtils.convertYUVtoARGB(width, height, buffer);
        return rgb;
    }
}
