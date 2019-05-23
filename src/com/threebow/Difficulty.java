package com.threebow;

class Difficulty {
	//Difficulty details
	String name;
	int rows;
	int columns;
	int mines;

	Difficulty(String name, int rows, int columns, int mines) {
		this.name = name;
		this.rows = rows;
		this.columns = columns;
		this.mines = mines;
	}
}