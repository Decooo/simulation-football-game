/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.druzyna;

public enum PozycjaZawodnika {
	OBRONCA {
		@Override
		public String getValue() {
			return "obronca";
		}
	},
	POMOCNIK {
		@Override
		public String getValue() {
			return "pomocnik";
		}
	},
	NAPASTNIK {
		@Override
		public String getValue() {
			return "napastnik";
		}
	},
	BRAMKARZ {
		@Override
		public String getValue() {
			return "bramkarz";
		}
	},
	NIEOKRESLONA {
		@Override
		public String getValue() {
			return "nieokreslona";
		}
	};


	public abstract String getValue();
}
