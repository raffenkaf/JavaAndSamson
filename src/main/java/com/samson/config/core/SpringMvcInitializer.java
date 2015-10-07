package com.samson.config.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.tiles.extras.complete.CompleteAutoloadTilesListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.samson.config.WebConfig;
import com.samson.config.RootConfig;
import com.samson.listener.ActionOnSessionDestroyListener;

public class SpringMvcInitializer 
	extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.addListener(ActionOnSessionDestroyListener.class);
		servletContext.addListener(CompleteAutoloadTilesListener.class);
		super.onStartup(servletContext);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
