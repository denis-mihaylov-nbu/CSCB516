package com.nbu.scm.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.nbu.scm.bean.Court;
import com.nbu.scm.model.CourtModel;

public class CourtController {

	public List<Court> getAvailableCourts(int clubId, Timestamp timestamp) throws Exception {
		List<Court> courts = CourtModel.getAvailableCourts(clubId, timestamp);
		return courts;
	}
	
	public static Set<Court> getCourtsById(int clubId) throws SQLException {
		return CourtModel.getCourtsById(clubId);
	}

	public static Court save(Court court) throws Exception {
		if (court.getId() > 0) {
			court = CourtModel.update(court);
		} else {
			court = CourtModel.insert(court);
		}
		return court;		
	}
	
}
