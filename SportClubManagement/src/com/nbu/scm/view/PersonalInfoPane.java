package com.nbu.scm.view;

import com.nbu.scm.bean.User;
import com.nbu.scm.controller.UserController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class PersonalInfoPane extends GridPane {
	
	private User user;

	private Label fname = new Label("First name : ");
	private Label lname = new Label("Last name : ");
	private Label uname = new Label("Username : ");
	private Label passw = new Label("Password : ");

	private TextField fnameField;
	private TextField lnameField;
	private TextField unameField;
	private TextField passwField = new TextField();

	private Button save = new Button("Save");

	public PersonalInfoPane(User user) {
		super();
		this.user = user;
		init();
	}

	public void init() {

		fnameField = new TextField(user.getFirstName());
		lnameField = new TextField(user.getLastName());
		unameField = new TextField(user.getUsername());

		add(fname, 0, 0);
		add(fnameField, 1, 0);
		add(lname, 0, 1);
		add(lnameField, 1, 1);
		add(uname, 0, 2);
		add(unameField, 1, 2);
		add(passw, 0, 3);
		add(passwField, 1, 3);
		add(save, 0, 4);		

		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleSaveButton(event);
			}
		});

	}
	
	protected void handleSaveButton(ActionEvent event) {
		user.setUsername(unameField.getText());
		user.setPassword(passwField.getText());
		user.setFirstName(fnameField.getText());
		user.setLastName(lnameField.getText());

		try {
			UserController.save(user);
			Alert alert = new Alert(AlertType.INFORMATION, "Success!");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}

}
