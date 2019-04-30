package com.threebow;

class Game {
	private Board board;

	Game() {
		board = new Board(8, 8);
		board.generate(10);
		board.printGrid();
	}
}