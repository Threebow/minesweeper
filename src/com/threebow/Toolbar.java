package com.threebow;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

class Toolbar extends JPanel {
	private static final int HEIGHT = 96;
	private final SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");

	private JLabel mineCount;
	private JButton reset;
	private JLabel timeElapsed;
	private int time;

	ControllableTimer timer = new ControllableTimer();

	Toolbar() {
		//Don't need to set an actual width because boxlayout will scale this to the parent width
		setPreferredSize(new Dimension(0, HEIGHT));

		//Use flowlayout because it's just one row
		setLayout(new GridLayout(2, Game.DIFFICULTIES.length));

		//Number of mines left
		mineCount = new JLabel("000");
		mineCount.setHorizontalAlignment(JLabel.CENTER);
		mineCount.setFont(new Font("Consolas", Font.PLAIN, 24));
		add(mineCount);

		//Reset Button
		reset = new JButton("Reset");
		reset.addActionListener(e -> difficultyClicked(Game.board.difficulty));
		add(reset);

		//Elapsed time counter
		timeElapsed = new JLabel("00:00");
		timeElapsed.setHorizontalAlignment(JLabel.CENTER);
		timeElapsed.setFont(new Font("Consolas", Font.PLAIN, 24));
		add(timeElapsed);

		//Populate the difficulty list at the top
		for(Difficulty difficulty : Game.DIFFICULTIES) {
			JButton button = new JButton(difficulty.name);
			button.addActionListener(e -> difficultyClicked(difficulty));
			button.setBackground(difficulty.color);
			add(button);
		}
	}

	private void difficultyClicked(Difficulty difficulty) {
		Game.board.clear();
		Game.board.generate(difficulty);
	}

	void setMineCount(int count) {
		mineCount.setText(String.format("%03d", count));
	}

	void resetTimer() {
		time = 0;
		timeElapsed.setText("00:00");

		timer.stop();
	}

	void startTimer() {
		timer.start(new TimerTask() {
			@Override
			public void run() {
				time++;
				timeElapsed.setText(formatter.format(new Date(time * 1000)));
			}
		});
	}
}