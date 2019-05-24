package com.threebow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

class Tile extends JButton {
	//Pixel size of each tile
	static final int SIZE = 48;

	//Parent board
	private Board board;

	//Tile info
	boolean mine = false;
	boolean flagged = false;
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

		//Set this button up
		addMouseListener(new MouseInputHandler());
		setFont(new Font("Consolas", Font.PLAIN, SIZE / 2));
		setMargin(new Insets(0, 0, 0, 0));
		setIcon(Resources.TILE);
	}

	void expose() {
		expose(true);
	}

	void expose(boolean end) {
		//Don't expose the tile if it's already flagged or exposed
		if(exposed || flagged) return;
		exposed = true;

		if(mine) {
			//Set the icon to a mine and end the game if we click on a mine
			setIcon(Resources.MINE);
			if(end) Main.game.end();
			return;
		} else {
			//Clear the icon and let the number show
			setIcon(null);
			setText(Integer.toString(getDisplayNumber()));
		}

		//Increment the uncovered amount of mines and check for a win
		board.uncovered++;
		board.checkWin();

		//Uncover adjacent zeroes
		if(getSurroundingMineCount() == 0) {
			ArrayList<Tile> tiles = getAdjacentTiles();
			for(Tile tile : tiles) {
				tile.expose();
			}
		}
	}

	//Returns an arraylist of the tiles around this tile that have mines
	ArrayList<Tile> getAdjacentTiles() {
		ArrayList<Tile> adjacent = new ArrayList<>();

		//Go through a 3x3 grid
		for(int lx = 0; lx < 3; lx++) {
			for(int ly = 0; ly < 3; ly++) {
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

	//Toggle if this tile is flagged
	void toggleFlag() {
		if(exposed) return;
		flagged = !flagged;
		setIcon(flagged ? Resources.FLAG : Resources.TILE);
	}

	//Set the icon to be flagged
	void flag() {
		setIcon(Resources.FLAG);
	}

	//Get the number that will be displayed here
	private int getDisplayNumber() {
		return mine ? -1 : getSurroundingMineCount();
	}
}