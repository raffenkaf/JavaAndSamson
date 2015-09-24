package com.samson.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import com.samson.dao.*;
import com.samson.exceptions.ImageUploadException;
import com.samson.hibernate.model.HibernateCustomer;
import com.samson.hibernate.model.HibernateOrder;
import com.samson.hibernate.model.HibernateProduct;
import com.samson.hibernate.service.CustomerService;
import com.samson.hibernate.service.OrderService;
import com.samson.hibernate.service.ProductService;
import com.samson.model.*;
import com.samson.service.MailService;

@Controller
public class BaseController { 
	static int bugNumber;
	static boolean isHibernateTablesCreated = false;
    private static final int BUFFER_SIZE = 4096;
    private String filePath = "/downloadFiles/CV.pdf";
	
    @Autowired
    ServletContext servletContext;
	@Autowired
	DataSource dataSource;
	@Autowired
	DataSource dataSourceForUserProfiles;
	@Resource(name="customerServiceImpl")
 	private CustomerService customerService;
	@Resource(name="orderServiceImpl")
 	private OrderService orderService;
	@Resource(name="productServiceImpl")
 	private ProductService productService;

	@RequestMapping(value = "downloadFile", method=RequestMethod.GET)
	public void downloadCV(HttpServletResponse response, ModelAndView mav) throws IOException{
		
	    String appPath = servletContext.getRealPath("");
	 
	    String fullPath = appPath + filePath;      
	    File downloadFile = new File(fullPath);
	    FileInputStream inputStream = new FileInputStream(downloadFile);

	    String mimeType = servletContext.getMimeType(fullPath);
	    if (mimeType == null) {
	    	mimeType = "application/octet-stream";
	    }

	    response.setContentType(mimeType);
	    response.setContentLength((int) downloadFile.length());
	 
	    String headerKey = "Content-Disposition";
	    String headerValue = String.format("attachment; filename=\"%s\"",
	    	downloadFile.getName());
	    response.setHeader(headerKey, headerValue);

	    OutputStream outStream = response.getOutputStream();
	 
	    byte[] buffer = new byte[BUFFER_SIZE];
	    int bytesRead = -1;
	    
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
	    	outStream.write(buffer, 0, bytesRead);
	    }

	    inputStream.close();
	    outStream.close();

	}
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@RequestMapping(value = "loginError", method=RequestMethod.GET)
	public ModelAndView loginError(ModelAndView mav){
		mav.setViewName("loginError");
		return mav;
	}
//	Autobiography
	@RequestMapping(value = "autobiography0", method=RequestMethod.GET)
	public ModelAndView autobiography0(ModelAndView mav){
		mav = new ModelAndView("autobiography/autobiography0");
		return mav;
	}
	
	@RequestMapping(value = "autobiography1", method=RequestMethod.GET)
	public ModelAndView autobiography1(ModelAndView mav){
		mav = new ModelAndView("autobiography/autobiography1");
		return mav;
	}
	
	@RequestMapping(value = "autobiography2", method=RequestMethod.GET)
	public ModelAndView autobiography2(ModelAndView mav){
		mav = new ModelAndView("autobiography/autobiography2");
		return mav;
	}
	
