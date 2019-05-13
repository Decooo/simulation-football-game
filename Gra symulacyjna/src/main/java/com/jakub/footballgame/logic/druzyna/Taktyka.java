/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.druzyna;

public enum Taktyka {
	TRZY_PIEC_DWA {
		@Override
		public int[] getValue() {
			return new int[]{3,5,2};
		}
	},
	TRZY_CZTERY_TRZY {
		@Override
		public int[] getValue() {
			return new int[]{3,4,3};
		}
	},
	CZTERY_CZTERY_DWA {
		@Override
		public int[] getValue() {
			return new int[]{4,4,2};
		}
	},
	CZTERY_TRZY_TRZY {
		@Override
		public int[] getValue() {
			return new int[]{4,3,3};
		}
	},
	CZTERY_PIEC_JEDEN {
		@Override
		public int[] getValue() {
			return new int[]{4,5,1};
		}
	},
	PIEC_CZTERY_JEDEN {
		@Override
		public int[] getValue() {
			return new int[]{5,4,1};
		}
	},
	PIEC_TRZY_DWA {
		@Override
		public int[] getValue() {
			return new int[]{5,3,2};
		}
	};


	public abstract int[] getValue();
}
