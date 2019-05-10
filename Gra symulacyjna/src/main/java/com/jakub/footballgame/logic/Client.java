/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic;

import com.jakub.footballgame.logic.zdarzenia.*;

public class Client {
	public static void main(String[] args) throws Exception {
		IFabrykaZdarzen fabrykaZdarzen = new FabrykaZdarzen();
		DaneZdarzenia daneZdarzenia = new DaneZdarzenia.Builder()
				.nazwaZdarzenia(NazwyZdarzen.FAUL)
				.druzynaAtakujaca(Druzyna.KOMPUTER)
				.numerGracza(0)
				.build();
		Zdarzenie zdarzenie = fabrykaZdarzen.utworzZdarzenie(daneZdarzenia);
		System.out.println("zdarzenie.getClass() = " + zdarzenie.getClass());
	}
}
