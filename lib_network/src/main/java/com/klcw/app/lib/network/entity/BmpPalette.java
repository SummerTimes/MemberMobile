package com.klcw.app.lib.network.entity;

/**
 * @author yp2
 * @date 2015-11-17
 * @description Bmp调色板
 */
public class BmpPalette {

	private byte[][] palettes;		//颜色索引映射表

	public byte[][] getPalettes() {
		return palettes;
	}

	public void setPalettes(byte[][] palettes) {
		this.palettes = palettes;
	}


}
