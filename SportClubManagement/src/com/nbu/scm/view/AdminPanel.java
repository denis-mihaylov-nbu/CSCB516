package com.nbu.scm.view;

import com.nbu.scm.bean.User;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

public class AdminPanel {

	public void start(User user) {
		Stage stage = new Stage();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		try {		
			Tab clubTab = new Tab("Club management");
			ClubManagmentPane clubGrid = new ClubManagmentPane(user);
			clubTab.setContent(clubGrid);
			tabPane.getTabs().add(clubTab);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
		
		try {		
			Tab clubTab = new Tab("Court management");
			CourtManagementPane courtGrid = new CourtManagementPane(user);
			clubTab.setContent(courtGrid);
			tabPane.getTabs().add(clubTab);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
				
		try {
			Tab userManagementTab = new Tab("User management");
			UserManagmentPane userManagementGrid;
				userManagementGrid = new UserManagmentPane(user);			
			userManagementTab.setContent(userManagementGrid);
			tabPane.getTabs().add(userManagementTab);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
		
		Tab personalTab = new Tab("Personal information");
		PersonalInfoPane personalGrid = new PersonalInfoPane(user);
		
		personalTab.setContent(personalGrid);
		tabPane.getTabs().add(personalTab);
		
		Scene scene = new Scene(tabPane, 1000, 500);
		stage.setScene(scene);
		stage.show();
	}
}
