package com.harium.blakfisk.codec.image.awt;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class BufferedImageDirectEncoder implements com.harium.blakfisk.codec.image.ImageEncoder<BufferedImage> {
	
	private int width;
	private int height;
	
	public BufferedImageDirectEncoder(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public byte[] encode(BufferedImage image) {
		DataBuffer buffer = image.getData().getDataBuffer();
		/*if(buffer.getDataType() == DataBuffer.TYPE_BYTE) {
			return ((DataBufferByte) buffer).getData();
		} else */
		if (buffer.getDataType() ==  DataBuffer.TYPE_INT) {
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
