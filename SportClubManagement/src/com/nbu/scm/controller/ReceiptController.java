package com.nbu.scm.controller;

import com.nbu.scm.bean.Receipt;
import com.nbu.scm.model.ReceiptModel;

public class ReceiptController {

	public Receipt createReceipt(Receipt receipt) throws Exception {
		receipt = ReceiptModel.create(receipt);
		return receipt;
	}

}
