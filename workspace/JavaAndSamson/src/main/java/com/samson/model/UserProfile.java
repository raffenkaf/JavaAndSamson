package com.samson.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserProfile {
	
	@Size(min = 1, max= 30, 
			message = "Имя должно быть из минимум 1, максимум 30 букв.")
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$",
			message = "Имя должно состоять из букв латиницы или кириллицы, цифр или знака доллара")
	private String name;
	@Size(min = 1, max= 30, 
			message = "Username должен быть из минимум 1, максимум 30 букв.")
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$",
			message = "Логин должен состоять из букв латиницы или кириллицы, цифр или знака доллара")	
	private String userName;
	@Size(min = 6, max= 30, 
			message = "Пароль должен быть из минимум 6, максимум 30 букв.")
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$",
			message = "Пароль должен состоять из букв латиницы или кириллицы, цифр или знака доллара")
	private String password;
	
	private String sessionId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String session) {
		this.sessionId = session;
	}	
}
