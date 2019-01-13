package com.nbu.scm.view;

import com.nbu.scm.bean.User;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReceptionistPanel {

	public void start(User user) throws Exception {
		Stage stage = new Stage();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		Tab receiptTab = new Tab("Receipt");
		ReceiptPane receiptGrid = new ReceiptPane(user.getClub());

		receiptTab.setContent(receiptGrid);
		tabPane.getTabs().add(receiptTab);
		
		Tab calendarTab = new Tab("Calendar");
		CalendarPane calendarGrid = new CalendarPane(user.getClub());
		
		calendarTab.setContent(calendarGrid);
		tabPane.getTabs().add(calendarTab);

		Tab clubTab = new Tab("Club information");
		GridPane clubGrid = new GridPane();

		// TODO Show all club information and courts - read only

		clubTab.setContent(clubGrid);
		tabPane.getTabs().add(clubTab);

		Tab personalTab = new Tab("Personal information");
		GridPane gridPersonal = new GridPane();

		// TODO Personal information edit page

		personalTab.setContent(gridPersonal);
		tabPane.getTabs().add(personalTab);

		Scene scene = new Scene(tabPane, 800, 500);
		stage.setScene(scene);
		stage.show();
	}
}
