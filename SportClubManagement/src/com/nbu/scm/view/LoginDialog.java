package com.nbu.scm.view;

import java.security.NoSuchAlgorithmException;

import com.nbu.scm.bean.User;
import com.nbu.scm.controller.LoginController;
import com.nbu.scm.security.Cryptography;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginDialog {

	TextField userTextField = new TextField();
	PasswordField pwBox = new PasswordField();
	Stage stage = null;
	
	public void start(Stage s, String[] args) {
		stage = s;
		stage.setTitle("Login");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		Label userName = new Label("Username");
		grid.add(userName, 0, 0);

		grid.add(userTextField, 1, 0);

		Label pw = new Label("Password");
		grid.add(pw, 0, 1);

		grid.add(pwBox, 1, 1);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 3);

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					handleSignInButton(e);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		if (args.length > 1) {
			userTextField.setText(args[0]);
			pwBox.setText(args[1]);
		}

		Scene scene = new Scene(grid, 300, 200);
		stage.setScene(scene);
		stage.show();
	}

	private void handleSignInButton(ActionEvent event) throws NoSuchAlgorithmException {
		try {
			User user = new LoginController().validateLogin(userTextField.getText(), Cryptography.cryptSHA256(pwBox.getText()));
			if (user != null){
				if (user.getType() == User.ADMIN){
					new AdminPanel().start(user);
				} else {
					new ReceptionistPanel().start(user);
				}
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Wrong login credentials");
				alert.showAndWait();
			}
		} catch (Exception e){
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
		}
	}
}