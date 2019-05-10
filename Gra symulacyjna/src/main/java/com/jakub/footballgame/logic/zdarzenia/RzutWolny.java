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
import com.jakub.footballgame.logic.efekty.IEfekt;

public class RzutWolny extends ZdarzeniaZakonczoneStrzalem {

	public RzutWolny(Druzyna druzynaAtakujaca, Druzyna druzynaBroniaca, int numerGraczaStrzelajacego, int silaStrzelca, int silaBramkarza) {
		super(druzynaAtakujaca, druzynaBroniaca, numerGraczaStrzelajacego, silaStrzelca, silaBramkarza);
	}

	@Override
	public IEfekt efektZdarzenia() {
		return null;
	}
}
