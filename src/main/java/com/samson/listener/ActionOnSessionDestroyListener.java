package com.samson.listener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.samson.controller.BaseController;
import com.samson.dao.CustomerDAO;
import com.samson.dao.CustomerDAOImpl;
import com.samson.dao.OrderDAO;
import com.samson.dao.OrderDAOImpl;
import com.samson.dao.ProductDAO;
import com.samson.dao.ProductDAOImpl;
import com.samson.dao.UserProfileDAO;
import com.samson.dao.UserProfileDAOImpl;

public class ActionOnSessionDestroyListener implements HttpSessionListener{
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Override
	public void sessionCreated(HttpSessionEvent createEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent destroyEvent) {
		ApplicationContext context= WebApplicationContextUtils.
				getRequiredWebApplicationContext(
						destroyEvent.getSession().getServletContext());	
		
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		
		String sessionId = destroyEvent.getSession().getId();
		CustomerDAO customerDAO = 
	    		new CustomerDAOImpl(dataSource, sessionId);//techDetSpringSQL
	    OrderDAO orderDAO = 
	    		new OrderDAOImpl(dataSource, sessionId);
	    ProductDAO productDAO = 
	    		new ProductDAOImpl(dataSource, sessionId);
    	UserProfileDAO userProfileDAO = 
	    		new UserProfileDAOImpl(dataSource);
    	orderDAO.deleteTable();
    	logger.info("Delete order tablesession ="+sessionId);
	    customerDAO.deleteTable();
	    logger.info("Delete order table session ="+sessionId);
	    productDAO.deleteTable();
	    logger.info("Delete order table session ="+sessionId);
	    userProfileDAO.deleteSession(sessionId);
	    logger.info("Delete session ="+sessionId);
	}
}
