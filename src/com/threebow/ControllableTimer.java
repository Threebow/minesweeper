package com.threebow;

import java.util.Timer;
import java.util.TimerTask;

public class ControllableTimer {
	private Timer timer;
	private TimerTask task;

	void setTask(TimerTask task) {
		this.task = task;
	}

	void start(int delay, int period) {
		timer = new Timer();
		timer.schedule(task, delay, period);
	}

	void stop() {
		timer.cancel();
		timer = null;
	}
}