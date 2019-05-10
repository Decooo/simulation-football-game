/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic;

public enum Druzyna {
	KOMPUTER {
		@Override
		public String getValue() {
			return "komputer";
		}
	},
	GRACZ {
		@Override
		public String getValue() {
			return "gracz";
		}
	};

	public abstract String getValue();
}
