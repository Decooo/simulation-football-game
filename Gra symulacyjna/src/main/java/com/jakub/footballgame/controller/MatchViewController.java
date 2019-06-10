/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller;

import com.jakub.footballgame.config.FxmlView;
import com.jakub.footballgame.config.StageManager;
import com.jakub.footballgame.logic.druzyna.Druzyny;
import com.jakub.footballgame.logic.druzyna.PozycjaZawodnika;
import com.jakub.footballgame.logic.druzyna.Zawodnik;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class MatchViewController implements Initializable {

	@Lazy
	@Autowired
	private StageManager stageManager;

	private ObservableList<Zawodnik> listaZawodnikowGracza = FXCollections.observableArrayList();
	private ObservableList<Zawodnik> listaZawodnikowKomputera = FXCollections.observableArrayList();
	private int minutaMeczu = 0;

	@FXML
	private TableView<Zawodnik> tableGraczeKomputera;
	@FXML
	private TableColumn<Zawodnik, Integer> colNumerK;
	@FXML
	private TableColumn<Zawodnik, Integer> colSilaK;
	@FXML
	private TableColumn<Zawodnik, String> colPozycjaK;
	@FXML
	private TableColumn<Zawodnik, Integer> colGoleK;
	@FXML
	private TableColumn<Zawodnik, Integer> colZKK;
	@FXML
	private TableColumn<Zawodnik, Integer> colCZKK;

	@FXML
	private TableView<Zawodnik> tableGraczeGracza;
	@FXML
	private TableColumn<Zawodnik, Integer> colNumerG;
	@FXML
	private TableColumn<Zawodnik, Integer> colSilaG;
	@FXML
	private TableColumn<Zawodnik, String> colPozycjaG;
	@FXML
	private TableColumn<Zawodnik, Integer> colGoleG;
	@FXML
	private TableColumn<Zawodnik, Integer> colZKG;
	@FXML
	private TableColumn<Zawodnik, Integer> colCZKG;

	@FXML
	private Label labelWynikKomputera;
	@FXML
	private Label labelWynikGracza;
	@FXML
	private Label labelMinuta;
	@FXML
	private Label silaDruzynyGracza;
	@FXML
	private Label silaDruzynyKomputera;
	@FXML
	private Label taktykaKomputera;
	@FXML
	private Label taktykaGracza;

	@FXML
	private Button btnZagrajPonownie;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		wczytajZawodnikowGracza();
		wczytajZawodnikowKomputera();
		ustawDaneMeczu();
		ustawienieStatystyk();
		wyswietlKomunikatORozpoczeciuMeczu();
		grajMecz();
	}
	
	private void grajMecz() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				wykonajZdarzenieMeczowe();
				if(minutaMeczu>=90) {
					timer.cancel();
					komunikatOZakonczeniuMeczu();
				}
			}
		}, 0l,2500l);
	}

	private void wyswietlKomunikatORozpoczeciuMeczu() {
		//TODO implementacja
	}
	private void komunikatOZakonczeniuMeczu() {
		//TODO implementacja
		btnZagrajPonownie.setDisable(false);
	}

	private void wykonajZdarzenieMeczowe() {
		minutaMeczu+=3;
		//TODO implementacja
		//ustal zdarzenie
		//wykonaj zdarzenie
		//wyświetl raport zdarzenia
		//zaaktualizuj dane meczu i zawodnikow
	}

	private void ustawienieStatystyk() {
		silaDruzynyGracza.setText(getSilaDruzyny(listaZawodnikowGracza).toString());
		silaDruzynyKomputera.setText(getSilaDruzyny(listaZawodnikowKomputera).toString());
		taktykaKomputera.setText(getTaktyka(listaZawodnikowKomputera));
		taktykaGracza.setText(getTaktyka(listaZawodnikowGracza));
	}

	private String getTaktyka(ObservableList<Zawodnik> listaZawodnikow) {
		long liczbaObroncow = listaZawodnikow.stream()
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.OBRONCA))
				.count();
		long liczbaPomocnikow = listaZawodnikow.stream()
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.POMOCNIK))
				.count();
		long liczbaNapastnikow = listaZawodnikow.stream()
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.NAPASTNIK))
				.count();

		return liczbaObroncow + "-" + liczbaPomocnikow + "-" + liczbaNapastnikow;
	}

	private Integer getSilaDruzyny(ObservableList<Zawodnik> listaZawodnikow) {
		return listaZawodnikow.stream()
				.map(Zawodnik::getPoziomUmiejetnosci)
				.reduce(0, Integer::sum);
	}

	private void ustawDaneMeczu() {
		labelMinuta.setText(String.valueOf(minutaMeczu));
		labelWynikGracza.setText(getWynikGracza().toString());
		labelWynikKomputera.setText(getWynikKomputera().toString());
	}

	private Integer getWynikGracza() {
		return listaZawodnikowGracza.stream()
				.map(Zawodnik::getLiczbaGoli)
				.reduce(0, Integer::sum);
	}

	private Integer getWynikKomputera() {
		return listaZawodnikowKomputera.stream()
				.map(Zawodnik::getLiczbaGoli)
				.reduce(0, Integer::sum);
	}

	private void wczytajZawodnikowGracza() {
		colNumerG.setCellValueFactory(new PropertyValueFactory<>("numerGracza"));
		colSilaG.setCellValueFactory(new PropertyValueFactory<>("poziomUmiejetnosci"));
		colPozycjaG.setCellValueFactory(new PropertyValueFactory<>("pozycja"));
		colGoleG.setCellValueFactory(new PropertyValueFactory<>("liczbaGoli"));
		colZKG.setCellValueFactory(new PropertyValueFactory<>("liczbaZoltychKartek"));
		colCZKG.setCellValueFactory(new PropertyValueFactory<>("liczbaCzerwonychKartek"));

		listaZawodnikowGracza.clear();
		listaZawodnikowGracza.addAll(Druzyny.getZawodnicyDruzynyGracza());
		tableGraczeGracza.setItems(listaZawodnikowGracza);
	}

	private void wczytajZawodnikowKomputera() {
		colNumerK.setCellValueFactory(new PropertyValueFactory<>("numerGracza"));
		colSilaK.setCellValueFactory(new PropertyValueFactory<>("poziomUmiejetnosci"));
		colPozycjaK.setCellValueFactory(new PropertyValueFactory<>("pozycja"));
		colGoleK.setCellValueFactory(new PropertyValueFactory<>("liczbaGoli"));
		colZKK.setCellValueFactory(new PropertyValueFactory<>("liczbaZoltychKartek"));
		colCZKK.setCellValueFactory(new PropertyValueFactory<>("liczbaCzerwonychKartek"));

		listaZawodnikowKomputera.clear();
		listaZawodnikowKomputera.addAll(Druzyny.getZawodnicyDruzynyKomputera());
		tableGraczeKomputera.setItems(listaZawodnikowKomputera);
	}

	public void zagrajPonownie(ActionEvent actionEvent) {
		stageManager.switchScene(FxmlView.TACTICSVIEW);
	}

	public void zakonczGre(ActionEvent actionEvent) {
		Platform.exit();
	}
}
