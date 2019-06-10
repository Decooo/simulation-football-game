/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller.tableModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MeczTableObject {

	private IntegerProperty minuta = new SimpleIntegerProperty(0);
	private StringProperty akcja = new SimpleStringProperty("");

	public final String getAkcja() {
		return this.akcjaProperty().get();
	}

	public final StringProperty akcjaProperty() {
		return this.akcja;
	}

	public final void setAkcja(final String akcja) {
		this.akcjaProperty().set(akcja);
	}

	public final int getMinuta() {
		return this.minutaProperty().get();
	}

	public final IntegerProperty minutaProperty() {
		return this.minuta;
	}

	public final void setMinuta(final int minuta) {
		this.minutaProperty().set(minuta);
	}

	public MeczTableObject(int minuta, String akcja) {
		setMinuta(minuta);
		setAkcja(akcja);
	}
}
