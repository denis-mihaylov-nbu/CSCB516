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

public class ReservationPanel {
	
	CalendarPane calendarPane;
	
	ReservationController reservationController = new ReservationController();
	CourtController courtController = new CourtController();

	public ReservationPanel(CalendarPane calendarPane) {
		this.calendarPane = calendarPane;
	}

	public void start(Reservation reservation) throws Exception {
		Stage stage = new Stage();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Label date = new Label("Date");
		Text dateField = new Text(reservation.getTimestampAsString());
		dateField.setDisable(false);

		Label court = new Label("Court");
		
		ObservableList<Court> courts = FXCollections.observableArrayList(
				courtController.getAvailableCourts(reservation.getClub().getId(), reservation.getTimestamp()));
		
		ComboBox<Court> courtComboBox = new ComboBox<Court>(courts);
		
		Label name = new Label("Name");
		TextField nameField = new TextField();

		Button save = new Button("OK");
		save.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
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
		});

		Button cancel = new Button("Cancel");
		cancel.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.close();
			}
		});

		grid.add(date, 0, 0);
		grid.add(dateField, 1, 0);
		
		if (courts.size() > 0) {
			courtComboBox.getSelectionModel().select(0);
			grid.add(court, 0, 1);
			grid.add(courtComboBox, 1, 1);
			grid.add(name, 0, 2);
			grid.add(nameField, 1, 2);
			grid.add(save, 0, 3);
			grid.add(cancel, 1, 3);
		} else {
			List<Reservation> reservations = reservationController.getByTimestamp(reservation.getTimestamp());
			int row = 1;
			for (int i = 0; i < reservations.size(); i++) {
				grid.add(new Label(reservations.get(i).toShortString()), 0, row++);
			}
			grid.add(cancel, 1, row++);
		}
		

		Scene scene = new Scene(grid, 300, 300);
		stage.setScene(scene);
		stage.show();
	}

}
