/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic;

import com.jakub.footballgame.logic.zdarzenia.*;

import java.util.concurrent.ThreadLocalRandom;

public class Client {
	public static void main(String[] args) throws Exception {
		IFabrykaZdarzen fabrykaZdarzen = new FabrykaZdarzen();
		DaneZdarzenia daneZdarzenia = new DaneZdarzenia.Builder()
				.nazwaZdarzenia(NazwyZdarzen.FAUL)
				.druzynaAtakujaca(Druzyna.KOMPUTER)
				.numerGracza(8)
				.build();
		Zdarzenie zdarzenie = fabrykaZdarzen.utworzZdarzenie(daneZdarzenia);
		System.out.println("efekt: = " + zdarzenie.efektZdarzenia().zwrocEfektZdarzenia());
		System.out.println("zdarzenie.getClass() = " + zdarzenie.getClass());
	}

	public void game(){
		//wylosowanie taktyki oraz poziomu sily druzyny dla komputera
		//wylosowanie zawodników do taktyki komputera
		//wylosowanie zawodników oraz poziomu ich sily dla gracza - (druzyna bez określonej pozycji)
		//ustawienie składu przez gracza (wybór taktyki, przypisanie graczom pozycji)
		//Rozegranie meczu - symulacja zdarzeń meczowych według prostego silnika meczeowego
		//wyswietlenie wyniku meczu
	}

}
