/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic;

import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
	Timer timer;
	private int timecounter = 0;

	TimerTask Task1 = new TimerTask() {
		@Override
		public void run() {
			setTimecounter(getTimecounter()+1);
		}
	};

	public TimerClass(){
		timer = new Timer();
	}

	public int getTimecounter() {
		return timecounter;
	}

	public void setTimecounter(int timecounter) {
		this.timecounter = timecounter;
	}
}
