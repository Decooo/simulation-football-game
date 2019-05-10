/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

public enum NazwyZdarzen {
	STRZAL {
		@Override
		public String getValue() {
			return "strzal";
		}
	},
	RZUTKARNY {
		@Override
		public String getValue() {
			return "rzutkarny";
		}
	},
	RZUTWOLNY {
		@Override
		public String getValue() {
			return "rzutwolny";
		}
	},
	FAUL {
		@Override
		public String getValue() {
			return "faul";
		}
	},
	SPALONY {
		@Override
		public String getValue() {
			return "spalony";
		}
	};

	public abstract String getValue();
}
