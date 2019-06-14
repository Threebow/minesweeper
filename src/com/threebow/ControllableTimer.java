package com.threebow;

import java.util.Timer;
import java.util.TimerTask;

class ControllableTimer {
	private Timer timer;

	void start(TimerTask task, int delay, int period) {
		timer = new Timer();
		timer.schedule(task, delay, period);
	}

	void stop() {
		System.out.println("STOP");

		timer.cancel();
		timer = null;
	}
}