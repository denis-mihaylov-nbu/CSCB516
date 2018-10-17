package com.nbu.scm.controller;

import com.nbu.scm.bean.User;
import com.nbu.scm.model.UserModel;

public class LoginController {
	
	public User validateLogin(String username, String password) throws Exception {
		User user = UserModel.getUserByUsernameAndPassword(username, password);
		return user;
	}
	
}
