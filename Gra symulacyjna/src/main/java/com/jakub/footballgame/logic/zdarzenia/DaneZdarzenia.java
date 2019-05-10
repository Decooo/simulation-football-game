/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.Druzyna;

public class DaneZdarzenia {
	private NazwyZdarzen nazwaZdarzenia;
	private Druzyna druzynaAtakujaca;
	private Druzyna druzynaBroniaca;
	private int numerGracza;
	private int silaStrzelca;
	private int silaBramkarza;

	public DaneZdarzenia(Builder builder) {
		this.nazwaZdarzenia = builder.nazwaZdarzenia;
		this.druzynaAtakujaca = builder.druzynaAtakujaca;
		this.druzynaBroniaca = builder.druzynaBroniaca;
		this.numerGracza = builder.numerGracza;
		this.silaStrzelca = builder.silaStrzelca;
		this.silaBramkarza = builder.silaBramkarza;
	}

	public NazwyZdarzen getNazwaZdarzenia() {
		return nazwaZdarzenia;
	}

	public Druzyna getDruzynaAtakujaca() {
		return druzynaAtakujaca;
	}

	public Druzyna getDruzynaBroniaca() {
		return druzynaBroniaca;
	}

	public int getNumerGracza() {
		return numerGracza;
	}

	public int getSilaStrzelca() {
		return silaStrzelca;
	}

	public int getSilaBramkarza() {
		return silaBramkarza;
	}

	public static class Builder {
		private NazwyZdarzen nazwaZdarzenia = null;
		private Druzyna druzynaAtakujaca = null;
		private Druzyna druzynaBroniaca = null;
		private int numerGracza = 0;
		private int silaStrzelca = 0;
		private int silaBramkarza = 0;

		public Builder nazwaZdarzenia(NazwyZdarzen nazwaZdarzenia) {
			this.nazwaZdarzenia = nazwaZdarzenia;
			return this;
		}

		public Builder druzynaAtakujaca(Druzyna druzynaAtakujaca) {
			this.druzynaAtakujaca = druzynaAtakujaca;
			return this;
		}

		public Builder druzynaBroniaca(Druzyna druzynaBroniaca) {
			this.druzynaBroniaca = druzynaBroniaca;
			return this;
		}

		public Builder numerGracza(int numerGracza) {
			this.numerGracza = numerGracza;
			return this;
		}

		public Builder silaStrzelca(int silaStrzelca) {
			this.silaStrzelca = silaStrzelca;
			return this;
		}

		public Builder silaBramkarza(int silaBramkarza) {
			this.silaBramkarza = silaBramkarza;
			return this;
		}

		public DaneZdarzenia build() {
			return new DaneZdarzenia(this);
		}
	}
}
