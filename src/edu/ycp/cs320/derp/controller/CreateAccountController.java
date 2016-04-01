package edu.ycp.cs320.derp.controller;

import edu.ycp.cs320.derp.model.TempLoginDatabase;

public class CreateAccountController{
private TempLoginDatabase model = null;
	
	public CreateAccountController(TempLoginDatabase model){
		this.model = model;
	}
	
	public boolean createAccount(String name, String password){
		return model.createNewUser(name, password);
	}
}
