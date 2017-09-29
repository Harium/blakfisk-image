package com.harium.etyl.networking.codec.image.awt;

import com.harium.etyl.networking.codec.image.ImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BufferedImageEncoder implements ImageEncoder<BufferedImage> {

    public byte[] encode(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, FORMAT_PNG, baos);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] data = baos.toByteArray();
        return data;
    }

    public BufferedImage decode(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
