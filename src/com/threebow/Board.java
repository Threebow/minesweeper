package com.threebow;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Board extends JFrame {
	private Random random = new Random();
	private Tile[][] tiles;

	//Size of the board
	private int w;
	private int h;

	//UI
	private JPanel main;
	Toolbar toolbar;

	//How many mines are on this board
	int mines;
	int uncovered;
	int flagCount;

	//Is the board prepared for a zero to be uncovered
	boolean isBuffered;

	//Has the game ended
	boolean gameOver;

	//What difficulty is currently being ran
	Difficulty difficulty;

	//Creates a new board of a certain size
	Board() {
		//Set JFrame options
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Minesweeper");

		//Use a box layout so we can put a toolbar on top
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		//Create the toolbar that we use
		toolbar = new Toolbar();
		add(toolbar);

		//Actual layout for the game
		main = new JPanel();
		add(main);
	}

	//Returns a tile at a position if it exists
	Tile getTile(int x, int y) {
		if(x < 0 || x > w - 1) return null;
		if(y < 0 || y > h - 1) return null;
		return tiles[x][y];
	}

	//Allows us to generate directly from a difficulty, bit more convenient
	void generate(Difficulty difficulty) {
		this.difficulty = difficulty;
		generate(difficulty.rows, difficulty.columns, difficulty.mines);
	}

	//Generates the board
	private void generate(int width, int height, int mineCount) {
		//Set the width and height and mines, they are swapped here for ease of looping.
		w = width;
		h = height;
		uncovered = 0;
		flagCount = 0;
		gameOver = false;

		//Set the main panel size, dimension here needs to reverse because we loop the other way everywhere
		main.setLayout(new GridLayout(w, h));
		main.setPreferredSize(new Dimension(h * Tile.SIZE, w * Tile.SIZE));
		pack();

		//Center the panel on the screen
		setLocationRelativeTo(null);

		//Initializes an empty two-dimensional array of tiles with the size of the board
		tiles = new Tile[w][h];

		//Goes through the board and generates a new tile for each one
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				Tile tile = new Tile(this, x, y);
				main.add(tile);
				tiles[x][y] = tile;
			}
		}

		//Set mine data
		isBuffered = true;
		mines = mineCount;
		toolbar.setMineCount(mines);

		setVisible(true);
	}

	void generateMines(Tile clicked) {
		int mineCount = mines;

		//Adds the specified amount of mines to the board, picking them at random
		while(mineCount > 0) {
			//Generate a random position
			int x = random.nextInt(w);
			int y = random.nextInt(h);

			//If it's within the clicked tile's radius then skip over this position
			if(clicked.x >= x - 1 && clicked.x <= x + 1
				&& clicked.y >= y - 1 && clicked.y <= y + 1) {
				continue;
			}

			//Get the tile at this position
			Tile tile = getTile(x, y);

			//Turn it into a tile if it isnt already
			if(!tile.mine) {
				tile.mine = true;
				mineCount--;
			}
		}

		isBuffered = false;
		toolbar.restartTimer();
	}

	//Gets rid of all the tile buttons
	void clear() {
		main.removeAll();
		main.repaint();
	}

	//Returns the area of the grid
	private int getTileCount() {
		return w * h;
	}

	//Exposes every mine on the board
	void exposeAllMines() {
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				Tile tile = tiles[x][y];
				if(tile.mine) {
					tile.setIcon(Resources.MINE);
				}
			}
		}
	}

	//Checks if every tile except the mines have been uncovered and flags all of them otherwise
	void checkWin() {
		if(uncovered == getTileCount() - mines) {
			for(int y = 0; y < h; y++) {
				for(int x = 0; x < w; x++) {
					Tile tile = tiles[x][y];
					if(tile.mine) {
						tile.flag();
					} else {
						tile.setEnabled(false);
					}
				}
			}

			flagCount = mines;
			toolbar.timerStarted = false;
		}
	}
}
