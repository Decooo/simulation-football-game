/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.druzyna;

public enum PoziomSilyDruzyny {
	BARDZONISKI {
		@Override
		public int getValue() {
			return 20;
		}
	},
	NISKI {
		@Override
		public int getValue() {
			return 35;
		}
	},
	SREDNI {
		@Override
		public int getValue() {
			return 50;
		}
	},
	WYSOKI {
		@Override
		public int getValue() {
			return 65;
		}
	},
	BARDZOWYSOKI {
		@Override
		public int getValue() {
			return 80;
		}
	};


	public abstract int getValue();
}
