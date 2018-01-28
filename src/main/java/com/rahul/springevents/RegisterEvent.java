package com.rahul.springevents;

import org.springframework.context.ApplicationEvent;
/***
 * POJO class to  Map registration details.
 * <br> Extends <b>ApplicationEvent</b> to mark this class as an event to listen to.
 * @author rahul
 *
 */
public class RegisterEvent extends ApplicationEvent {

	public RegisterEvent(Object source) {
		super(source);
	}

	public RegisterEvent(Object source, String email, String password, String date) {
		super(source);
		this.email = email;
		this.password = password;
		this.date = date;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private String date;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RegisterEvent [email=" + email + ", registered on date=" + date + "]";
	}
	
	

}
