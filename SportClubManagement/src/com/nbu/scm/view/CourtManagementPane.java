package com.nbu.scm.view;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.CourtType;
import com.nbu.scm.bean.User;
import com.nbu.scm.controller.ClubController;
import com.nbu.scm.controller.CourtController;
import com.nbu.scm.controller.CourtTypeController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CourtManagementPane extends GridPane {
	
	private Label clubLabel = new Label("Club : ");
	private ComboBox<Club> clubComboBox = new ComboBox<Club>();
	private TextField clubField = new TextField();
	
	private Label courtLabel = new Label("Court : ");
	private ComboBox<Court> courtComboBox = new ComboBox<Court>();
	
	private Label numberLabel = new Label("Court number : ");
	private TextField numberTextField = new TextField();

	private Label courtTypeLabel = new Label("Type : ");
	private ComboBox<CourtType> courtTypeComboBox = new ComboBox<CourtType>();

	private Label priceLabel = new Label("Price : ");
	private TextField priceTextField = new TextField();
	
	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	private User loggedUser;
	private boolean isSuperAdmin = false;

	private Court court;
	private Club club;

	public CourtManagementPane(User loggedUser) throws Exception {
		this.loggedUser = loggedUser;
		this.club = loggedUser.getClub();
		this.isSuperAdmin = loggedUser.getId() == 1;
		init();
	}

	public void init() throws Exception {

		courtTypeComboBox.setItems(FXCollections.observableArrayList(CourtTypeController.getCourtTypes()));

		add(clubLabel, 0, 0);
		if (isSuperAdmin) {
			clubComboBox.setItems(FXCollections.observableArrayList(ClubController.getClubs()));
			add(clubComboBox, 1, 0);
		} else {
			clubField.setText(loggedUser.getClub().toString());
			clubField.setDisable(true);
			add(clubField, 1, 0);
		}
		add(courtLabel, 0, 1);
		if (loggedUser.getClub() != null) {
			courtComboBox.setItems(FXCollections.observableArrayList(loggedUser.getClub().getCourts()));
		}
		add(courtComboBox, 1, 1);
		add(numberLabel, 0, 2);
		add(numberTextField, 1, 2);
		add(courtTypeLabel, 0, 3);
		add(courtTypeComboBox, 1, 3);
		add(priceLabel, 0, 4);
		add(priceTextField, 1, 4);
		add(save, 0, 5);
		add(cancel, 1, 5);


		clubComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Club>() {

			@Override
			public void changed(ObservableValue<? extends Club> observable, Club oldValue, Club newValue) {
				handleClubComboBoxChange();
			}
			
		});
		

		courtComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Court>() {

			@Override
			public void changed(ObservableValue<? extends Court> observable, Court oldValue, Court newValue) {
				handleCourtComboBoxChange();
			}

		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleSaveButton(event);
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleCancelButton(event);
			}
		});

	}

	private void handleClubComboBoxChange() {
		Club club = clubComboBox.getValue();
		if (club != null) {
			courtComboBox.setItems(FXCollections.observableArrayList(club.getCourts()));
		}
	}
	
	private void handleCourtComboBoxChange() {
		court = courtComboBox.getValue();
		if (court != null) {
			numberTextField.setText(String.valueOf(court.getNumber()));
			priceTextField.setText(String.valueOf(court.getPrice()));
			courtTypeComboBox.setValue(court.getType());
		}
	}

	protected void handleCancelButton(ActionEvent event) {
		clubComboBox.setValue(null);
		courtComboBox.setValue(null);
		numberTextField.setText("");
		priceTextField.setText("");
		courtTypeComboBox.setValue(null);
	}

	protected void handleSaveButton(ActionEvent event) {
		if (court == null) {
			court = new Court();
		}
		court.setNumber(Integer.valueOf(numberTextField.getText()));
		court.setPrice(Double.valueOf(priceTextField.getText()));
		court.setType(courtTypeComboBox.getValue());
		if (isSuperAdmin) {
			club = clubComboBox.getValue();
		}
		court.setClubId(club.getId());
		try {
			CourtController.save(court);
			if (isSuperAdmin) {
				clubComboBox.setItems(FXCollections.observableArrayList(ClubController.getClubs()));
				clubComboBox.setValue(club);
			}
			courtComboBox.setItems(FXCollections.observableArrayList(CourtController.getCourtsById(club.getId())));
			courtComboBox.setValue(null);
			numberTextField.setText("");
			priceTextField.setText("");
			courtTypeComboBox.setValue(null);
			Alert alert = new Alert(AlertType.INFORMATION, "Success!");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}
}
