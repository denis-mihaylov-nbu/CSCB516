package com.nbu.scm.view;

import com.nbu.scm.bean.User;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalInfoPane extends GridPane {

	private User user;
	
	public PersonalInfoPane(User user) {
		super();
		this.user = user;
		init();
	}

	public void init(){

		Label fname = new Label("First name : ");
		Label lname = new Label("Last name : ");
		Label uname = new Label("Username : ");
		Label passw = new Label("Password : ");
				
		TextField fnameField = new TextField(user.getFirstName());
		TextField lnameField = new TextField(user.getLastName());
		TextField unameField = new TextField(user.getUsername());
		TextField passwField = new TextField();
		
		Button save = new Button("Save");
		Button cancel = new Button("Cancel");
		
		add(fname, 0, 0);
		add(fnameField, 1, 0);
		add(lname, 0, 1);
		add(lnameField, 1, 1);
		add(uname, 0, 2);
		add(unameField, 1, 2);
		add(passw, 0, 3);
		add(passwField, 1, 3);
		add(save, 0, 4);
		add(cancel, 1, 4);
		
	}
	
}
