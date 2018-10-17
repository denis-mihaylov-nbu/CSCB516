package com.nbu.scm.view;

import com.nbu.scm.bean.Reservation;
import com.nbu.scm.controller.ReservationController;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReservationPanel {
	
	ReservationController reservationController = new ReservationController();

	public void start(Reservation reservation) {
		Stage stage = new Stage();			
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Label date = new Label("Date");
		Text dateField = new Text(reservation.getTimestampAsString());
		dateField.setDisable(false);
		Label court = new Label("Court");
		Text courtField = new Text(reservation.getCourt().getNumber() + "");
		courtField.setDisable(false);
		Label name = new Label("Name");
		TextField nameField = new TextField();
		
		Button save = new Button("OK");		
		save.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	reservation.setName(nameField.getText());
		    	
		    	// TODO call to BE to insert data in DB
		    	System.out.println(reservation);
		    	try {
					reservationController.createReservation(reservation);
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
		grid.add(court, 0, 1);
		grid.add(courtField, 1, 1);
		grid.add(name, 0, 2);
		grid.add(nameField, 1, 2);
		grid.add(save, 0, 3);
		grid.add(cancel, 1, 3);

		Scene scene = new Scene(grid, 300, 300);
		stage.setScene(scene);
		stage.show();
	}
	
}
