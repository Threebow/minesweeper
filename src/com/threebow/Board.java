package com.threebow;

import javax.swing.JFrame;
import java.awt.*;
import java.util.Random;

class Board extends JFrame {
	private Random random = new Random();
	private Tile[][] tiles;

	//Size of the board
	private int w;
	private int h;

	//Creates a new board of a certain size
	Board(int width, int height) {
		w = width;
		h = height;

		//Set JFrame options
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(w, h));
		setPreferredSize(new Dimension(800, 800));
	}

	//Returns a tile at a position if it exists
	Tile getTile(int x, int y) {
		if(x < 0 || x > w - 1) return null;
		if(y < 0 || y > h - 1) return null;
		return tiles[x][y];
	}

	//Generates the board randomly
	void generate(int mines) {
		//Initializes an empty two-dimensional array of tiles with the size of the board
		tiles = new Tile[w][h];

		//Goes through the board and generates a new tile for each one
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Tile tile = new Tile(this, x, y);
				add(tile);
				tiles[x][y] = tile;
			}
		}

		//Adds the specified amount of mines to the board, picking them at random
		while (mines > 0) {
			Tile tile = getTile(random.nextInt(w), random.nextInt(h));

			if(!tile.mine) {
				tile.mine = true;
				mines--;
			}
		}

		pack();
		setVisible(true);
	}

	void printGrid() {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Tile tile = tiles[x][y];
				if(tile.mine) System.out.print("M ");
				else System.out.print(tile.getSurroundingMineCount() + " ");
			}
			System.out.println();
		}
	}
}
