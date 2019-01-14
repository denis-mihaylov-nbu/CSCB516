package com.nbu.scm.controller;

import java.util.Set;

import com.nbu.scm.bean.Club;
import com.nbu.scm.model.ClubModel;

public class ClubController {

	public static Set<Club> getClubs() throws Exception {
		return ClubModel.getClubs();
	}

	public static void save(Club club) throws Exception {
		throw new UnsupportedOperationException("Not implemented yet");		
	}
}
