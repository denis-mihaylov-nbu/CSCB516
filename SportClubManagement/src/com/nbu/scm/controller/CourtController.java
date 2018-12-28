package com.nbu.scm.controller;

import java.sql.Timestamp;
import java.util.List;

import com.nbu.scm.bean.Court;
import com.nbu.scm.model.CourtModel;

public class CourtController {

	public List<Court> getAvailableCourts(int clubId, Timestamp timestamp) throws Exception {
		List<Court> courts = CourtModel.getAvailableCourts(clubId, timestamp);
		return courts;
	}
	
}
