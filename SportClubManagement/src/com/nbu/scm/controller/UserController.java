package com.nbu.scm.controller;

import java.util.Set;

import com.nbu.scm.bean.User;
import com.nbu.scm.model.UserModel;

public class UserController {

	
	public static Set<User> getUsersByClubId(int clubId) throws Exception {
		return UserModel.getUsersByClubId(clubId);
	}

	public static User save(User user) throws Exception {
		if (user.getId() > 0) {
			user = UserModel.update(user);
		} else {
			user = UserModel.insert(user);
		}
		return user;		
	}
	
}
