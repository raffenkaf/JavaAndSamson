package com.samson.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.samson.*")
@Import({SecurityConfig.class})
public class RootConfig {
	
}
