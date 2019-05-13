package com.threebow;

import javax.swing.JButton;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

class Tile extends JButton {
	private Board board;
	private MouseInputHandler handler;

	boolean mine = false;
	boolean flagged = false;
	private boolean exposed = false;
	private int surroundingMineCount = -1;

	//Coordinates of this tile
	private int x;
	private int y;

	//Pixel size of this tile
	private int w;
	private int h;

	//Constructor, sets the parent board and the tile's position in it
	Tile(Board parent, int posX, int posY, int sizeW, int sizeH) {
		board = parent;
		x = posX;
		y = posY;
		w = sizeW;
		h = sizeH;

		handler = new MouseInputHandler();
		addMouseListener(handler);
		setFont(new Font("Consolas", Font.PLAIN, 36));
		setIcon(Resources.scaleIcon(Resources.TILE, w, h));
	}

	void expose() {
		expose(true);
	}

	void expose(boolean end) {
		if(exposed || flagged) return;
		exposed = true;

		if(mine) {
			setIcon(Resources.scaleIcon(Resources.MINE, w, h));
			if(end) Main.game.end();
			return;
		} else {
			setIcon(null);
			setText(Integer.toString(getDisplayNumber()));
		}

		board.uncovered++;
		board.checkWin();

		if(getSurroundingMineCount() == 0) {
			ArrayList<Tile> tiles = getAdjacentTiles();
			for (Tile tile : tiles) {
				tile.expose();
			}
		}
	}

	//Returns an arraylist of the tiles around this tile that have mines
	ArrayList<Tile> getAdjacentTiles() {
		ArrayList<Tile> adjacent = new ArrayList<>();

		//Go through a 3x3 grid
		for (int lx = 0; lx < 3; lx++) {
			for (int ly = 0; ly < 3; ly++) {
				//Continue if it's the center of the 3x3 grid (this tile)
				if(lx == 1 && ly == 1) continue;

				//Get the tile at this position in the 3x3 grid, relative to the current tile
				Tile tile = board.getTile(x - 1 + lx, y - 1 + ly);

				//Add it to the arraylist if it's in bounds and is a mine
				if(tile != null) {
					adjacent.add(tile);
				}
			}
		}

		//Return the list of adjacent mines
		return adjacent;
	}

	//Returns an arraylist of the tiles around this tile that have mines
	private ArrayList<Tile> getAdjacentMines() {
		ArrayList<Tile> adjacent = getAdjacentTiles().stream().filter(t -> t.mine).collect(Collectors.toCollection(ArrayList::new));
		surroundingMineCount = adjacent.size();
		return adjacent;
	}

	int getSurroundingMineCount() {
		//Cache the adjacent mines if we haven't already
		if(surroundingMineCount == -1) {
			getAdjacentMines();
		}

		return surroundingMineCount;
	}

	void toggleFlag() {
		if(exposed) return;
		flagged = !flagged;
		setIcon(Resources.scaleIcon(flagged ? Resources.FLAG : Resources.TILE, w, h));
	}

	void flag() {
		setIcon(Resources.scaleIcon(Resources.FLAG, w, h));
	}

	private int getDisplayNumber() {
		return mine ? -1 : getSurroundingMineCount();
	}
}