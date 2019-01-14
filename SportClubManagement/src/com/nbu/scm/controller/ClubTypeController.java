package com.nbu.scm.controller;

import java.util.Set;

import com.nbu.scm.bean.ClubType;
import com.nbu.scm.model.ClubTypeModel;

public class ClubTypeController {

	public static Set<ClubType> getClubTypes() throws Exception {
		return ClubTypeModel.getClubTypes();
	}
}
