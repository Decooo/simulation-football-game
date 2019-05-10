/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.Druzyna;

public abstract class ZdarzeniaZakonczoneStrzalem implements Zdarzenie {
	private Druzyna druzynaAtakujaca;
	private Druzyna druzynaBroniaca;
	private int numerGraczaStrzelajacego;
	private int silaStrzelca;
	private int silaBramkarza;

	public ZdarzeniaZakonczoneStrzalem(Druzyna druzynaAtakujaca, Druzyna druzynaBroniaca, int numerGraczaStrzelajacego, int silaStrzelca, int silaBramkarza) {
		this.druzynaAtakujaca = druzynaAtakujaca;
		this.druzynaBroniaca = druzynaBroniaca;
		this.numerGraczaStrzelajacego = numerGraczaStrzelajacego;
		this.silaStrzelca = silaStrzelca;
		this.silaBramkarza = silaBramkarza;
	}

	public Druzyna getDruzynaAtakujaca() {
		return druzynaAtakujaca;
	}

	public void setDruzynaAtakujaca(Druzyna druzynaAtakujaca) {
		this.druzynaAtakujaca = druzynaAtakujaca;
	}

	public Druzyna getDruzynaBroniaca() {
		return druzynaBroniaca;
	}

	public void setDruzynaBroniaca(Druzyna druzynaBroniaca) {
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
