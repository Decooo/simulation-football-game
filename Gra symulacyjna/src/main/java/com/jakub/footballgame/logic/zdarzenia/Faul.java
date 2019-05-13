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
import com.jakub.footballgame.logic.efekty.*;

import java.util.concurrent.ThreadLocalRandom;

public class Faul implements Zdarzenie {
	private Druzyna druzynaAtakujaca;
	private int numerGracza;

	public Faul(Druzyna druzynaAtakujaca, int numerGracza) {
		this.druzynaAtakujaca = druzynaAtakujaca;
		this.numerGracza = numerGracza;
	}

	@Override
	public IEfekt efektZdarzenia() {
		int randomNumer = ThreadLocalRandom.current().nextInt(1, 11);
		if (randomNumer < 4) return new ZoltaKartka(druzynaAtakujaca, numerGracza);
		else if (randomNumer < 5)
			return new CzerwonaKartka(druzynaAtakujaca, numerGracza);
		else return new EfektFaulu(druzynaAtakujaca, numerGracza);
	}
}
