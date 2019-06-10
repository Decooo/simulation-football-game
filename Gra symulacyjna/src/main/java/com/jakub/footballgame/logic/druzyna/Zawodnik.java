/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.druzyna;

import java.util.concurrent.ThreadLocalRandom;

public class Zawodnik {
	private int poziomUmiejetnosci;
	private PozycjaZawodnika pozycja;
	private int numerGracza;
	private int liczbaGoli;
	private int liczbaZoltychKartek;
	private int liczbaCzerwonychKartek;

	public Zawodnik(PoziomSilyDruzyny poziomSilyDruzyny, PozycjaZawodnika pozycja, int numerGracza) {
		this.poziomUmiejetnosci = losujPoziomUmiejetnosci(poziomSilyDruzyny.getValue());
		this.pozycja = pozycja;
		this.numerGracza = numerGracza;
		this.liczbaGoli = 0;
		this.liczbaZoltychKartek = 0;
		this.liczbaCzerwonychKartek = 0;
	}

	public Zawodnik(PoziomSilyDruzyny poziomSilyDruzyny, int numerGracza) {
		this.poziomUmiejetnosci = losujPoziomUmiejetnosci(poziomSilyDruzyny.getValue());
		this.pozycja = PozycjaZawodnika.NIEOKRESLONA;
		this.numerGracza = numerGracza;
		this.liczbaGoli = 0;
		this.liczbaZoltychKartek = 0;
		this.liczbaCzerwonychKartek = 0;
	}

	private int losujPoziomUmiejetnosci(int poziomUmiejetnosci) {
		return ThreadLocalRandom.current().nextInt(poziomUmiejetnosci - 19, poziomUmiejetnosci + 20);
	}

	public int getLiczbaGoli() {
		return liczbaGoli;
	}

	public void setLiczbaGoli(int liczbaGoli) {
		this.liczbaGoli = liczbaGoli;
	}

	public int getLiczbaZoltychKartek() {
		return liczbaZoltychKartek;
	}

	public void setLiczbaZoltychKartek(int liczbaZoltychKartek) {
		this.liczbaZoltychKartek = liczbaZoltychKartek;
	}

	public int getLiczbaCzerwonychKartek() {
		return liczbaCzerwonychKartek;
	}

	public void setLiczbaCzerwonychKartek(int liczbaCzerwonychKartek) {
		this.liczbaCzerwonychKartek = liczbaCzerwonychKartek;
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

