package com.threebow;

import javax.swing.*;
import java.awt.*;

class Resources {
	//Create our various icons
	static ImageIcon TILE = scaledIcon("resource/Tile.png");
	static ImageIcon MINE = scaledIcon("resource/Mine.png");
	static ImageIcon FLAG = scaledIcon("resource/Flag.png");

	//Scale an ImageIcon to a new size
	static ImageIcon scaledIcon(String iconPath) {
		Image img = new ImageIcon(iconPath).getImage();
		return new ImageIcon(img.getScaledInstance(Tile.SIZE, Tile.SIZE, Image.SCALE_SMOOTH));
	}
}