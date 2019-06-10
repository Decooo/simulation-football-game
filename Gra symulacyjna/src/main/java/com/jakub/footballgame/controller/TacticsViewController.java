/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller;

import com.jakub.footballgame.config.FxmlView;
import com.jakub.footballgame.config.StageManager;
import com.jakub.footballgame.controller.tableModel.ZawodnikTableObject;
import com.jakub.footballgame.logic.druzyna.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class TacticsViewController implements Initializable {

	@Lazy
	@Autowired
	private StageManager stageManager;

	private Random random = new Random();
	private Taktyka[] taktyka = Taktyka.values();
	private PoziomSilyDruzyny[] poziomSilyDruzyny = PoziomSilyDruzyny.values();
	private HashMap<String, PozycjaZawodnika> pozycje = new HashMap<>();
	private HashMap<String, Integer[]> miejsceRysowaniaPozycji = new HashMap<>();

	@FXML
	private GridPane gridKomputera;
	@FXML
	private GridPane gridGracza;
	@FXML
	private TableView<ZawodnikTableObject> tableZawodnicy;
	@FXML
	private TableColumn<ZawodnikTableObject, Integer> colNumer;
	@FXML
	private TableColumn<ZawodnikTableObject, Integer> colSila;
	@FXML
	private TableColumn<ZawodnikTableObject, ZawodnikTableObject> colPozycja;

	private ObservableList<ZawodnikTableObject> listaZawodnikowGracza = FXCollections.observableArrayList();
	private ObservableList<String> listaPozycji;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Druzyny druzyna = new Druzyny();
		druzyna.stworzDruzyneKomputera(losujPoziomSilyDruzyny(), losujTaktyke());
		druzyna.stworzDruzyneGracza(losujPoziomSilyDruzyny());

		ustawDruzyneKomputeraNaBoisku();
		wczytajZawodnikowGracza();
	}

	private void wczytajZawodnikowGracza() {
		colNumer.setCellValueFactory(new PropertyValueFactory<>("numer"));
		colSila.setCellValueFactory(new PropertyValueFactory<>("sila"));
		colPozycja.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue()));

		dodanieComboBoxaZPozycja();
		listaZawodnikowGracza.clear();
		ZawodnikTableObject zawodnikTableObject = new ZawodnikTableObject();
		listaZawodnikowGracza = zawodnikTableObject.zawodnikTableObject(Druzyny.getZawodnicyDruzynyGracza());
		tableZawodnicy.setItems(listaZawodnikowGracza);
	}

	private void ustawDruzyneKomputeraNaBoisku() {
		List<Zawodnik> zawodnicy = Druzyny.getZawodnicyDruzynyKomputera();

		ustawBramkarza(zawodnicy);
		ustawObroncow(zawodnicy);
		ustawPomocnikow(zawodnicy);
		ustawNapastnikow(zawodnicy);
	}

	private void ustawBramkarza(List<Zawodnik> zawodnicy) {
		Optional<Zawodnik> bramkarz = zawodnicy.stream()
				.filter(zawodnik -> zawodnik.getPozycja() == PozycjaZawodnika.BRAMKARZ)
				.findFirst();

		bramkarz.ifPresent(zawodnik -> gridKomputera.add(rysujPilkarza(zawodnik.getPoziomUmiejetnosci()), 2, 3));
	}

	private VBox rysujPilkarza(int sila) {
		Label dane = new Label(sila + "Q");
		dane.setAlignment(Pos.CENTER);
		dane.setStyle("-fx-font-weight: BOLD; -fx-text-fill: RED; -fx-font-size: 14");

		Circle znak = new Circle(11, Color.GRAY);

		VBox vb = new VBox();
		vb.getChildren().addAll(dane, znak);
		vb.setAlignment(Pos.CENTER);
		return vb;
	}

	private void ustawObroncow(List<Zawodnik> zawodnicy) {
		List<Zawodnik> obroncy = zawodnicy.stream()
				.filter(zawodnik -> zawodnik.getPozycja() == PozycjaZawodnika.OBRONCA)
				.collect(Collectors.toList());

		rozmiescPilkarzyWLinii(obroncy, 2);
	}

	private void ustawPomocnikow(List<Zawodnik> zawodnicy) {
		List<Zawodnik> pomocnicy = zawodnicy.stream()
				.filter(zawodnik -> zawodnik.getPozycja() == PozycjaZawodnika.POMOCNIK)
				.collect(Collectors.toList());

		rozmiescPilkarzyWLinii(pomocnicy, 1);
	}

	private void ustawNapastnikow(List<Zawodnik> zawodnicy) {
		List<Zawodnik> napastnicy = zawodnicy.stream()
				.filter(zawodnik -> zawodnik.getPozycja() == PozycjaZawodnika.NAPASTNIK)
				.collect(Collectors.toList());

		rozmiescPilkarzyWLinii(napastnicy, 0);
	}

	private void rozmiescPilkarzyWLinii(List<Zawodnik> zawodnicy, int numerLinii) {
		if (zawodnicy.size() == 1) {
			zawodnicy.forEach(zawodnik ->
					gridKomputera.add(rysujPilkarza(zawodnik.getPoziomUmiejetnosci()), 2, numerLinii));
		} else if (zawodnicy.size() == 2) {
			AtomicInteger i = new AtomicInteger(1);
			rysujZpominieciemSrodkowejPozycji(zawodnicy, numerLinii, i);
		} else if (zawodnicy.size() == 3) {
			int wariant = random.nextInt(2);
			if (wariant == 1) {
				AtomicInteger i = new AtomicInteger(1);
				zawodnicy.forEach(zawodnik -> {
					gridKomputera.add(rysujPilkarza(zawodnik.getPoziomUmiejetnosci()), i.get(), numerLinii);
					i.getAndIncrement();
				});
			} else {
				AtomicInteger i = new AtomicInteger(0);
				zawodnicy.forEach(zawodnik -> {
					gridKomputera.add(rysujPilkarza(zawodnik.getPoziomUmiejetnosci()), i.get(), numerLinii);
					i.getAndIncrement();
					i.getAndIncrement();
				});
			}

		} else if (zawodnicy.size() == 4) {
			AtomicInteger i = new AtomicInteger(0);
			rysujZpominieciemSrodkowejPozycji(zawodnicy, numerLinii, i);
		} else if (zawodnicy.size() == 5) {
			AtomicInteger i = new AtomicInteger(0);
			zawodnicy.forEach(zawodnik -> {
				gridKomputera.add(rysujPilkarza(zawodnik.getPoziomUmiejetnosci()), i.get(), numerLinii);
				i.getAndIncrement();
			});
		}
	}

	private void rysujZpominieciemSrodkowejPozycji(List<Zawodnik> zawodnicy, int numerLinii, AtomicInteger i) {
		zawodnicy.forEach(zawodnik -> {
			gridKomputera.add(rysujPilkarza(zawodnik.getPoziomUmiejetnosci()), i.get(), numerLinii);
			i.getAndIncrement();
			if (i.get() == 2) i.getAndIncrement();
		});
	}

	private Taktyka losujTaktyke() {
		return taktyka[random.nextInt(taktyka.length)];
	}

	private PoziomSilyDruzyny losujPoziomSilyDruzyny() {
		return poziomSilyDruzyny[random.nextInt(poziomSilyDruzyny.length)];
	}

	private void dodanieComboBoxaZPozycja() {
		wypelnienieListyPozycji();

		colPozycja.setCellFactory(param -> new TableCell<>() {
			@Override
			protected void updateItem(ZawodnikTableObject item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
				} else {
					ComboBox<String> cb = new ComboBox<>(listaPozycji);
					cb.getSelectionModel().select(0);
					cb.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
						if (!newValue.equals("")) {
							boolean isAlreadySetPosition = false;
							for (ZawodnikTableObject zaw : listaZawodnikowGracza) {
								if (zaw.getPozycja().equals(newValue)) {
									isAlreadySetPosition = true;
									break;
								}
							}
							if (!isAlreadySetPosition) {
								item.pozycjaProperty().set(newValue);
							} else {
								showAlertPositionAlreadySet();
								item.pozycjaProperty().set(newValue);
								cb.getSelectionModel().select(listaPozycji.indexOf(oldValue));
							}
						} else if (!oldValue.equals("")) {
							item.pozycjaProperty().set(newValue);
						} else item.pozycjaProperty().set(newValue);

						odswiezPlanszeGracza();
					});
					setGraphic(cb);
					setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				}
			}

			private void showAlertPositionAlreadySet() {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Błąd");
				alert.setHeaderText(null);
				alert.setContentText("Na wybranej pozycji został już ustawiony inny gracz");
				alert.showAndWait();
			}
		});
	}

	private void odswiezPlanszeGracza() {
		usunStarePozycjeZPlanszy();
		narysujZawodnikowNaPlanszy();
	}

	private void usunStarePozycjeZPlanszy() {
		ObservableList<Node> nodes = gridGracza.getChildren();
		List<Node> node = nodes.stream()
				.filter(n -> !(n instanceof Pane))
				.collect(Collectors.toList());
		gridGracza.getChildren().clear();
		for (Node aNode : node) {
			gridGracza.getChildren().addAll(aNode);
		}
	}

	private void narysujZawodnikowNaPlanszy() {
		List<ZawodnikTableObject> zawodnicy = listaZawodnikowGracza.stream()
				.filter(z -> !z.getPozycja().equals(""))
				.collect(Collectors.toList());
		for (ZawodnikTableObject zaw : zawodnicy) {
			Integer[] pozycjaNaPlanszy = miejsceRysowaniaPozycji.get(zaw.getPozycja());
			gridGracza.add(rysujPilkarza(zaw.getSila()), pozycjaNaPlanszy[0], pozycjaNaPlanszy[1]);
		}
	}

	private void wypelnienieListyPozycji() {
		pozycje.put("", PozycjaZawodnika.NIEOKRESLONA);
		pozycje.put("Bramkarz", PozycjaZawodnika.BRAMKARZ);

		pozycje.put("Lewy obrońca", PozycjaZawodnika.OBRONCA);
		pozycje.put("Lewy środkowy obrońca", PozycjaZawodnika.OBRONCA);
		pozycje.put("Srodkowy obrońca", PozycjaZawodnika.OBRONCA);
		pozycje.put("Prawy srodkowy obrońca", PozycjaZawodnika.OBRONCA);
		pozycje.put("Prawy obrońca", PozycjaZawodnika.OBRONCA);

		pozycje.put("Lewy pomocnik", PozycjaZawodnika.POMOCNIK);
		pozycje.put("Lewy środkowy pomocnik", PozycjaZawodnika.POMOCNIK);
		pozycje.put("Srodkowy pomocnik", PozycjaZawodnika.POMOCNIK);
		pozycje.put("Prawy srodkowy pomocnik", PozycjaZawodnika.POMOCNIK);
		pozycje.put("Prawy pomocnik", PozycjaZawodnika.POMOCNIK);

		pozycje.put("Lewy napastnik", PozycjaZawodnika.NAPASTNIK);
		pozycje.put("Lewy środkowy napastnik", PozycjaZawodnika.NAPASTNIK);
		pozycje.put("Srodkowy napastnik", PozycjaZawodnika.NAPASTNIK);
		pozycje.put("Prawy srodkowy napastnik", PozycjaZawodnika.NAPASTNIK);
		pozycje.put("Prawy napastnik", PozycjaZawodnika.NAPASTNIK);

		listaPozycji = FXCollections.observableArrayList(pozycje.keySet());
		wypelnienieMiejscRysowaniaPozycji();
	}

	private void wypelnienieMiejscRysowaniaPozycji() {
		miejsceRysowaniaPozycji.put("Bramkarz", new Integer[]{2, 3});

		miejsceRysowaniaPozycji.put("Lewy obrońca", new Integer[]{0, 2});
		miejsceRysowaniaPozycji.put("Lewy środkowy obrońca", new Integer[]{1, 2});
		miejsceRysowaniaPozycji.put("Srodkowy obrońca", new Integer[]{2, 2});
		miejsceRysowaniaPozycji.put("Prawy srodkowy obrońca", new Integer[]{3, 2});
		miejsceRysowaniaPozycji.put("Prawy obrońca", new Integer[]{4, 2});

		miejsceRysowaniaPozycji.put("Lewy pomocnik", new Integer[]{0, 1});
		miejsceRysowaniaPozycji.put("Lewy środkowy pomocnik", new Integer[]{1, 1});
		miejsceRysowaniaPozycji.put("Srodkowy pomocnik", new Integer[]{2, 1});
		miejsceRysowaniaPozycji.put("Prawy srodkowy pomocnik", new Integer[]{3, 1});
		miejsceRysowaniaPozycji.put("Prawy pomocnik", new Integer[]{4, 1});

		miejsceRysowaniaPozycji.put("Lewy napastnik", new Integer[]{0, 0});
		miejsceRysowaniaPozycji.put("Lewy środkowy napastnik", new Integer[]{1, 0});
		miejsceRysowaniaPozycji.put("Srodkowy napastnik", new Integer[]{2, 0});
		miejsceRysowaniaPozycji.put("Prawy srodkowy napastnik", new Integer[]{3, 0});
		miejsceRysowaniaPozycji.put("Prawy napastnik", new Integer[]{4, 0});
	}

	public void rozpocznijMecz(ActionEvent actionEvent) {
		long liczbaGraczyBezPozycji = listaZawodnikowGracza.stream()
				.filter(z -> z.getPozycja().equals(""))
				.count();
		if (liczbaGraczyBezPozycji > 0)
			alertNieUstawionaPozycja();
		else {
			zapiszPozycjeGraczy();
			stageManager.switchScene(FxmlView.MATCHVIEW);
		}
	}

	private void zapiszPozycjeGraczy() {
		for (int i = 0; i < Druzyny.getZawodnicyDruzynyGracza().size(); i++) {
			int numer = Druzyny.getZawodnicyDruzynyGracza().get(i).getNumerGracza();
			Optional<ZawodnikTableObject> zaw = listaZawodnikowGracza.stream()
					.filter(z -> z.getNumer() == numer)
					.findFirst();
			final int index = i;
			zaw.ifPresent(z -> Druzyny.getZawodnicyDruzynyGracza().get(index).setPozycja(pozycje.get(z.getPozycja())));
		}
	}

	private void alertNieUstawionaPozycja() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Błąd");
		alert.setHeaderText(null);
		alert.setContentText("Nie można rozpocząć meczu, ponieważ nie wszyscy zawodnicy mają wybraną pozycję");
		alert.showAndWait();
	}

	public void zagrajZLosowymiPozycjami(ActionEvent actionEvent) {
		Druzyny druzyny = new Druzyny();
		druzyny.przypiszPozycjeLosowo(Druzyny.getZawodnicyDruzynyGracza(),losujTaktyke());
		stageManager.switchScene(FxmlView.MATCHVIEW);
	}
}