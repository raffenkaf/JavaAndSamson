package com.samson.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.samson.controller"})
public class WebConfig extends WebMvcConfigurerAdapter{
		
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/SalesDept");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("DS81hekbn");
	    return driverManagerDataSource;
	}
	
	@Bean(name = "dataSourceForUserProfiles")
	public DriverManagerDataSource dataSourceForUserProfiles() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/UserProfileDB");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("DS81hekbn");
	    return driverManagerDataSource;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");		
	}
	
	@Bean(name = "mailSender")
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.debug", true);

		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("raffenkaf@gmail.com");
		javaMailSenderImpl.setPassword("DS81hekbn");
		javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
		
	    return javaMailSenderImpl;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		return new TilesViewResolver();
	} 
	
	@Bean(name = "tilesConfigurer")
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/pages/views.xml"});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	} 
}
