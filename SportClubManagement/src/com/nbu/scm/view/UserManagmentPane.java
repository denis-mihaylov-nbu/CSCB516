package com.nbu.scm.view;

import java.util.Set;

import com.nbu.scm.bean.User;
import com.nbu.scm.controller.UserController;

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

public class UserManagmentPane extends GridPane {

	private static final String[] ROLES = new String[] { "Administrator", "Receptionist" };

	// Users combo box
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
	private ComboBox<String> roleComboBox = new ComboBox<String>();

	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	private User loggedUser;
	
	private User user;
	
	public UserManagmentPane(User loggedUser) {
		this.loggedUser = loggedUser;
		init();
	}

	public void init() {

		roleComboBox.setItems(FXCollections.observableArrayList(ROLES));
		try {
			usersComboBox.setItems(
					FXCollections.observableArrayList(UserController.getUsersByClubId(loggedUser.getClub().getId())));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
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
		add(save, 0, 9);
		add(cancel, 1, 9);

		usersComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

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
		//Fill all fields in the pane from the user object
		fnameField.setText(user.getFirstName());
		lnameField.setText(user.getLastName());
		unameField.setText(user.getUsername());
//		passwField.setText(user.getPassword());
	}
	
	protected void handleCancelButton(ActionEvent event) {
		System.out.println("Cancel...");
		// usersComboBox.setValue(null);
		fnameField.setText("");
		lnameField.setText("");
		unameField.setText("");
		passwField.setText("");
//		roleComboBox.setValue(null);

	}

	protected void handleSaveButton(ActionEvent event) {
		// TODO New instance of User when not selected from Combobox and save
		// if selected edit the selected instance and update.
		
	    if (user == null) {
	    	// If user is not selected - create new instance
	    	// user.id is 0 meaning insert
	    	user = new User();
	    }
	    // if user is not null then user id is != 0 meaning update
		String firstName = fnameField.getText();
		String lastName = lnameField.getText();
		String username = unameField.getText();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);

		System.out.println(user);
	}
}
