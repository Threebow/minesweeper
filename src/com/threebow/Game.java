package com.threebow;

class Game {
	static Board board;
	static final Difficulty[] DIFFICULTIES = {
		new Difficulty("Beginner", 8, 8, 10),
		new Difficulty("Intermediate", 16, 16, 40),
		new Difficulty("Expert", 16, 30, 99)
	};

	Game() {
		board = new Board();
		board.generate(DIFFICULTIES[0]);
	}

	void end() {
		board.exposeAllMines();
	}
}