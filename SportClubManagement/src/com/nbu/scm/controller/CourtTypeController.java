package com.nbu.scm.controller;

import java.util.Set;

import com.nbu.scm.bean.ClubType;
import com.nbu.scm.bean.CourtType;
import com.nbu.scm.model.ClubTypeModel;
import com.nbu.scm.model.CourtTypeModel;

public class CourtTypeController {

	public static Set<ClubType> getClubTypes() throws Exception {
		return ClubTypeModel.getClubTypes();
	}

	public static Set<CourtType> getCourtTypes() throws Exception {
		return CourtTypeModel.getCourtTypes();
	}
}
