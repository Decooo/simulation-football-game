/*
 * Autor: Jakub Kuśnierz
 * Data: 2019
 */

package com.jakub.footballgame.controller;

import com.jakub.footballgame.logic.druzyna.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class TacticsViewController implements Initializable {

	private Random random = new Random();
	private Taktyka[] taktyka = Taktyka.values();
	private PoziomSilyDruzyny[] poziomSilyDruzyny = PoziomSilyDruzyny.values();

	@FXML
	private GridPane gridKomputera;
	@FXML
	private TableView<Zawodnik> tableZawodnicy;
	@FXML
	private TableColumn<Zawodnik, Integer> colNumer;
	@FXML
	private TableColumn<Zawodnik, Integer> colSila;

	private ObservableList<Zawodnik> listaZawodnikowGracza = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Druzyny druzyna = new Druzyny();
		druzyna.stworzDruzyneKomputera(losujPoziomSilyDruzyny(), losujTaktyke());
		druzyna.stworzDruzyneGracza(losujPoziomSilyDruzyny());

		ustawDruzyneKomputeraNaBoisku();
		wczytajZawodnikowGracza();
	}

	private void wczytajZawodnikowGracza() {
		colNumer.setCellValueFactory(new PropertyValueFactory<>("numerGracza"));
		colSila.setCellValueFactory(new PropertyValueFactory<>("poziomUmiejetnosci"));

		listaZawodnikowGracza.clear();
		listaZawodnikowGracza.addAll(Druzyny.getZawodnicyDruzynyGracza());
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
			if(wariant == 1){
				AtomicInteger i = new AtomicInteger(1);
				zawodnicy.forEach(zawodnik -> {
					gridKomputera.add(rysujPilkarza(zawodnik.getPoziomUmiejetnosci()), i.get(), numerLinii);
					i.getAndIncrement();
				});
			}else{
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
}