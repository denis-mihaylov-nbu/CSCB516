package com.nbu.scm.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.User;
import com.nbu.scm.controller.UserController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	private List<Club> clubType = new ArrayList<Club>();

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

		clubType.add(new Club("Zona Sport", "Mall Bulgaria"));
		clubType.add(new Club("Maleevi", "Lozenets"));

		clubsComboBox.setItems(FXCollections.observableArrayList(clubType));

		nameField = new TextField(club.getName());
		adressField = new TextField(club.getAddress());

		try {
			typeComboBox.setItems(FXCollections.observableArrayList(TYPES));
		} catch (Exception e) {
			e.printStackTrace();
		}

		int index = 0;
		if (loggedUser.getId() == 1) {
//			 changing selected club
			System.out.println("super admin -> admin");
			add(clubsComboBox, 0, index++);
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

	protected void handleCancelButton(ActionEvent event) {
		System.out.println("Cancel...");
		nameField.setText("");
		adressField.setText("");
		//TODO type
	}

	protected void handleComboBoxChange() {
		this.club = clubsComboBox.getValue();
		System.out.println(club);
		nameField.setText(club.getName());
		adressField.setText(club.getAddress());
	}

	protected void handleSaveButton(ActionEvent event) {

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
