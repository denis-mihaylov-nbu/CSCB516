package com.nbu.scm.view;

import java.util.List;

import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.Reservation;
import com.nbu.scm.controller.CourtController;
import com.nbu.scm.controller.ReservationController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReservationPanel extends GridPane {
	
	CalendarPane calendarPane;
	
	ReservationController reservationController = new ReservationController();
	CourtController courtController = new CourtController();
	
	Reservation reservation;
	Stage stage = new Stage();

	Label name = new Label("Name");
	Label date = new Label("Date");
	Label court = new Label("Court");
	TextField nameField = new TextField();
	Text dateField = new Text();
	ComboBox<Court> courtComboBox;
	
	Button save = new Button("OK");
	Button cancel = new Button("Cancel");

	public ReservationPanel(CalendarPane calendarPane) {
		this.calendarPane = calendarPane;
	}

	public void start(Reservation reservation) throws Exception {
		this.reservation = reservation;
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		
		dateField.setText(reservation.getTimestampAsString());
		dateField.setDisable(false);

		
		ObservableList<Court> courts = FXCollections.observableArrayList(
				courtController.getAvailableCourts(reservation.getClub().getId(), reservation.getTimestamp()));
		
		courtComboBox = new ComboBox<Court>(courts);
		

		save.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				saveReservation();
			}
		});

		cancel.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.close();
			}
		});

		add(date, 0, 0);
		add(dateField, 1, 0);
		
		if (courts.size() > 0) {
			courtComboBox.getSelectionModel().select(0);
			add(court, 0, 1);
			add(courtComboBox, 1, 1);
			add(name, 0, 2);
			add(nameField, 1, 2);
			add(save, 0, 3);
			add(cancel, 1, 3);
		} else {
			List<Reservation> reservations = reservationController.getByTimestamp(reservation.getTimestamp());
			int row = 1;
			for (int i = 0; i < reservations.size(); i++) {
				add(new Label(reservations.get(i).toShortString()), 0, row++);
			}
			add(cancel, 1, row++);
		}		

		Scene scene = new Scene(this, 300, 300);
		stage.setScene(scene);
		stage.show();
	}
	
	private void saveReservation() {
		reservation.setName(nameField.getText());
		reservation.setCourt(courtComboBox.getValue());
		try {
			reservationController.createReservation(reservation);
			calendarPane.init();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
		}
		stage.close();
	}

}
