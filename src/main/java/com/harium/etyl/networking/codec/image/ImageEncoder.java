package com.harium.etyl.networking.codec.image;


public interface ImageEncoder<T> {
    public static final String FORMAT_JPG = "jpg";
    public static final String FORMAT_PNG = "png";

    byte[] encode(T image);

    T decode(byte[] data);
}
