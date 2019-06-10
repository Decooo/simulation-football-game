/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.NazwaDruzyny;
import com.jakub.footballgame.logic.efekty.Gol;
import com.jakub.footballgame.logic.efekty.IEfekt;
import com.jakub.footballgame.logic.efekty.ObronaBramkarza;
import com.jakub.footballgame.logic.efekty.StrzalNiecelny;

import java.util.concurrent.ThreadLocalRandom;

public class RzutKarny extends ZdarzeniaZakonczoneStrzalem {

	public RzutKarny(NazwaDruzyny druzynaAtakujaca, NazwaDruzyny druzynaBroniaca, int numerGraczaStrzelajacego, int silaStrzelca, int silaBramkarza) {
		super(druzynaAtakujaca, druzynaBroniaca, numerGraczaStrzelajacego, silaStrzelca, silaBramkarza);
	}

	@Override
	public IEfekt efektZdarzenia() {
		int randomNumer = ThreadLocalRandom.current().nextInt(1, 11);
		if (randomNumer < 8) return new Gol(this.getDruzynaAtakujaca(), this.getNumerGraczaStrzelajacego());
		else if (randomNumer < 10)
			return new ObronaBramkarza(this.getDruzynaAtakujaca(), this.getNumerGraczaStrzelajacego());
		else return new StrzalNiecelny(this.getDruzynaAtakujaca(), this.getNumerGraczaStrzelajacego());
	}
}
