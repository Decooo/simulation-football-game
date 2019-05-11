/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.Druzyna;

public class ObronaBramkarza extends Efekt {
	public ObronaBramkarza(Druzyna druzynaAtakujaca, int numerGracza) {
		super(druzynaAtakujaca, numerGracza);
	}

	@Override
	public String zwrocEfektZdarzenia() {
		String druzyna = getDruzynaAtakujaca() == Druzyna.KOMPUTER ? "komputera" : "gracza";
		return "Piękna parada bramkarza drużyny " + druzyna + " po strzale zawodnika z numerem " + getNumerGracza();
	}
}
