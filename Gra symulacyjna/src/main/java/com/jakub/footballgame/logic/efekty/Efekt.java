/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.efekty;

import com.jakub.footballgame.logic.Druzyna;

public abstract class Efekt implements IEfekt{
	private Druzyna druzynaAtakujaca;
	private int numerGracza;

	public Druzyna getDruzynaAtakujaca() {
		return druzynaAtakujaca;
	}

	public int getNumerGracza() {
		return numerGracza;
	}

	public Efekt(Druzyna druzynaAtakujaca, int numerGracza) {
		this.druzynaAtakujaca = druzynaAtakujaca;
		this.numerGracza = numerGracza;
	}

}
