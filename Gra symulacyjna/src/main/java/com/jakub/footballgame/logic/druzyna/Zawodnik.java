/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.druzyna;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Zawodnik {
	public int poziomUmiejetnosci;
	public PozycjaZawodnika pozycja;
	public int numerGracza;

	public Zawodnik(PoziomSilyDruzyny poziomSilyDruzyny, PozycjaZawodnika pozycja, int numerGracza) {
		this.poziomUmiejetnosci = losujPoziomUmiejetnosci(poziomSilyDruzyny.getValue());
		this.pozycja = pozycja;
		this.numerGracza = numerGracza;
	}

	private int losujPoziomUmiejetnosci(int poziomUmiejetnosci) {
		return ThreadLocalRandom.current().nextInt(poziomUmiejetnosci-20, poziomUmiejetnosci + 21);
	}

	public int getPoziomUmiejetnosci() {
		return poziomUmiejetnosci;
	}

	public void setPoziomUmiejetnosci(int poziomUmiejetnosci) {
		this.poziomUmiejetnosci = poziomUmiejetnosci;
	}

	public PozycjaZawodnika getPozycja() {
		return pozycja;
	}

	public void setPozycja(PozycjaZawodnika pozycja) {
		this.pozycja = pozycja;
	}

	public int getNumerGracza() {
		return numerGracza;
	}

	public void setNumerGracza(int numerGracza) {
		this.numerGracza = numerGracza;
	}
}

