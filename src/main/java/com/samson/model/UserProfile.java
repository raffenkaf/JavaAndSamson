package com.samson.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;


public class UserProfile {
	
	@NotEmpty(message = "Пароль должен существовать")
	@Size(min = 1, max= 30, 
			message = "The name must be a minimum of 1, maximum of 30 characters.")
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$",
			message = "Имя должно состоять из букв латиницы или кириллицы, цифр или знака доллара")
	private String name;

	@NotEmpty(message = "Пароль должен существовать")
	@Size(min = 1, max= 30, 
			message = "Username должен быть из минимум 1, максимум 30 букв.")
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$",
			message = "Логин должен состоять из букв латиницы или кириллицы, цифр или знака доллара")
	private String userName;
	
	@NotEmpty(message = "Пароль должен существовать")
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
