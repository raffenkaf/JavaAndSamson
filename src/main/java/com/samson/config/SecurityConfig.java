package com.samson.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, true as enabled from UserProfiles where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from UserRoles where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home").authenticated()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and()
				.formLogin().loginPage("/login").failureUrl("/loginError")
				.usernameParameter("username").passwordParameter("password")
			.and().logout().logoutSuccessUrl("/login?logout")
			.and()
				.logout().logoutSuccessUrl("/index")
			.and()
				.exceptionHandling().accessDeniedPage("/accessDenied")
			.and()
				.csrf();
	}
}
