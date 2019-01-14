package com.nbu.scm.view;

import com.nbu.scm.bean.Club;
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

public class ClubManagmentPane extends GridPane {
	private static final String[] TYPES = new String[] { "Football", "Tennis" };


	private Label select = new Label("Select club for edit : ");
	private ComboBox<Club> clubsComboBox = new ComboBox<Club>();

	private Label name = new Label("Name : ");
	private TextField nameField;

	private Label adress = new Label("Adress");
	private TextField adressField;

	private Label type = new Label("Type : ");
	// type combobox
	private ComboBox<String> typeComboBox = new ComboBox<String>();

	private Button save = new Button("Save");
	private Button cancel = new Button("Cancel");

	private User loggedUser;

	private Club club;

	public ClubManagmentPane(User loggedUser) {
		this.loggedUser = loggedUser;
		this.club = loggedUser.getClub();
		init();
	}

	public void init() {
		
		typeComboBox.setItems(FXCollections.observableArrayList(TYPES));

		nameField = new TextField(club.getName());
		adressField = new TextField(club.getAddress());

		if (loggedUser.getId() == 1) {
//			 = new User();
		} else {
//		 changing user's club

			try {
//				clubsComboBox.setItems(FXCollections.observableArrayList());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add(name, 0, 0);
			add(nameField, 1, 0);
			add(adress, 0, 1);
			add(adressField, 1, 1);
			add(type, 0, 2);
			add(typeComboBox, 1, 2);
			add(save, 0, 3);
			add(cancel, 1, 3);

			save.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					handleSaveButton(event);
				}
			});
		}
	}

	protected void handleSaveButton(ActionEvent event) {
		// TODO Auto-generated method stub

		if (club == null) {
			club = new Club();
		}
		String name = nameField.getText();
		String adress = adressField.getText();
		club.setName(name);
		club.setAddress(adress);

		// TODO type

		System.out.println(club);

	}

}
