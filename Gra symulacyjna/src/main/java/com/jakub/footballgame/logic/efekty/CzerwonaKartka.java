/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.Druzyna;

public class CzerwonaKartka extends Efekt {
	public CzerwonaKartka(Druzyna druzynaAtakujaca, int numerGracza) {
		super(druzynaAtakujaca, numerGracza);
	}

	@Override
	public String zwrocEfektZdarzenia() {
		String druzyna = getDruzynaAtakujaca() == Druzyna.KOMPUTER ? "komputera" : "gracza";
		return "Czerwona kartka dla gracza z numerem " + getNumerGracza() + " z drużyny " + druzyna;
	}
}
