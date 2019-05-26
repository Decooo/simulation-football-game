/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.config;

import java.util.ResourceBundle;

public enum FxmlView {
	MAINVIEW {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("mainview.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/mainview.fxml";
		}
	};

	public abstract String getTitle();

	public abstract String getFxmlFile();

	String getStringFromResourceBundle(String key) {
		return ResourceBundle.getBundle("Bundle").getString(key);
	}
}
