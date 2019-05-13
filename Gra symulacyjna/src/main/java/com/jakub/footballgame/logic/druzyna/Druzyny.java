/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.druzyna;

import java.util.ArrayList;
import java.util.Optional;

public class Druzyny {

	public static ArrayList<Zawodnik> zawodnicyDruzynyKomputera;
	public static ArrayList<Zawodnik> zawodnicyDruzynyGracza;

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
}
