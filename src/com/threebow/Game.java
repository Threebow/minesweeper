package com.threebow;

import java.awt.*;

class Game {
	//The reference to the game board
	static Board board;

	//List of difficulties
	static final Difficulty[] DIFFICULTIES = {
		new Difficulty("Beginner", 8, 8, 10, new Color(46, 204, 113)),
		new Difficulty("Intermediate", 16, 16, 40, new Color(241, 196, 15)),
		new Difficulty("Expert", 16, 30, 99, new Color(231, 76, 60))
	};

	Game() {
		//Create the initial board and use the first difficulty by default
		board = new Board();
		board.generate(DIFFICULTIES[0]);
	}

	//End the game by exposing all the mines
	void end() {
		board.exposeAllMines();
		board.toolbar.timer.stop();
		board.gameOver = true;
	}
}