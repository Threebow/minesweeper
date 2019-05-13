package com.threebow;

class Game {
	private Board board;
	Difficulty[] difficulties = {
		new Difficulty("Beginner", 8, 8, 10),
		new Difficulty("Intermediate", 16, 16, 40),
		new Difficulty("Expert", 16, 30, 99)
	};

	Game() {
		board = new Board(8, 8);
		board.generate(5);
		board.printGrid();
	}

	void end() {
		board.exposeAllMines();
	}
}