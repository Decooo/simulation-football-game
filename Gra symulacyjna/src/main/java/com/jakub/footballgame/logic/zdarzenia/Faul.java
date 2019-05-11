/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.Druzyna;
import com.jakub.footballgame.logic.efekty.EfektFaulu;
import com.jakub.footballgame.logic.efekty.IEfekt;

public class Faul implements Zdarzenie {
	private Druzyna druzynaAtakujaca;
	private int numerGracza;

	public Faul(Druzyna druzynaAtakujaca, int numerGracza) {
		this.druzynaAtakujaca = druzynaAtakujaca;
		this.numerGracza = numerGracza;
	}

	@Override
	public IEfekt efektZdarzenia() {
		return new EfektFaulu(druzynaAtakujaca,numerGracza);
	}
}
