package com.nbu.scm.view;

import com.nbu.scm.bean.User;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminPanel {

	public void start(User user) {
		Stage stage = new Stage();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
				
		Tab clubTab = new Tab("Club management");
		GridPane clubGrid = new GridPane();
		
		// TODO Show all club information and courts - editable
		
		clubTab.setContent(clubGrid);
		tabPane.getTabs().add(clubTab);

		
		
		Tab userManagementTab = new Tab("User management");
		GridPane userManagementGrid = new GridPane();
		
		// TODO Page for managing administrators and receptionists
		
		userManagementTab.setContent(userManagementGrid);
		tabPane.getTabs().add(userManagementTab);
		
		
		
		Tab personalTab = new Tab("Personal information");
		PersonalInfoPane personalGrid = new PersonalInfoPane(user);
		
		// TODO Personal information edit page
		
		personalTab.setContent(personalGrid);
		tabPane.getTabs().add(personalTab);

		Scene scene = new Scene(tabPane, 800, 500);
		stage.setScene(scene);
		stage.show();
	}
}
