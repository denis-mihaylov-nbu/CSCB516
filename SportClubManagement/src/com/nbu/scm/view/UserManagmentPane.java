package com.nbu.scm.view;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.RoleType;
import com.nbu.scm.bean.User;
import com.nbu.scm.controller.ClubController;
import com.nbu.scm.controller.UserController;

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

public class UserManagmentPane extends GridPane {

	private Label selectUser = new Label("Select User : ");
	private ComboBox<User> usersComboBox = new ComboBox<User>();
	private Label createNew = new Label("or create new :");

	private Label fname = new Label("First name : ");
	private TextField fnameField = new TextField();

	private Label lname = new Label("Last name : ");
	private TextField lnameField = new TextField();

	private Label uname = new Label("Username : ");
	private TextField unameField = new TextField();

	private Label passw = new Label("Password : ");
	private TextField passwField = new TextField();

	private Label role = new Label("Role : ");
	private ComboBox<RoleType> roleComboBox = new ComboBox<RoleType>();

	private Label clubLabel = new Label("Club : ");
	private ComboBox<Club> clubComboBox = new ComboBox<Club>();
	private TextField clubField = new TextField();

	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	private User loggedUser;
	private boolean isSuperAdmin = false;

	private User user;

	public UserManagmentPane(User loggedUser) throws Exception {
		this.loggedUser = loggedUser;
		this.isSuperAdmin = loggedUser.getId() == 1;
		init();
	}

	public void init() throws Exception {

		roleComboBox.setItems(FXCollections.observableArrayList(RoleType.values()));

		if (isSuperAdmin) {
			usersComboBox.setItems(FXCollections.observableArrayList(UserController.getUsersByClubId(0)));
		} else {
			usersComboBox.setItems(
					FXCollections.observableArrayList(UserController.getUsersByClubId(loggedUser.getClub().getId())));
		}

		add(selectUser, 0, 1);
		add(usersComboBox, 0, 2);
		add(createNew, 0, 3);
		add(fname, 0, 4);
		add(fnameField, 1, 4);
		add(lname, 0, 5);
		add(lnameField, 1, 5);
		add(uname, 0, 6);
		add(unameField, 1, 6);
		add(passw, 0, 7);
		add(passwField, 1, 7);
		add(role, 0, 8);
		add(roleComboBox, 1, 8);
		add(clubLabel, 0, 9);
		if (isSuperAdmin) {
			clubComboBox.setItems(FXCollections.observableArrayList(ClubController.getClubs()));
			add(clubComboBox, 1, 9);
		} else {
			clubField.setText(loggedUser.getClub().toString());
			clubField.setDisable(true);
			add(clubField, 1, 9);
		}
		add(save, 0, 10);
		add(cancel, 1, 10);

		usersComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue arg, User oldValue, User newValue) {
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
		user = usersComboBox.getValue();
		if (user != null) {
			fnameField.setText(user.getFirstName());
			lnameField.setText(user.getLastName());
			unameField.setText(user.getUsername());
			roleComboBox.setValue(user.getType());
			clubComboBox.setValue(user.getClub());
		}
	}

	protected void handleCancelButton(ActionEvent event) {
		usersComboBox.setValue(null);
		fnameField.setText("");
		lnameField.setText("");
		unameField.setText("");
		passwField.setText("");
		roleComboBox.setValue(null);
		clubComboBox.setValue(null);

	}

	protected void handleSaveButton(ActionEvent event) {
		if (user == null) {
			user = new User();
		}
		user.setUsername(unameField.getText());
		user.setPassword(passwField.getText());
		user.setFirstName(fnameField.getText());
		user.setLastName(lnameField.getText());
		user.setType(roleComboBox.getValue());
		if (isSuperAdmin) {
			user.setClub(clubComboBox.getValue());
		} else {
			user.setClub(loggedUser.getClub());
		}

		try {
			UserController.save(user);
			usersComboBox.setItems(
					FXCollections.observableArrayList(UserController.getUsersByClubId(user.getClub().getId())));
			usersComboBox.setValue(user);
			fnameField.setText("");
			lnameField.setText("");
			unameField.setText("");
			passwField.setText("");
			roleComboBox.setValue(null);
			Alert alert = new Alert(AlertType.INFORMATION, "Success!");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}
}
