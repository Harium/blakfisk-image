package com.harium.etyl.networking.codec.image;


public interface ImageEncoder<T> {
    String FORMAT_JPG = "jpg";
    String FORMAT_PNG = "png";

    byte[] encode(T image);

    T decode(byte[] data);
}
