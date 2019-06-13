/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller;

import com.jakub.footballgame.config.FxmlView;
import com.jakub.footballgame.config.StageManager;
import com.jakub.footballgame.controller.tableModel.MeczTableObject;
import com.jakub.footballgame.logic.druzyna.Druzyny;
import com.jakub.footballgame.logic.druzyna.PozycjaZawodnika;
import com.jakub.footballgame.logic.druzyna.Zawodnik;
import com.jakub.footballgame.logic.zdarzenia.WyborZdarzenia;
import com.jakub.footballgame.logic.zdarzenia.Zdarzenie;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
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
	private ObservableList<MeczTableObject> listaAkcji = FXCollections.observableArrayList();
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
	private TableView<MeczTableObject> tableMecz;
	@FXML
	private TableColumn<MeczTableObject, String> colMinuta;
	@FXML
	private TableColumn<MeczTableObject, String> colAkcja;

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
		minutaMeczu = 0;
		wczytajZawodnikowGracza();
		wczytajZawodnikowKomputera();
		ustawDaneMeczu();
		ustawienieStatystyk();
		wczytajZdarzeniaMeczowe();
		wyswietlKomunikatORozpoczeciuMeczu();
		grajMecz();
	}

	private void grajMecz() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					wykonajZdarzenieMeczowe();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (minutaMeczu >= 90) {
					timer.cancel();
					komunikatOZakonczeniuMeczu();
				}
			}
		}, 0l, 3000l);
	}

	private void komunikatOZakonczeniuMeczu() {
		String text = "Koniec czasu drugiej połowy, sędzia kończy mecz. " + getWynikKomputera().toString() + ":" + getWynikGracza().toString();
		MeczTableObject akcja = new MeczTableObject(minutaMeczu + 1, text);
		listaAkcji.addAll(akcja);
		btnZagrajPonownie.setDisable(false);
		tableMecz.sort();
	}

	private void wykonajZdarzenieMeczowe() throws Exception {
		minutaMeczu += 3;
		WyborZdarzenia wyborZdarzenia = new WyborZdarzenia();
		Zdarzenie zdarzenie = wyborZdarzenia.wybierzZlecenie();
		listaAkcji.addAll(new MeczTableObject(minutaMeczu, zdarzenie.efektZdarzenia().zwrocEfektZdarzenia()));
		tableMecz.sort();
		Platform.runLater(() -> {
			ustawDaneMeczu();
			odswiezStatystykiGraczy();
		});
	}

	private void odswiezStatystykiGraczy() {
		tableGraczeKomputera.refresh();
		tableGraczeGracza.refresh();
		ustawienieStatystyk();
	}

	private void wyswietlKomunikatORozpoczeciuMeczu() {
		String text = "Witamy na spotkaniu, które mamy nadzieję dostarczy nam dużo emocji. Drużyna komputera rozpocznie " +
				"to spotkaniu w ustawieniu " + getTaktyka(listaZawodnikowKomputera) + " , natomiast drużyna gracza w ustawieniu " +
				getTaktyka(listaZawodnikowGracza) + ".";
		MeczTableObject akcja = new MeczTableObject(minutaMeczu, text);
		listaAkcji.addAll(akcja);
	}

	private void ustawienieStatystyk() {
		silaDruzynyGracza.setText(getSilaDruzyny(listaZawodnikowGracza).toString());
		silaDruzynyKomputera.setText(getSilaDruzyny(listaZawodnikowKomputera).toString());
		taktykaKomputera.setText(getTaktyka(listaZawodnikowKomputera));
		taktykaGracza.setText(getTaktyka(listaZawodnikowGracza));
	}

	private String getTaktyka(ObservableList<Zawodnik> listaZawodnikow) {
		long liczbaObroncow = listaZawodnikow.stream()
				.filter(zawodnik -> zawodnik.getLiczbaCzerwonychKartek() == 0)
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.OBRONCA))
				.count();
		long liczbaPomocnikow = listaZawodnikow.stream()
				.filter(zawodnik -> zawodnik.getLiczbaCzerwonychKartek() == 0)
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.POMOCNIK))
				.count();
		long liczbaNapastnikow = listaZawodnikow.stream()
				.filter(zawodnik -> zawodnik.getLiczbaCzerwonychKartek() == 0)
				.filter(z -> z.getPozycja().equals(PozycjaZawodnika.NAPASTNIK))
				.count();

		return liczbaObroncow + "-" + liczbaPomocnikow + "-" + liczbaNapastnikow;
	}

	private Integer getSilaDruzyny(ObservableList<Zawodnik> listaZawodnikow) {
		return listaZawodnikow.stream()
				.filter(zawodnik -> zawodnik.getLiczbaCzerwonychKartek() == 0)
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

	private void wczytajZdarzeniaMeczowe() {
		colMinuta.setCellValueFactory(new PropertyValueFactory<>("minuta"));
		colAkcja.setCellValueFactory(new PropertyValueFactory<>("akcja"));

		colAkcja.setCellFactory(new Callback<TableColumn<MeczTableObject, String>, TableCell<MeczTableObject, String>>() {
			@Override
			public TableCell<MeczTableObject, String> call(
					TableColumn<MeczTableObject, String> param) {
				TableCell<MeczTableObject, String> cell = new TableCell<>();
				Text text = new Text();
				cell.setGraphic(text);
				cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
				text.wrappingWidthProperty().bind(cell.widthProperty());
				text.textProperty().bind(cell.itemProperty());
				return cell;
			}
		});
		listaAkcji.clear();
		tableMecz.setItems(listaAkcji);

		tableMecz.getSortOrder().add(colMinuta);
		colAkcja.setSortType(TableColumn.SortType.DESCENDING);
		colAkcja.setSortable(true);
	}

	public void zagrajPonownie(ActionEvent actionEvent) {
		stageManager.switchScene(FxmlView.TACTICSVIEW);
	}

	public void zakonczGre(ActionEvent actionEvent) {
		Platform.exit();
	}
}
