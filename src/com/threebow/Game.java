package com.threebow;

class Game {
	Game() {
		Board board = new Board(8, 8);
		board.generate(10);
		board.printGrid();
	}
}