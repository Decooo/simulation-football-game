/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.NazwaDruzyny;

public abstract class Efekt implements IEfekt{
	private NazwaDruzyny druzynaAtakujaca;
	private int numerGracza;

	public NazwaDruzyny getDruzynaAtakujaca() {
		return druzynaAtakujaca;
	}

	public int getNumerGracza() {
		return numerGracza;
	}

	public Efekt(NazwaDruzyny druzynaAtakujaca, int numerGracza) {
		this.druzynaAtakujaca = druzynaAtakujaca;
		this.numerGracza = numerGracza;
	}

}
