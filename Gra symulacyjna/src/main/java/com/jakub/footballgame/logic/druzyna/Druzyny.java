/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.druzyna;

import com.jakub.footballgame.logic.NazwaDruzyny;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Druzyny {

	private static ArrayList<Zawodnik> zawodnicyDruzynyKomputera;
	private static ArrayList<Zawodnik> zawodnicyDruzynyGracza;

	public static ArrayList<Zawodnik> getZawodnicyDruzynyKomputera() {
		return zawodnicyDruzynyKomputera;
	}

	public static ArrayList<Zawodnik> getZawodnicyDruzynyGracza() {
		return zawodnicyDruzynyGracza;
	}

	public void stworzDruzyneKomputera(PoziomSilyDruzyny poziomSilyDruzyny, Taktyka taktyka) {
		zawodnicyDruzynyKomputera = new ArrayList<Zawodnik>();
		int[] liczbaGraczyWFormacji = taktyka.getValue();
		zawodnicyDruzynyKomputera.add(new Zawodnik(poziomSilyDruzyny, PozycjaZawodnika.BRAMKARZ, 1));

		int numerAktualnieTworzonegoZawodnika = 2;
		for (int i = 0; i < liczbaGraczyWFormacji[0]; i++) {
			zawodnicyDruzynyKomputera.add(new Zawodnik(poziomSilyDruzyny, PozycjaZawodnika.OBRONCA, numerAktualnieTworzonegoZawodnika));
			numerAktualnieTworzonegoZawodnika++;
		}
		for (int i = 0; i < liczbaGraczyWFormacji[1]; i++) {
			zawodnicyDruzynyKomputera.add(new Zawodnik(poziomSilyDruzyny, PozycjaZawodnika.POMOCNIK, numerAktualnieTworzonegoZawodnika));
			numerAktualnieTworzonegoZawodnika++;
		}
		for (int i = 0; i < liczbaGraczyWFormacji[2]; i++) {
			zawodnicyDruzynyKomputera.add(new Zawodnik(poziomSilyDruzyny, PozycjaZawodnika.NAPASTNIK, numerAktualnieTworzonegoZawodnika));
			numerAktualnieTworzonegoZawodnika++;
		}
	}

	public void stworzDruzyneGracza(PoziomSilyDruzyny poziomSilyDruzyny) {
		zawodnicyDruzynyGracza = new ArrayList<>();
		for (int i = 1; i < 12; i++) {
			zawodnicyDruzynyGracza.add(new Zawodnik(poziomSilyDruzyny, i));
		}
	}

	public void przypiszPozycjeLosowo(ArrayList<Zawodnik> zawodnicy, Taktyka taktyka) {
		int[] liczbaGraczyWFormacji = taktyka.getValue();

		for (int i = 0; i < 11; i++) {
			if (i == 0) zawodnicy.get(i).setPozycja(PozycjaZawodnika.BRAMKARZ);
			else if (i <= liczbaGraczyWFormacji[0]) zawodnicy.get(i).setPozycja(PozycjaZawodnika.OBRONCA);
			else if (i <= (liczbaGraczyWFormacji[0] + liczbaGraczyWFormacji[1]))
				zawodnicy.get(i).setPozycja(PozycjaZawodnika.POMOCNIK);
			else zawodnicy.get(i).setPozycja(PozycjaZawodnika.NAPASTNIK);
		}
	}

	public static void zapiszGola(NazwaDruzyny druzyna, int numerGracza) {
		List<Zawodnik> zawodnicy = (druzyna.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza();
		zawodnicy.stream()
				.filter(z -> z.getNumerGracza() == numerGracza)
				.forEach(zawodnik -> zawodnik.setLiczbaGoli(zawodnik.getLiczbaGoli() + 1));
	}

	public static void zapiszZoltaKartke(NazwaDruzyny druzyna, int numerGracza) {
		List<Zawodnik> zawodnicy = (druzyna.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza();
		zawodnicy.stream()
				.filter(z -> z.getNumerGracza() == numerGracza)
				.filter(z -> z.getLiczbaZoltychKartek() < 2)
				.filter(z -> z.getLiczbaCzerwonychKartek() < 1)
				.peek(zawodnik -> zawodnik.setLiczbaZoltychKartek(zawodnik.getLiczbaZoltychKartek() + 1))
				.filter(z -> z.getLiczbaZoltychKartek() == 2)
				.forEach(zawodnik -> zawodnik.setLiczbaCzerwonychKartek(1));
	}

	public static void zapiszCzerwonaKartke(NazwaDruzyny druzyna, int numerGracza) {
		List<Zawodnik> zawodnicy = (druzyna.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza();
		zawodnicy.stream()
				.filter(z -> z.getNumerGracza() == numerGracza)
				.filter(z -> z.getLiczbaCzerwonychKartek() < 1)
				.forEach(zawodnik -> zawodnik.setLiczbaCzerwonychKartek(1));
	}
}
