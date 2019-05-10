package com.threebow;

import javax.swing.*;
import java.awt.*;

class Resources {
	static ImageIcon TILE = new ImageIcon("resource/Tile.png");
	static ImageIcon MINE = new ImageIcon("resource/Mine.png");
	static ImageIcon FLAG = new ImageIcon("resource/Flag.png");

	static ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
}