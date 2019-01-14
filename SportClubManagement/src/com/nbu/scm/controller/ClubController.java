package com.nbu.scm.controller;

import java.util.Set;

import com.nbu.scm.bean.Club;
import com.nbu.scm.model.ClubModel;

public class ClubController {

	public static Set<Club> getClubs() throws Exception {
		return ClubModel.getClubs();
	}

	public static Club save(Club club) throws Exception {
		if (club.getId() > 0) {
			club = ClubModel.update(club);
		} else {
			club = ClubModel.insert(club);
		}
		return club;		
	}
}
