package com.nbu.scm.controller;

import java.time.LocalDate;
import java.util.Set;

import com.nbu.scm.bean.Club;
import com.nbu.scm.bean.Receipt;
import com.nbu.scm.model.ReceiptModel;

public class ReceiptController {

	public Receipt createReceipt(Receipt receipt) throws Exception {
		receipt = ReceiptModel.create(receipt);
		return receipt;
	}

	public static Set<Receipt> readReceipt(Club club, LocalDate date) throws Exception {
		Set<Receipt> receipts = ReceiptModel.read(club, date);
		return receipts;
	}

}
