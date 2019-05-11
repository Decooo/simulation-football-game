/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.Druzyna;

public class Gol extends Efekt {

	public Gol(Druzyna druzynaAtakujaca, int numerGracza) {
		super(druzynaAtakujaca, numerGracza);
	}

	@Override
	public String zwrocEfektZdarzenia() {
		String druzyna = getDruzynaAtakujaca() == Druzyna.KOMPUTER ? "komputera" : "gracza";
		return "Gol dla drużyny " + druzyna + " zdobyty przez gracza z numerem " + getNumerGracza();
	}
}
