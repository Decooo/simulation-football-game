/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.NazwaDruzyny;
import com.jakub.footballgame.logic.druzyna.Druzyny;

public class ZoltaKartka extends Efekt {
	public ZoltaKartka(NazwaDruzyny druzynaAtakujaca, int numerGracza) {
		super(druzynaAtakujaca, numerGracza);
	}

	@Override
	public String zwrocEfektZdarzenia() {
		String druzyna = getDruzynaAtakujaca() == NazwaDruzyny.KOMPUTER ? "komputera" : "gracza";
		Druzyny.zapiszZoltaKartke(getDruzynaAtakujaca(),getNumerGracza());
		return "Żółta kartka dla gracza z numerem " + getNumerGracza() + " z drużyny " + druzyna;
	}
}
