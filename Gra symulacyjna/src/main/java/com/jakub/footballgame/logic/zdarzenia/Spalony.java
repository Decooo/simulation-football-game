/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.NazwaDruzyny;
import com.jakub.footballgame.logic.efekty.EfektSpalony;
import com.jakub.footballgame.logic.efekty.IEfekt;

public class Spalony implements Zdarzenie {
	private NazwaDruzyny druzynaAtakujaca;
	private int numerGracza;

	public Spalony(NazwaDruzyny druzynaAtakujaca, int numerGracza) {
		this.druzynaAtakujaca = druzynaAtakujaca;
		this.numerGracza = numerGracza;
	}

	@Override
	public IEfekt efektZdarzenia() {
		return new EfektSpalony(druzynaAtakujaca, numerGracza);
	}
}
