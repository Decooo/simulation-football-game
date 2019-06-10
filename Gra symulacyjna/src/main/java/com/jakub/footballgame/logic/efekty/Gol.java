/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.NazwaDruzyny;
import com.jakub.footballgame.logic.druzyna.Druzyny;

public class Gol extends Efekt {

	public Gol(NazwaDruzyny druzynaAtakujaca, int numerGracza) {
		super(druzynaAtakujaca, numerGracza);
	}

	@Override
	public String zwrocEfektZdarzenia() {
		String druzyna = getDruzynaAtakujaca() == NazwaDruzyny.KOMPUTER ? "komputera" : "gracza";
		return "GOOOOOOL!!! Gol dla drużyny " + druzyna + " zdobyty przez gracza z numerem " + getNumerGracza();
	}
}
