/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.NazwaDruzyny;

public class DaneZdarzenia {
	private NazwyZdarzen nazwaZdarzenia;
	private NazwaDruzyny druzynaAtakujaca;
	private NazwaDruzyny druzynaBroniaca;
	private int numerGracza;
	private int silaStrzelca;
	private int silaBramkarza;

	private DaneZdarzenia(Builder builder) {
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

	public NazwaDruzyny getDruzynaAtakujaca() {
		return druzynaAtakujaca;
	}

	public NazwaDruzyny getDruzynaBroniaca() {
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
		private NazwaDruzyny druzynaAtakujaca = null;
		private NazwaDruzyny druzynaBroniaca = null;
		private int numerGracza = 0;
		private int silaStrzelca = 0;
		private int silaBramkarza = 0;

		public Builder nazwaZdarzenia(NazwyZdarzen nazwaZdarzenia) {
			this.nazwaZdarzenia = nazwaZdarzenia;
			return this;
		}

		public Builder druzynaAtakujaca(NazwaDruzyny druzynaAtakujaca) {
			this.druzynaAtakujaca = druzynaAtakujaca;
			return this;
		}

		public Builder druzynaBroniaca(NazwaDruzyny druzynaBroniaca) {
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
