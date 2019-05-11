/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.Druzyna;

public class EfektSpalony extends Efekt {

	public EfektSpalony(Druzyna druzynaAtakujaca, int numerGracza) {
		super(druzynaAtakujaca, numerGracza);
	}

	@Override
	public String zwrocEfektZdarzenia() {
		String druzyna = getDruzynaAtakujaca() == Druzyna.KOMPUTER ? "komputera" : "gracza";
		return "Gracz z numerem " + getNumerGracza() + " z drużyny " + druzyna + " znajdował się na pozycji spalonej";
	}
}
