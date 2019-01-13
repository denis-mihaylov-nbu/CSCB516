package com.nbu.scm.controller;

import java.util.Set;

import com.nbu.scm.bean.User;
import com.nbu.scm.model.UserModel;

public class UserController {

	
	public static Set<User> getUsersByClubId(int clubId) throws Exception {
		return UserModel.getUsersByClubId(clubId);
	}
	
	
}
