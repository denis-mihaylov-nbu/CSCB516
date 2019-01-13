package com.nbu.scm.view;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.Receipt;
import com.nbu.scm.bean.Reservation;
import com.nbu.scm.bean.Timestamp;
import com.nbu.scm.bean.filter.ReservationFilter;
import com.nbu.scm.controller.ReceiptController;
import com.nbu.scm.controller.ReservationController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ReceiptPane extends GridPane {

	private Club club;

	private DatePicker fromDate = new DatePicker(LocalDate.now());
	private DatePicker toDate = new DatePicker(LocalDate.now());
	private TextField name = new TextField();
	private Button filter = new Button("Filter");
	private Button printReceipt = new Button("Print");
	private ListView<Reservation> list = new ListView<Reservation>();
	private ListView<Reservation> receiptList = new ListView<Reservation>();

	private ReservationController reservationController = new ReservationController();
	private ReceiptController receiptController = new ReceiptController();

	public ReceiptPane(Club club) throws Exception {
		super();
		this.club = club;
		init();
	}

	public void init() throws Exception {

		fromDate.setValue(LocalDate.now());
		toDate.setValue(LocalDate.now());

		int rowId = 0;
		add(fromDate, 1, rowId);
		add(toDate, 2, rowId);
		add(name, 3, rowId);
		add(filter, 4, rowId++);
		filter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					filter();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		filter();
		add(list, 1, rowId);
		add(receiptList, 3, rowId++);

		list.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
					Reservation selected = list.getSelectionModel().getSelectedItem();
					list.getItems().remove(selected);
					receiptList.getItems().add(selected);
				}
			}
		});

		printReceipt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					printReceipt();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		add(printReceipt, 4, rowId);
		
		receiptList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
					Reservation selected = receiptList.getSelectionModel().getSelectedItem();
					receiptList.getItems().remove(selected);
					list.getItems().add(selected);
					FXCollections.sort(list.getItems());
				}
			}
		});

	}

	private void filter() throws Exception {
		List<Reservation> reservations = reservationController.get(getFilter());
		list.setItems(FXCollections.observableArrayList(reservations));
	}

	private void printReceipt() throws Exception {
		double value = 0.0;
		for (Reservation res : receiptList.getItems()) {
			value += res.getCourt().getPrice();
		}
		Receipt receipt = new Receipt(new Timestamp(Calendar.getInstance()), value, club, new ArrayList<Object>(receiptList.getItems()));
		receiptController.createReceipt(receipt);
		receiptList.getItems().clear();
	}

	private ReservationFilter getFilter() {
		ReservationFilter filter = new ReservationFilter();
		filter.setClubId(club.getId());
		filter.setName(name.getText());
		filter.setFromDate(fromDate.getValue());
		filter.setToDate(toDate.getValue());
		return filter;
	}

}
