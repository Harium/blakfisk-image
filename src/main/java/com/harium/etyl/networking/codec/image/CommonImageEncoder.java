package com.harium.etyl.networking.codec.image;

public class CommonImageEncoder {

    protected int width;
    protected int height;

    public CommonImageEncoder(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
