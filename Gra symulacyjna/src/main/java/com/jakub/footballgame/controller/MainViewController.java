/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller;

import com.jakub.footballgame.config.FxmlView;
import com.jakub.footballgame.config.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class MainViewController {

	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	public void startNewGame(ActionEvent event) {
		stageManager.switchScene(FxmlView.TACTICSVIEW);
	}

}
