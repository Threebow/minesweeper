package com.threebow;

import javax.swing.JButton;
import java.awt.*;
import java.util.ArrayList;

class Tile extends JButton {
	private Board board;
	private MouseInputHandler handler;

	boolean mine = false;
	private boolean exposed = false;
	private int surroundingMineCount = -1;

	//Coordinates of this tile
	private int x;
	private int y;

	//Constructor, sets the parent board and the tile's position in it
	Tile(Board parent, int posX, int posY) {
		board = parent;
		x = posX;
		y = posY;

		handler = new MouseInputHandler();
		addMouseListener(handler);
		setFont(new Font("Consolas", Font.PLAIN, 36));
	}

	void expose() {
		if(exposed) return;
		exposed = true;
		setText(Integer.toString(getDisplayNumber()));
	}

	//Returns an arraylist of the tiles around this tile that have mines
	ArrayList<Tile> getAdjacentMines() {
		ArrayList<Tile> adjacent = new ArrayList<>();

		//Go through a 3x3 grid
		for (int lx = 0; lx < 3; lx++) {
			for (int ly = 0; ly < 3; ly++) {
				//Continue if it's the center of the 3x3 grid (this tile)
				if (lx == 1 && ly == 1) continue;

				//Get the tile at this position in the 3x3 grid, relative to the current tile
				Tile tile = board.getTile(x - 1 + lx, y - 1 + ly);

				//Add it to the arraylist if it's in bounds and is a mine
				if (tile != null && tile.mine) {
					adjacent.add(tile);
				}
			}
		}

		//Cache the count
		surroundingMineCount = adjacent.size();

		//Return the list of adjacent mines
		return adjacent;
	}

	int getSurroundingMineCount() {
		//Cache the adjacent mines if we haven't already
		if (surroundingMineCount == -1) {
			getAdjacentMines();
		}

		return surroundingMineCount;
	}

	private int getDisplayNumber() {
		return mine ? -1 : getSurroundingMineCount();
	}
}