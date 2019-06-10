package com.threebow;

import javax.swing.*;
import java.awt.*;

class Toolbar extends JPanel {
	private static final int HEIGHT = 96;

	private JLabel mineCount;
	private JButton reset;
	private JLabel timer;

	Toolbar() {
		//Don't need to set an actual width because boxlayout will scale this to the parent width
		setPreferredSize(new Dimension(0, HEIGHT));

		//Use flowlayout because it's just one row
		setLayout(new GridLayout(2, Game.DIFFICULTIES.length));

		//Number of mines left
		mineCount = new JLabel("099");
		mineCount.setHorizontalAlignment(JLabel.CENTER);
		mineCount.setFont(new Font("Consolas", Font.PLAIN, 24));
		add(mineCount);

		//Reset Button
		reset = new JButton("Reset");
		add(reset);

		//Elapsed time
		timer = new JLabel("01:05");
		timer.setHorizontalAlignment(JLabel.CENTER);
		timer.setFont(new Font("Consolas", Font.PLAIN, 24));
		add(timer);


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

	void setMineCount(int count) {
		mineCount.setText(Integer.toString(count));
	}

	void setTime(String str) {
		timer.setText(str);
	}
}