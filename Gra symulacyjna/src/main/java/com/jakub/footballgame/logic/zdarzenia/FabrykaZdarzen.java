
/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.logic.zdarzenia;

public class FabrykaZdarzen implements IFabrykaZdarzen {

	@Override
	public Zdarzenie utworzZdarzenie(DaneZdarzenia daneZdarzenia) throws Exception {
		if(daneZdarzenia.getNazwaZdarzenia() == NazwyZdarzen.STRZAL){
			return new Strzal(daneZdarzenia.getDruzynaAtakujaca(),daneZdarzenia.getDruzynaBroniaca(),daneZdarzenia.getNumerGracza(),daneZdarzenia.getSilaStrzelca(),daneZdarzenia.getSilaBramkarza());
		}else if(daneZdarzenia.getNazwaZdarzenia() == NazwyZdarzen.RZUTKARNY){
			return new RzutKarny(daneZdarzenia.getDruzynaAtakujaca(),daneZdarzenia.getDruzynaBroniaca(),daneZdarzenia.getNumerGracza(),daneZdarzenia.getSilaStrzelca(),daneZdarzenia.getSilaBramkarza());
		}else if(daneZdarzenia.getNazwaZdarzenia() == NazwyZdarzen.RZUTWOLNY){
			return new RzutWolny(daneZdarzenia.getDruzynaAtakujaca(),daneZdarzenia.getDruzynaBroniaca(),daneZdarzenia.getNumerGracza(),daneZdarzenia.getSilaStrzelca(),daneZdarzenia.getSilaBramkarza());
		}else if(daneZdarzenia.getNazwaZdarzenia() == NazwyZdarzen.FAUL){
			return new Faul(daneZdarzenia.getDruzynaAtakujaca(),daneZdarzenia.getNumerGracza());
		}else if(daneZdarzenia.getNazwaZdarzenia() == NazwyZdarzen.SPALONY){
			return new Spalony(daneZdarzenia.getDruzynaAtakujaca(),daneZdarzenia.getNumerGracza());
		}else throw new Exception("Nie ma takiego zdarzenia");
	}
}
