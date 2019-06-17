package com.threebow;

import java.util.Timer;
import java.util.TimerTask;

class ControllableTimer {
	private Timer timer;

	void start(TimerTask task) {
		timer = new Timer();
		timer.schedule(task, 1000, 1000);
	}

	void stop() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}
}