package com.nbu.scm.view;

import java.time.LocalDate;
import java.util.Set;

import com.nbu.scm.bean.Receipt;
import com.nbu.scm.bean.User;
import com.nbu.scm.controller.ReceiptController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class TurnoverPane extends GridPane {

	private Label dateLabel = new Label("Date : ");
	private DatePicker datePicker = new DatePicker(LocalDate.now());
	private Button filter = new Button("Filter");
	private ListView<Receipt> listReceipts = new ListView<Receipt>();
	

	private Label totalLabel = new Label("Total : ");
	private TextField totalTextField = new TextField();

	private User loggedUser;

	public TurnoverPane(User user) {
		super();
		this.loggedUser = user;
		init();
	}

	public void init() {

		totalTextField.setDisable(true);
		
		add(dateLabel, 0, 0);
		add(datePicker, 1, 0);
		add(filter, 2, 0);
		add(listReceipts, 0, 1, 3, 1);
		add(totalLabel, 0, 2);
		add(totalTextField, 1, 2);

		filter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleFilterButton(event);
			}

		});

	}

	protected void handleFilterButton(ActionEvent event) {
		try {
			Set<Receipt> receipts = ReceiptController.readReceipt(loggedUser.getClub(), datePicker.getValue());
			listReceipts.setItems(FXCollections.observableArrayList(receipts));
			double total = 0;
			for (Receipt receipt : receipts) {
				total += receipt.getValue();
			}
			totalTextField.setText(String.valueOf(total));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}

}
