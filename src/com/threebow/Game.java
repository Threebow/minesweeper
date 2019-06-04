package com.threebow;

class Game {
	//The reference to the game board
	static Board board;

	//List of difficulties
	static final Difficulty[] DIFFICULTIES = {
		new Difficulty("Beginner", 8, 8, 10),
		new Difficulty("Intermediate", 16, 16, 40),
		new Difficulty("Expert", 16, 30, 99)
	};

	Game() {
		//Create the initial board and use the first difficulty by default
		board = new Board();
		board.generate(DIFFICULTIES[0]);
	}

	//End the game by exposing all the mines
	void end() {
		board.exposeAllMines();
	}
}