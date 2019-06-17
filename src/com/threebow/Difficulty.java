package com.threebow;

import java.awt.*;

class Difficulty {
	//Difficulty details
	String name;
	int rows;
	int columns;
	int mines;
	Color color;

	Difficulty(String name, int rows, int columns, int mines, Color color) {
		this.name = name;
		this.rows = rows;
		this.columns = columns;
		this.mines = mines;
		this.color = color;
	}
}