package com.nbu.scm.view;

import java.util.Set;

import com.nbu.scm.bean.User;
import com.nbu.scm.controller.UserController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UserManagmentPane extends GridPane {

	private static final String[] ROLES = new String[] { "Administrator", "Receptionist" };

	// Users combo box
	private Label selectUser = new Label("Select User : ");
	private ComboBox<User> usersComboBox = new ComboBox<User>();

	private Label createNew = new Label("or create new :");
	private Label fname = new Label("First name : ");
	private Label lname = new Label("Last name : ");
	private Label uname = new Label("Username : ");
	private Label passw = new Label("Password : ");
	private TextField fnameField = new TextField();
	private TextField lnameField = new TextField();
	private TextField unameField = new TextField();
	private TextField passwField = new TextField();
	private Label role = new Label("Role : ");
	private ComboBox<String> roleComboBox = new ComboBox<String>();

	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	// Currently logged user!
	private User user;

	public UserManagmentPane(User user) {
		this.user = user;
		init();
	}

	public void init() {

		roleComboBox.setItems(FXCollections.observableArrayList(ROLES));
		try {
			usersComboBox.setItems(
					FXCollections.observableArrayList(UserController.getUsersByClubId(user.getClub().getId())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		//check if entered
		//TODO New instance of User when not selected from Combobox and save
		//if selected edit the selected instance and update.
		System.out.println(user);
	}
}
