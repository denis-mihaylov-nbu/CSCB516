package com.nbu.scm.view;

import com.nbu.scm.bean.User;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalInfoPane extends GridPane {
	private static final String[] ROLES = new String[] { "Administrator", "Receptionist" };
	private User user;

	private Label fname = new Label("First name : ");
	private Label lname = new Label("Last name : ");
	private Label uname = new Label("Username : ");
	private Label passw = new Label("Password : ");

	private TextField fnameField;
	private TextField lnameField;
	private TextField unameField;
	private TextField passwField = new TextField();

	private Label role = new Label("Role : ");
	private ComboBox<String> roleComboBox = new ComboBox<String>();

	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	public PersonalInfoPane(User user) {
		super();
		this.user = user;
		init();
	}

	public void init() {

		fnameField = new TextField(user.getFirstName());
		lnameField = new TextField(user.getLastName());
		unameField = new TextField(user.getUsername());

		roleComboBox.setItems(FXCollections.observableArrayList(ROLES));

		add(fname, 0, 0);
		add(fnameField, 1, 0);
		add(lname, 0, 1);
		add(lnameField, 1, 1);
		add(uname, 0, 2);
		add(unameField, 1, 2);
		add(passw, 0, 3);
		add(passwField, 1, 3);
		add(role, 0, 4);
		add(roleComboBox, 1, 4);
		add(save, 0, 5);
		add(cancel, 1, 5);

	}

}
