package com.nbu.scm.view;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.ClubType;
import com.nbu.scm.bean.User;
import com.nbu.scm.controller.ClubController;
import com.nbu.scm.controller.ClubTypeController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class ClubManagmentPane extends GridPane {
	
	private Label selectClubLabel = new Label("Select club for edit : ");
	private ComboBox<Club> clubsComboBox = new ComboBox<Club>();
	private Label newClubLabel = new Label("or create new one");

	private Label name = new Label("Name : ");
	private TextField nameField = new TextField();

	private Label adress = new Label("Adress");
	private TextField adressField= new TextField();

	private Label type = new Label("Type : ");
	
	private ComboBox<ClubType> typeComboBox = new ComboBox<ClubType>();

	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	private User loggedUser;
	private boolean isSuperAdmin;
	private Club club;

	public ClubManagmentPane(User loggedUser) throws Exception {
		this.loggedUser = loggedUser;
		this.club = loggedUser.getClub();
		this.isSuperAdmin = loggedUser.getId() == 1;
		init();
	}

	public void init() throws Exception {
		typeComboBox.setItems(FXCollections.observableArrayList(ClubTypeController.getClubTypes()));

		int index = 0;
		if (isSuperAdmin) {
			clubsComboBox.setItems(FXCollections.observableArrayList(ClubController.getClubs()));

			add(selectClubLabel, 0, index++);
			add(clubsComboBox, 0, index++);
			add(newClubLabel, 0, index++);
		} else {
			nameField.setText(club.getName());
			adressField.setText(club.getAddress());
			typeComboBox.setValue(club.getType());
		}

		add(name, 0, index++);
		add(nameField, 0, index++);
		add(adress, 0, index++);
		add(adressField, 0, index++);
		add(type, 0, index++);
		add(typeComboBox, 0, index++);
		add(save, 0, index);
		add(cancel, 1, index);

		clubsComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Club>() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue observable, Club oldValue, Club newValue) {
				handleComboBoxChange();
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

	private void handleComboBoxChange() {
		this.club = clubsComboBox.getValue();
		if (club != null) {
			nameField.setText(club.getName());
			adressField.setText(club.getAddress());
			typeComboBox.setValue(club.getType());
		}
	}

	private void handleSaveButton(ActionEvent event) {
		if (club == null) {
			club = new Club();
		}
		club.setName(nameField.getText());
		club.setAddress(adressField.getText());
		club.setType(typeComboBox.getValue());

		try {
			ClubController.save(club);
			clubsComboBox.setItems(FXCollections.observableArrayList(ClubController.getClubs()));
			clubsComboBox.setValue(club);
			nameField.setText("");
			adressField.setText("");
			typeComboBox.setValue(null);
			Alert alert = new Alert(AlertType.INFORMATION, "Success!");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	private void handleCancelButton(ActionEvent event) {
		if (isSuperAdmin) {
			clubsComboBox.setValue(null);
			nameField.setText("");
			adressField.setText("");
			typeComboBox.setValue(null);
		} else {
			clubsComboBox.setValue(loggedUser.getClub());
			nameField.setText(loggedUser.getClub().getName());
			adressField.setText(loggedUser.getClub().getAddress());
			typeComboBox.setValue(loggedUser.getClub().getType());
		}
	}
}
