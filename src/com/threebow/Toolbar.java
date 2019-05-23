package com.threebow;

import javax.swing.*;
import java.awt.*;

class Toolbar extends JPanel {
	private static final int HEIGHT = 64;

	Toolbar() {
		//Don't need to set an actual width because boxlayout will scale this to the parent width
		setPreferredSize(new Dimension(0, HEIGHT));

		//Use flowlayout because it's just one row
		setLayout(new GridLayout(1, Game.DIFFICULTIES.length));

		//Populate the difficulty list at the top
		for(Difficulty difficulty : Game.DIFFICULTIES) {
			JButton button = new JButton(difficulty.name);
			button.addActionListener(e -> difficultyClicked(difficulty));
			add(button);
		}
	}

	private void difficultyClicked(Difficulty difficulty) {
		Game.board.clear();
		Game.board.generate(difficulty);
	}
}