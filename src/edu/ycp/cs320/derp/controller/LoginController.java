/**
 * File copied and modifed from CS320 Library Solution Lab 03.
 */

package edu.ycp.cs320.derp.controller;

import edu.ycp.cs320.derp.model.TempLoginDatabase;

public class LoginController{
	private TempLoginDatabase model = null;
	
	public LoginController(TempLoginDatabase model) {
		this.model = model;
	}
	
	public boolean checkUserName(String name) {
		return model.validateUserName(name);
	}
	
	public boolean validateCredentials(String name, String pw) {
		return model.validatePW(name, pw);
	}
}
