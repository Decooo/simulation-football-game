/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.NazwaDruzyny;
import com.jakub.footballgame.logic.druzyna.Druzyny;

public class CzerwonaKartka extends Efekt {
	public CzerwonaKartka(NazwaDruzyny druzynaAtakujaca, int numerGracza) {
		super(druzynaAtakujaca, numerGracza);
	}

	@Override
	public String zwrocEfektZdarzenia() {
		String druzyna = getDruzynaAtakujaca() == NazwaDruzyny.KOMPUTER ? "komputera" : "gracza";
		Druzyny.zapiszCzerwonaKartke(getDruzynaAtakujaca(),getNumerGracza());
		return "CZERWONA KARTKA dla gracza z numerem " + getNumerGracza() + " z drużyny " + druzyna;
	}
}
