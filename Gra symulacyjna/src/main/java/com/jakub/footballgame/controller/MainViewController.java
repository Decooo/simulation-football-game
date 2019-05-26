/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller;

import com.jakub.footballgame.config.FxmlView;
import com.jakub.footballgame.config.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainViewController implements Initializable {

	@Lazy
	@Autowired
	private StageManager stageManager;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	@FXML
	public void startNewGame(ActionEvent event) {
		stageManager.switchScene(FxmlView.TACTICSVIEW);
	}

}
