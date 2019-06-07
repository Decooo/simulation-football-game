/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller.tableModel;

import com.jakub.footballgame.logic.druzyna.Zawodnik;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ZawodnikTableObject {
	private IntegerProperty numer = new SimpleIntegerProperty(0);
	private IntegerProperty sila = new SimpleIntegerProperty(0);
	private StringProperty pozycja = new SimpleStringProperty("");

	public final String getPozycja() {
		return this.pozycjaProperty().get();
	}

	public final StringProperty pozycjaProperty() {
		return this.pozycja;
	}

	public final void setPozycja(final String pozycja) {
		this.pozycjaProperty().set(pozycja);
	}

	public final int getNumer() {
		return this.numerProperty().get();
	}

	public final IntegerProperty numerProperty() {
		return this.numer;
	}

	public final void setNumer(final int numer) {
		this.numerProperty().set(numer);
	}

	public final int getSila() {
		return this.silaProperty().get();
	}

	public final IntegerProperty silaProperty() {
		return this.sila;
	}

	public final void setSila(final int sila) {
		this.silaProperty().set(sila);
	}

	public ObservableList<ZawodnikTableObject> zawodnikTableObject(List<Zawodnik> zawodnicy) {
		ObservableList<ZawodnikTableObject> zawodnicyTableObjects = FXCollections.observableArrayList();
		for (Zawodnik z : zawodnicy) {
			ZawodnikTableObject zaw = new ZawodnikTableObject();
			zaw.setSila(z.getPoziomUmiejetnosci());
			zaw.setNumer(z.getNumerGracza());

			zawodnicyTableObjects.add(zaw);
		}

		return zawodnicyTableObjects;
	}
}