// 	Delete record    
    
    @RequestMapping(value = "deleteRecord", method = RequestMethod.POST)
    public ModelAndView deleteRecord(ModelAndView mav){

    	ServletRequestAttributes reqAttr = 
    			(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();		

    	String sessionId = getSessionId();
    	
	    CustomerDAO customerDAO = 
	    		new CustomerDAOImpl(dataSource, sessionId);//techDetSpringSQL
	    OrderDAO orderDAO = 
	    		new OrderDAOImpl(dataSource, sessionId);
	    ProductDAO productDAO = 
	    		new ProductDAOImpl(dataSource, sessionId);
	    
    	String[] customerCheckBoxes =  reqAttr.getRequest().getParameterValues("customerCheckBox");
    	String[] productCheckBoxes =  reqAttr.getRequest().getParameterValues("productCheckBox");
    	String[] orderCheckBoxes =  reqAttr.getRequest().getParameterValues("orderCheckBox");
    	
    	if (customerCheckBoxes != null) {
        	for (int iterator = 0; iterator < customerCheckBoxes.length; iterator++) {
        	    logger.info("User delete customer record id ="+iterator);
    			customerDAO.delete(Integer.parseInt(customerCheckBoxes[iterator]));
    		}
		}
    	
    	System.out.println(3);
    	if (productCheckBoxes != null) {
        	for (int iterator = 0; iterator < productCheckBoxes.length; iterator++) {
        		logger.info("User delete product record id ="+iterator);
        		productDAO.delete(Integer.parseInt(customerCheckBoxes[iterator]));
    		}
		}

    	if (orderCheckBoxes != null) {
        	for (int iterator = 0; iterator < orderCheckBoxes.length; iterator++) {
        		logger.info("User delete order record id ="+iterator+" session =");
        		orderDAO.delete(Integer.parseInt(customerCheckBoxes[iterator]));
    		}
		}
    	mav = techDetSpringSQL(mav);
    	return mav;
    }
    
//  Add record(нужна полноценная валидация значений)    
    
    @RequestMapping(value = "addRecord", method = RequestMethod.POST)
    public ModelAndView addRecord(@ModelAttribute(value="customer") Customer customer, 
    		@ModelAttribute(value="product") Product product, 
    		@ModelAttribute(value="order") Order order, ModelAndView mav){
	
    	String sessionId = getSessionId();
    	
	    CustomerDAO customerDAO = new CustomerDAOImpl(dataSource, sessionId);
	    OrderDAO orderDAO = new OrderDAOImpl(dataSource, sessionId);
	    ProductDAO productDAO = new ProductDAOImpl(dataSource, sessionId);
	    
   	    if (customer!=null
	    		&& customer.getAddress()!=null 
	    		&& customer.getName()!= null 
	    		&& customer.getPhone() != null 
	    		&& customer.getRating() != -1) {
			customerDAO.add(customer);
		    logger.info("User add new customer record session="+sessionId);
		}
	    
	    if (order!= null	
	    		&& order.getAmount() != -1 
	    		&& order.getCustomerId() != -1 
	    		&& order.getDate() != null 
	    		&& order.getProductId() != -1 
	    		&& order.getQty()!= -1) {
	    	logger.info("User add new order record session="+sessionId);
	    	orderDAO.add(order);
	    	
		}
	    
	    if (product != null
	    		&& product.getDescription() != null 
	    		&& product.getDetails() != null 
	    		&& product.getPrice() != -1) {
	    	logger.info("User add new product record session="+sessionId);
	    	productDAO.add(product);
	    	
		}
	    mav = techDetSpringSQL(mav);
    	return mav;
    }
    
//	Send bug report to my email, validating image and save image// доделать на выходных!!!!!!
    
    @RequestMapping(value = "sendBugReport", method = RequestMethod.POST)
	public ModelAndView sendBugReport(@RequestParam(value="image", required=false)
	MultipartFile image, BindingResult bindingResult, ModelAndView mav) {
    	String bugDescription;
    	
    	try {
    		if(!image.isEmpty()) {
    			validateImage(image);
    			saveImage(bugNumber++ + ".jpg", image); // Сохранить файл
    		}
    	} catch (ImageUploadException e) {
    		bindingResult.reject(e.getMessage());
    		mav.setViewName("bugReport");
    		return mav;
    	}
    	
    	MailService mailService = new MailService();
    	
    	bugDescription = (String) mav.getModel().get("bugDescriprion");
    	
    	if (image.isEmpty()) {
    		mailService.sendSimpleMail("raffenkaf@gmail.com", "Bug Report", bugDescription);
		}else{
			try {
				mailService.sendMailWithPrintScreen("raffenkaf@gmail.com", "Bug Report", 
					bugDescription, new File("/home/samson/workspace/JavaAndSamson/src"
						+ "/main/resources/bugReportPrintScreen/" + (bugNumber-1) + ".jpg"));
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
    	
    	mav = new ModelAndView("tnxForBugReport");
    	return mav;    	
	}
    
    private void saveImage(String filename, MultipartFile image) {
    	try {
    		File file = new File("/home/samson/workspace/JavaAndSamson/src"
    				+ "/main/resources/bugReportPrintScreen/" + filename);
    		FileUtils.writeByteArrayToFile(file, image.getBytes());
    		} catch (IOException e) {
    		throw new ImageUploadException("Unable to save image", e);
    	}
	}

	private void validateImage(MultipartFile image) {
    	if(!image.getContentType().equals("image/jpeg")) {
    		throw new ImageUploadException("Only JPG images accepted");
    	}
	}

//	Main page
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView welcomePage(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

//  User addition
	@RequestMapping(value="addUserProfile" ,method = RequestMethod.POST)
	public ModelAndView addUserProfile(@Valid UserProfile userProfile, 
			BindingResult bindingResult, ModelAndView mav) {
   	
    	UserProfileDAO userProfileDAO = 
    			new UserProfileDAOImpl(dataSourceForUserProfiles);
    	
    	if(bindingResult.hasErrors()) {
    		mav.setViewName("registrationPage");
    		return mav;
    	}
    	
    	List<UserProfile> allUserProfiles = userProfileDAO.getAllUsers();
    	System.out.println();
    	for (UserProfile tmpUserProfile : allUserProfiles) {
			if (tmpUserProfile.getUserName() == userProfile.getUserName()) {
				bindingResult.rejectValue("username", "error.user", 
						"Такой username уже существует, выберите, пожалуйста, другой username");
				return mav;
			}
		}

    	userProfileDAO.addUser(userProfile);

    	mav.setViewName("registration/tnxForRegistrationPage");
	    logger.info("Add new user name="+userProfile.getName());
    	return mav;
	}
//	User deleting	
	@RequestMapping(value="deleteUserProfile", method = RequestMethod.POST )
	public ModelAndView deleteUserProfile(HttpServletRequest request,ModelAndView mav){
    	
		UserProfileDAO userProfileDAO = 
    			new UserProfileDAOImpl(dataSourceForUserProfiles);
		
		userProfileDAO.deleteUserByUserName(
				SecurityContextHolder.getContext().getAuthentication().getName());
		
		logger.info("Delete user userName="
				+SecurityContextHolder.getContext().getAuthentication().getName());
		
		request.getSession().invalidate();
		
    	SecurityContextHolder.clearContext();
    	
		
		String projectUrl = "http://localhost:8080/JavaAndSamson/";
		mav = new ModelAndView("redirect:" + projectUrl);
		return mav;
	}

//  Internal and external redirect
    @RequestMapping(method = RequestMethod.GET, value="showPage")
	public ModelAndView showPage(@RequestParam("direction") String submitData, ModelAndView mav){
		
		switch (submitData) {
		case "Короткая версия всего сайта(CV)":     						//from index
			mav.setViewName("CV_page");
			break;
		case "Автобиография":												//from index
			mav.setViewName("autobiography/autobiography0");
			break;
		case "Технические детали":											//from index
			mav = techDetails(mav);
			break;
		case "Литература":													//from index
			mav.setViewName("bibliography");
			break;
		case "Более детальный осмотр Spring+SQL":							//from techDetail
			mav = techDetSpringSQL(mav);
			break;
		case "Более детальный осмотр Spring+Hibernate":						//from techDetail
			mav = techDetSpringHibernate(mav);
			break;
		case "Более детальный осмотр Spring Web Flow":						//from techDetail
			mav.setViewName("techDetails/techDetSpringWebFlow");
			break;
		case "Примеры кода с описанием":										//from techDetail
			mav.setViewName("techDetails/techDetCodeExample");
			break;
		case "Регистрация":													//from any place
			mav = registrationPage(mav);
			break;
		case "Вход на сайт":												//from any place
			mav.setViewName("login");
			break;
		case "Я нашел баг":													//from any place
			mav.setViewName("bugReport");
			break;
		case "Главная страница":											//from any place
			mav.setViewName("index");
			break;
		case "Карта сайта":													//from any place
			mav.setViewName("siteMap");
			break;
		case "Изменить таблицы":											//from techDetSpringSQL
			mav = changeSQLTable(mav);
			break;
		case "Спасибо за ваше время":										//from bugReport
			mav.setViewName("tnxForBugReport");
			break;
		case "Исходники сайта на GitHub":										//from bugReport
			String projectUrl = "https://github.com/raffenkaf";
			mav = new ModelAndView("redirect:" + projectUrl);
            break;
		case "Домашняя страница":										//from bugReport
			mav.setViewName("home");
			break;
		}

		
		return mav;
	}

    private ModelAndView techDetSpringHibernate(ModelAndView mav) {
	
    	if (!isHibernateTablesCreated) {
    		HibernateCustomer customer = new HibernateCustomer(
				533,"ООО \"Кускус\"","313-48-48","ул. Смольная, 7",1000);
    		customerService.saveCustomer(customer);
    		customer = new HibernateCustomer(
				534,"Петров","112-14-15","ул. Рокотова, 8",1500);
    		customerService.saveCustomer(customer);
    		customer = new HibernateCustomer(
				536,"Крылов","444-78-90","Зеленый просп., 22",1000);
    		customerService.saveCustomer(customer);
		
		
			HibernateProduct product = new HibernateProduct(
				1, "Обогреватель 12Г", "Мощность 400/800/1200 Вт", 1145);
			productService.saveProduct(product);
			product = new HibernateProduct(
				2, "Гриль СТ14", "Мощность 1200 Вт", 2115);
			productService.saveProduct(product);
			product = new HibernateProduct(
				3, "Кофеварка ЕКЛ32", "Мощность 450 Вт", 710);
			productService.saveProduct(product);
			product = new HibernateProduct(
				4, "Чайник МН3", "Мощность 2200 Вт", 925);
			productService.saveProduct(product);
			product = new HibernateProduct(
				5, "Утюг АБ20", "Мощность 1400 Вт", 518);
			productService.saveProduct(product);
			
			HibernateOrder order = new HibernateOrder(1012, 8, 4500);
			customer.setCustomerId(533);
			order.setCustomer(customer);
			product.setProductId(5);
			order.setProduct(product);
			orderService.saveOrder(order);
			order = new HibernateOrder(1013, 14, 22000);
			customer.setCustomerId(536);
			order.setCustomer(customer);
			product.setProductId(2);
			order.setProduct(product);
			orderService.saveOrder(order);
			
			isHibernateTablesCreated=true;
			logger.info("Filled hibernate tables");
		}    	
		List<HibernateCustomer> allCustomerList= customerService.findAllCustomers();
		mav.addObject("allCustomerList",allCustomerList);
		
		List<HibernateOrder> allOrderList= orderService.findAllOrders();
		mav.addObject("allOrderList", allOrderList);
		
		List<HibernateProduct> allProductList= productService.findAllProducts();
		mav.addObject("allProductList",allProductList);
		
		mav.setViewName("techDetails/techDetSpringHibernate");
		return mav;
	}

	//	Change sql table page
	private ModelAndView changeSQLTable(ModelAndView mav) {
		
		mav.setViewName("techDetails/techChangeSQLTable");
		
		mav.addObject("customer", new Customer());
		mav.addObject("product", new Product());
		mav.addObject("order", new Order());

    	String sessionId = getSessionId();
    	
    	CustomerDAO customerDAO;
    	OrderDAO orderDAO;
    	ProductDAO productDAO;
	
		customerDAO = new CustomerDAOImpl(dataSource, sessionId);
		orderDAO = new OrderDAOImpl(dataSource, sessionId);
		productDAO = new ProductDAOImpl(dataSource, sessionId);

    	
		List<Customer> allCustomerList= customerDAO.getAll();
		mav.addObject("allCustomerList",allCustomerList);
		
		List<Order> allOrderList= orderDAO.getAll();
		mav.addObject("allOrderList", allOrderList);
		
		List<Product> allProductList= productDAO.getAll();
		mav.addObject("allProductList",allProductList);
		
		return mav;
	}

//	Tech details page with creation of tables	
	private ModelAndView techDetails(ModelAndView mav) {
		
		mav.setViewName("techDetails");
    	
	    return mav;
	}
	
	private void createTablesSQLSpring(ModelAndView mav){
		
    	String sessionId = getSessionId(); 
		
		CustomerDAO customerDAO = 
				new CustomerDAOImpl(dataSource, sessionId);//techDetSpringSQL
	    ProductDAO productDAO = 
	    		new ProductDAOImpl(dataSource, sessionId);
	    OrderDAO orderDAO = 
	    		new OrderDAOImpl(dataSource, sessionId);
	    
	    if (!customerDAO.isTableExist()) {
		    customerDAO.create();
		    logger.info("Create customer table session="+sessionId);
		}
	    if (!productDAO.isTableExist()) {
		    productDAO.create();
		    logger.info("Create product table session="+sessionId);
		}
	    if (!orderDAO.isTableExist()) {
		    orderDAO.create();
		    logger.info("Create order table session="+sessionId);
		}
	}

//  Registration page + creation of object of user profile
	
	private ModelAndView registrationPage(ModelAndView mav) {
		mav.setViewName("registration/registrationPage");
		mav.addObject(new UserProfile());
		return mav;
	}

// 	Page with examples of SQL + Spring and examples of queries. 

	private ModelAndView techDetSpringSQL(ModelAndView mav) {
		
		mav.setViewName("techDetails/techDetSpringSQL");
		
    	CustomerDAO customerDAO;
    	OrderDAO orderDAO;
    	ProductDAO productDAO;
    	CustomerJoinOrderDAO customerJoinOrderDAO;    	
    	
    	String sessionId = getSessionId();
    	
    	customerDAO = new CustomerDAOImpl(dataSource, sessionId);
		orderDAO = new OrderDAOImpl(dataSource, sessionId);
		productDAO = new ProductDAOImpl(dataSource, sessionId);
		customerJoinOrderDAO = new CustomerJoinOrderDAOImpl(dataSource, sessionId);

    	if (!(customerDAO.isTableExist() && orderDAO.isTableExist() && productDAO.isTableExist())) {
    	   	createTablesSQLSpring(mav);
		}
     	
	    List<DescribeRow> listDescribeCustomer = customerDAO.describeCustomer();
	    mav.addObject("listDescribeCustomer",listDescribeCustomer);
	    
	    List<DescribeRow> listDescribeOrder = orderDAO.describeOrder();
	    mav.addObject("listDescribeOrder",listDescribeOrder);
	    
	    List<DescribeRow> listDescribeProduct = productDAO.describeProduct();
	    mav.addObject("listDescribeProduct",listDescribeProduct);
		
		List<Customer> allCustomerList= customerDAO.getAll();
		mav.addObject("allCustomerList",allCustomerList);
		
		List<Order> allOrderList= orderDAO.getAll();
		mav.addObject("allOrderList", allOrderList);
		
		List<Product> allProductList= productDAO.getAll();
		mav.addObject("allProductList",allProductList);
		
		List<Customer> distinctCustomerList= customerDAO.distinctSelect();
		mav.addObject("distinctCustomerList",distinctCustomerList);
		
		List<Customer> likeCustomerList = customerDAO.likeSelect();
		mav.addObject("likeCustomerList", likeCustomerList);
		
		List<Customer> selectCustomerWithIdIn = customerDAO.selectWithIdIn();
		mav.addObject("selectCustomerWithIdIn", selectCustomerWithIdIn);
		
		Order selectOrderMaxAmountOrder = orderDAO.selectMaxAmountOrder();
		mav.addObject("selectOrderMaxAmountOrder", selectOrderMaxAmountOrder);
		
		List <Order> selectOrderMaxMinAmountOrder = orderDAO.selectMaxMinAmountOrder();
		mav.addObject("selectOrderMaxMinAmountOrder", selectOrderMaxMinAmountOrder);
		
		List<Product> selectCustomerExist = productDAO.selectExist();
		mav.addObject("selectCustomerExist", selectCustomerExist);
		
		List<Customer> selectCustomerAllProductOrder = customerDAO.selectAllProductOrder();
		mav.addObject("selectCustomerAllProductOrder", selectCustomerAllProductOrder);
		
		List <CustomerWithAliasesSelect> listCustomerWithAliases = 
				customerDAO.selectWithAliases();
		mav.addObject("listCustomerWithAliases", listCustomerWithAliases);
		
		List <OrderCaseSelect> listOrderCaseSelect = orderDAO.caseSelect();
		mav.addObject("listOrderCaseSelect", listOrderCaseSelect);
		
		List <OrderWithSum> listOrderHavingSelect = orderDAO.havingSelect();
		mav.addObject("listOrderHavingSelect", listOrderHavingSelect);
		
		List <OrderWithSum> listOrderSelectHavingWithRollup = 
				orderDAO.selectHavingWithRollup();
		mav.addObject("listOrderSelectHavingWithRollup", 
				listOrderSelectHavingWithRollup);
		
		List<CustomerJoinOrder> selectCustomerOrderCrossJoin = 
				customerJoinOrderDAO.selectCrossJoin();
		mav.addObject("selectCustomerOrderCrossJoin", selectCustomerOrderCrossJoin);
		
		List<CustomerJoinOrder> selectCustomerOrderNaturalJoin = 
				customerJoinOrderDAO.selectNaturalJoin();
		mav.addObject("selectCustomerOrderNaturalJoin", selectCustomerOrderNaturalJoin);
		
		List<CustomerJoinOrder> selectCustomerOrderJoinUseId = 
				customerJoinOrderDAO.selectJoinUseId();
		mav.addObject("selectCustomerOrderJoinUseId", selectCustomerOrderJoinUseId);
		
		List<CustomerJoinOrder> selectCustomerOrderJoinOn = 
				customerJoinOrderDAO.selectJoinOn();
		mav.addObject("selectCustomerOrderJoinOn", selectCustomerOrderJoinOn);
		
		List<CustomerJoinOrder> selectCustomerOrderLeftOuterJoin = 
				customerJoinOrderDAO.selectJoinOn();
		mav.addObject("selectCustomerOrderLeftOuterJoin", selectCustomerOrderLeftOuterJoin);

		return mav;
	}
	
	public String getSessionId() {
    	ServletRequestAttributes reqAttr = 
    			(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	
    	String sessionId = 
    			reqAttr.getSessionId().substring(0, reqAttr.getSessionId().length()-4);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getName() != "anonymousUser") {
    		
        	UserProfileDAO userProfileDAO = new UserProfileDAOImpl(dataSourceForUserProfiles);

			UserProfile userProfile = userProfileDAO.getUserByUserName(SecurityContextHolder.
					getContext().getAuthentication().getName());
    		
			if (userProfile.getSessionId() == null) {
				userProfileDAO.addSessionId(userProfile.getUserName(), sessionId);
			}else {
				sessionId = userProfile.getSessionId();
			}
		}
		return sessionId;
	}
}