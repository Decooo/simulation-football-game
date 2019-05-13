/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.NazwaDruzyny;

public abstract class ZdarzeniaZakonczoneStrzalem implements Zdarzenie {
	private NazwaDruzyny druzynaAtakujaca;
	private NazwaDruzyny druzynaBroniaca;
	private int numerGraczaStrzelajacego;
	private int silaStrzelca;
	private int silaBramkarza;

	public ZdarzeniaZakonczoneStrzalem(NazwaDruzyny druzynaAtakujaca, NazwaDruzyny druzynaBroniaca, int numerGraczaStrzelajacego, int silaStrzelca, int silaBramkarza) {
		this.druzynaAtakujaca = druzynaAtakujaca;
		this.druzynaBroniaca = druzynaBroniaca;
		this.numerGraczaStrzelajacego = numerGraczaStrzelajacego;
		this.silaStrzelca = silaStrzelca;
		this.silaBramkarza = silaBramkarza;
	}

	public NazwaDruzyny getDruzynaAtakujaca() {
		return druzynaAtakujaca;
	}

	public void setDruzynaAtakujaca(NazwaDruzyny druzynaAtakujaca) {
		this.druzynaAtakujaca = druzynaAtakujaca;
	}

	public NazwaDruzyny getDruzynaBroniaca() {
		return druzynaBroniaca;
	}

	public void setDruzynaBroniaca(NazwaDruzyny druzynaBroniaca) {
		this.druzynaBroniaca = druzynaBroniaca;
	}

	public int getNumerGraczaStrzelajacego() {
		return numerGraczaStrzelajacego;
	}

	public void setNumerGraczaStrzelajacego(int numerGraczaStrzelajacego) {
		this.numerGraczaStrzelajacego = numerGraczaStrzelajacego;
	}

	public int getSilaStrzelca() {
		return silaStrzelca;
	}

	public void setSilaStrzelca(int silaStrzelca) {
		this.silaStrzelca = silaStrzelca;
	}

	public int getSilaBramkarza() {
		return silaBramkarza;
	}

	public void setSilaBramkarza(int silaBramkarza) {
		this.silaBramkarza = silaBramkarza;
	}
}
