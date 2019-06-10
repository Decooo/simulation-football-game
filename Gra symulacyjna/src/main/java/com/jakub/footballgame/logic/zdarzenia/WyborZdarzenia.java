/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

import com.jakub.footballgame.logic.NazwaDruzyny;
import com.jakub.footballgame.logic.druzyna.Druzyny;
import com.jakub.footballgame.logic.druzyna.PozycjaZawodnika;
import com.jakub.footballgame.logic.druzyna.Zawodnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class WyborZdarzenia {

	public Zdarzenie wybierzZlecenie() throws Exception {
		int randomNumer = wylosujNumer();

		IFabrykaZdarzen fabrykaZdarzen = new FabrykaZdarzen();

		if (randomNumer <= 3) return fabrykaZdarzen.utworzZdarzenie(przygotowanieZdarzeniaStrzalu());
		else if (randomNumer <= 5) return fabrykaZdarzen.utworzZdarzenie(przygotowanieZdarzeniaRzutuWolnego());
		else if (randomNumer <= 6) return fabrykaZdarzen.utworzZdarzenie(przygotowanieZdarzeniaRzutuKarnego());
		else if (randomNumer <= 9) return fabrykaZdarzen.utworzZdarzenie(przygotowanieZdarzeniaFaulu());
		else return fabrykaZdarzen.utworzZdarzenie(przygotowanieZdarzeniaSpalony());
	}

	private int wylosujNumer(){
		int randomNumer = ThreadLocalRandom.current().nextInt(1, 11);

		List<Integer> wylosowanyNumer = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			wylosowanyNumer.add(i);
		}
		Collections.shuffle(wylosowanyNumer);
		Collections.shuffle(wylosowanyNumer);
		return wylosowanyNumer.get(randomNumer-1);
	}

	private DaneZdarzenia przygotowanieZdarzeniaStrzalu() {
		NazwaDruzyny druzynaAtakujaca = ustalDruzyneAtakujaca();
		int numerGracza = ustalStrzelca((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyGracza() : Druzyny.getZawodnicyDruzynyKomputera());

		return new DaneZdarzenia.Builder()
				.nazwaZdarzenia(NazwyZdarzen.STRZAL)
				.druzynaAtakujaca(druzynaAtakujaca)
				.druzynaBroniaca((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? NazwaDruzyny.GRACZ : NazwaDruzyny.KOMPUTER)
				.numerGracza(numerGracza)
				.silaStrzelca(getSilaGracza((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyGracza() : Druzyny.getZawodnicyDruzynyKomputera(), numerGracza))
				.silaBramkarza(getSilaBramkarza((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza()))
				.build();
	}

	private int getSilaBramkarza(ArrayList<Zawodnik> zawodnicy) {
		Optional<Zawodnik> bramkarz = zawodnicy.stream()
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.BRAMKARZ))
				.findFirst();

		return bramkarz.map(Zawodnik::getPoziomUmiejetnosci).orElse(0);
	}

	private int getSilaGracza(List<Zawodnik> zawodnicy, int numerGracza) {
		Optional<Zawodnik> zawodnik = zawodnicy.stream()
				.filter(z -> z.getNumerGracza() == numerGracza)
				.findFirst();

		return zawodnik.map(Zawodnik::getPoziomUmiejetnosci).orElse(0);
	}

	private int ustalStrzelca(List<Zawodnik> zawodnicy) {
		int suma = 0;
		List<Zawodnik> zawodnicyZPola = getZawodnikowZPola(zawodnicy);

		Integer[] listaSzansNaGola = new Integer[zawodnicyZPola.size()];

		for (int i = 0; i < zawodnicyZPola.size(); i++) {
			if (zawodnicyZPola.get(i).getPozycja().equals(PozycjaZawodnika.OBRONCA)) {
				listaSzansNaGola[i] = suma + zawodnicyZPola.get(i).getPoziomUmiejetnosci();
				suma = listaSzansNaGola[i];
			} else if (zawodnicyZPola.get(i).getPozycja().equals(PozycjaZawodnika.POMOCNIK)) {
				listaSzansNaGola[i] = suma + (zawodnicyZPola.get(i).getPoziomUmiejetnosci() * 2);
				suma = listaSzansNaGola[i];
			} else if (zawodnicyZPola.get(i).getPozycja().equals(PozycjaZawodnika.NAPASTNIK)) {
				listaSzansNaGola[i] = suma + (zawodnicyZPola.get(i).getPoziomUmiejetnosci() * 3);
				suma = listaSzansNaGola[i];
			}
		}
		int randomNumer = ThreadLocalRandom.current().nextInt(1, suma);
		for (int i = 0; i < listaSzansNaGola.length; i++) {
			if (randomNumer < listaSzansNaGola[i]) {
				if (i > 0) return zawodnicyZPola.get(i - 1).getNumerGracza();
			}
		}
		return zawodnicyZPola.get(zawodnicyZPola.size() - 1).getNumerGracza();
	}

	private NazwaDruzyny ustalDruzyneAtakujaca() {
		Integer silaDruzynyKomputera = getSilaDruzyny(Druzyny.getZawodnicyDruzynyKomputera());
		Integer silaDruzynyGracza = getSilaDruzyny(Druzyny.getZawodnicyDruzynyGracza());

		Integer silaPomocyDruzynyKomputera = getSilaPomocy(Druzyny.getZawodnicyDruzynyKomputera());
		Integer silaPomocyDruzynyGracza = getSilaPomocy(Druzyny.getZawodnicyDruzynyGracza());

		double szansaKomputera = wyliczenieSzansyNaSytuacje(silaDruzynyKomputera, silaDruzynyGracza, silaPomocyDruzynyGracza, silaPomocyDruzynyKomputera) * 100;
		int randomNumer = ThreadLocalRandom.current().nextInt(1, 101);

		if (randomNumer <= szansaKomputera) return NazwaDruzyny.KOMPUTER;
		else return NazwaDruzyny.GRACZ;
	}

	private double wyliczenieSzansyNaSytuacje(Integer silaDruzynyKomputera, Integer silaDruzynyGracza,
											  Integer silaPomocyDruzynyGracza, Integer silaPomocyDruzynyKomputera) {
		double wynikSilDruzyny = (double)silaDruzynyKomputera / ((double)silaDruzynyKomputera + (double)silaDruzynyGracza);
		double wynikPomocyDruzyny = (double)silaPomocyDruzynyKomputera / ((double)silaPomocyDruzynyKomputera + (double)silaPomocyDruzynyGracza);
		double szansaKomputera = (wynikSilDruzyny + wynikPomocyDruzyny) /2;

		if (szansaKomputera > 0.8) szansaKomputera = 0.8;
		else if (szansaKomputera < 0.2) szansaKomputera = 0.2;
		return szansaKomputera;
	}

	private Integer getSilaDruzyny(List<Zawodnik> listaZawodnikow) {
		return listaZawodnikow.stream()
				.map(Zawodnik::getPoziomUmiejetnosci)
				.reduce(0, Integer::sum);
	}

	private Integer getSilaPomocy(List<Zawodnik> listaZawodnikow) {
		return listaZawodnikow.stream()
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.POMOCNIK))
				.map(Zawodnik::getPoziomUmiejetnosci)
				.reduce(0, Integer::sum);
	}

	private DaneZdarzenia przygotowanieZdarzeniaSpalony() {
		int randomNumer = ThreadLocalRandom.current().nextInt(1, 2);
		return new DaneZdarzenia.Builder()
				.nazwaZdarzenia(NazwyZdarzen.FAUL)
				.druzynaAtakujaca((randomNumer < 2) ? NazwaDruzyny.KOMPUTER : NazwaDruzyny.GRACZ)
				.numerGracza(wylosujGraczaZPola((randomNumer < 2) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza()))
				.build();
	}

	private DaneZdarzenia przygotowanieZdarzeniaFaulu() {
		int randomNumer = ThreadLocalRandom.current().nextInt(1, 2);
		return new DaneZdarzenia.Builder()
				.nazwaZdarzenia(NazwyZdarzen.FAUL)
				.druzynaAtakujaca((randomNumer < 2) ? NazwaDruzyny.KOMPUTER : NazwaDruzyny.GRACZ)
				.numerGracza(wylosujGracza((randomNumer < 2) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza()))
				.build();
	}

	private int wylosujGracza(ArrayList<Zawodnik> zawodnicy) {
		int randomNumer = ThreadLocalRandom.current().nextInt(1, zawodnicy.size());
		return zawodnicy.get(randomNumer - 1).getNumerGracza();
	}

	private int wylosujGraczaZPola(List<Zawodnik> zawodnicy) {
		List<Zawodnik> zawodnicyZPola = getZawodnikowZPola(zawodnicy);
		int randomNumer = ThreadLocalRandom.current().nextInt(1, zawodnicyZPola.size());
		return zawodnicyZPola.get(randomNumer - 1).getNumerGracza();
	}

	private List<Zawodnik> getZawodnikowZPola(List<Zawodnik> zawodnicy) {
		return zawodnicy.stream()
				.filter(z -> !z.getPozycja().equals(PozycjaZawodnika.BRAMKARZ))
				.collect(Collectors.toList());
	}

	private DaneZdarzenia przygotowanieZdarzeniaRzutuKarnego() {
		NazwaDruzyny druzynaAtakujaca = ustalDruzyneAtakujaca();
		int numerGracza = ustalStrzelca((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyGracza() : Druzyny.getZawodnicyDruzynyKomputera());

		return new DaneZdarzenia.Builder()
				.nazwaZdarzenia(NazwyZdarzen.RZUTKARNY)
				.druzynaAtakujaca(druzynaAtakujaca)
				.druzynaBroniaca((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? NazwaDruzyny.GRACZ : NazwaDruzyny.KOMPUTER)
				.numerGracza(numerGracza)
				.silaStrzelca(getSilaGracza((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyGracza() : Druzyny.getZawodnicyDruzynyKomputera(), numerGracza))
				.silaBramkarza(getSilaBramkarza((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza()))
				.build();
	}

	private DaneZdarzenia przygotowanieZdarzeniaRzutuWolnego() {
		NazwaDruzyny druzynaAtakujaca = ustalDruzyneAtakujaca();
		int numerGracza = ustalStrzelca((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyGracza() : Druzyny.getZawodnicyDruzynyKomputera());

		return new DaneZdarzenia.Builder()
				.nazwaZdarzenia(NazwyZdarzen.RZUTWOLNY)
				.druzynaAtakujaca(druzynaAtakujaca)
				.druzynaBroniaca((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? NazwaDruzyny.GRACZ : NazwaDruzyny.KOMPUTER)
				.numerGracza(numerGracza)
				.silaStrzelca(getSilaGracza((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyGracza() : Druzyny.getZawodnicyDruzynyKomputera(), numerGracza))
				.silaBramkarza(getSilaBramkarza((druzynaAtakujaca.equals(NazwaDruzyny.KOMPUTER)) ? Druzyny.getZawodnicyDruzynyKomputera() : Druzyny.getZawodnicyDruzynyGracza()))
				.build();
	}
}
