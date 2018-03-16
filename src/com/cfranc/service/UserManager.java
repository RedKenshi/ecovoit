package com.cfranc.service;

import java.util.ArrayList;
import java.util.List;

import com.cfranc.model.User;

public class UserManager {
	
	private static UserManager singleton;
	
	private List<User> allUser;
	
	public static UserManager getUserManager(){
		if(singleton==null){
			singleton=new UserManager();
		}
		return singleton;
	}

	public UserManager() {
		super();
		this.allUser = new ArrayList<User>();
	}

	public void addUser(User u){
		allUser.add(u);
	}
	
	public List<User> getAllUser(){
		return allUser;
	}
}
