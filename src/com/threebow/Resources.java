package com.threebow;

import javax.swing.*;
import java.awt.*;

class Resources {
	//Create our various icons
	static ImageIcon TILE = scaledIcon("resource/Tile.png");
	static ImageIcon MINE = scaledIcon("resource/Mine.png");
	static ImageIcon FLAG = scaledIcon("resource/Flag.png");
	static ImageIcon HOVERED = scaledIcon("resource/Hovered.png");
	static ImageIcon FLAG_HOVERED = scaledIcon("resource/FlagHovered.png");
	static ImageIcon UNCOVERED = scaledIcon("resource/Uncovered.png");
	static ImageIcon UNCOVERED_HOVERED = scaledIcon("resource/UncoveredHovered.png");

	//Scale an ImageIcon to a new size
	private static ImageIcon scaledIcon(String iconPath) {
		Image img = new ImageIcon(iconPath).getImage();
		return new ImageIcon(img.getScaledInstance(Tile.SIZE, Tile.SIZE, Image.SCALE_SMOOTH));
	}
}