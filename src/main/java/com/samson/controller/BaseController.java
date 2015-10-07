package com.samson.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
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
    private static final int BUFFER_SIZE = 4096;
    private String fileCVPhotoPath = "/resources/downloadFiles/CV.pdf";
	
    @Autowired
    ServletContext servletContext;
	@Autowired
	DataSource dataSource;
	@Resource(name="customerServiceImpl")
 	private CustomerService customerService;
	@Resource(name="orderServiceImpl")
 	private OrderService orderService;
	@Resource(name="productServiceImpl")
 	private ProductService productService;

	@RequestMapping(value = "downloadFile", method=RequestMethod.GET)
	public void downloadCV(HttpServletRequest req, 
			HttpServletResponse response, ModelAndView mav) throws IOException, URISyntaxException{
		
		InputStream inputStream = servletContext.getResourceAsStream(fileCVPhotoPath);

	    String mimeType = "application/pdf";
 
	    response.setContentType(mimeType);
	 
	    String headerKey = "Content-Disposition";
	    String headerValue = String.format("attachment; filename=\"%s\"", "CV.pdf");
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
	    		new CustomerDAOImpl(dataSource, sessionId);
	    OrderDAO orderDAO = 
	    		new OrderDAOImpl(dataSource, sessionId);
	    ProductDAO productDAO = 
	    		new ProductDAOImpl(dataSource, sessionId);
	    
    	String[] customerCheckBoxes =  reqAttr.getRequest().getParameterValues("customerCheckBox");
    	String[] productCheckBoxes =  reqAttr.getRequest().getParameterValues("productCheckBox");
    	String[] orderCheckBoxes =  reqAttr.getRequest().getParameterValues("orderCheckBox");

    	
    	if (customerCheckBoxes != null) {
        	for (int iterator = 0; iterator < customerCheckBoxes.length; iterator++) {
        	    logger.info("User delete customer record id ="+Integer.parseInt(customerCheckBoxes[iterator]));
    			customerDAO.delete(Integer.parseInt(customerCheckBoxes[iterator]));
    		}
		}

    	if (productCheckBoxes != null) {
        	for (int iterator = 0; iterator < productCheckBoxes.length; iterator++) {
        		logger.info("User delete product record id ="+productCheckBoxes[iterator]);
        		productDAO.delete(Integer.parseInt(productCheckBoxes[iterator]));
    		}
		}

    	if (orderCheckBoxes != null) {
        	for (int iterator = 0; iterator < orderCheckBoxes.length; iterator++) {
        		logger.info("User delete order record id ="+orderCheckBoxes[iterator]);
        		orderDAO.delete(Integer.parseInt(orderCheckBoxes[iterator]));
    		}
		}
    	mav = techDetSpringSQL(mav);
    	return mav;
    }
    
//  Add record()    
    
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
    
//	Send bug report to my email, validating image and save image// 
    
    @RequestMapping(value = "sendBugReport", method = RequestMethod.POST)
	public ModelAndView sendBugReport(@RequestParam(value="image", required=false)
	MultipartFile image, BindingResult bindingResult, ModelAndView mav) {
    	String bugDescription;
    	
    	try {
    		if(!image.isEmpty()) {
    			validateImage(image);
    			saveImage(bugNumber++ + ".jpg", image); 
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
	public ModelAndView addUserProfile(@Valid @ModelAttribute("userProfile") UserProfile userProfile, 
			BindingResult bindingResult, ModelAndView mav) {
		
    	UserProfileDAO userProfileDAO = 
    			new UserProfileDAOImpl(dataSource);
    	
    	if(bindingResult.hasErrors()) {
    		mav.setViewName("registration/registrationPage");
    		return mav;
    	}
    	
    	List<UserProfile> allUserProfiles = userProfileDAO.getAllUsers();

    	for (UserProfile tmpUserProfile : allUserProfiles) {
			if (tmpUserProfile.getUserName().equalsIgnoreCase(userProfile.getUserName())) {
				bindingResult.rejectValue("userName", "userName.notvalid", 
						"Username занят - возьмите другой username");
				mav.setViewName("registration/registrationPage");
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
    			new UserProfileDAOImpl(dataSource);
		
		userProfileDAO.deleteUserByUserName(
				SecurityContextHolder.getContext().getAuthentication().getName());
		
		logger.info("Delete user userName="
				+SecurityContextHolder.getContext().getAuthentication().getName());
		
		request.getSession().invalidate();
		
    	SecurityContextHolder.clearContext();
    	
		mav = new ModelAndView("redirect:");
		return mav;
	}

//  Internal and external redirect
    @RequestMapping(method = RequestMethod.GET, value="showPage")
	public ModelAndView showPage(@RequestParam("direction") String submitData, ModelAndView mav){
		
		switch (submitData) {
		case "CV page":     												//from index
			mav.setViewName("CV_page");
			break;
		case "Autobiography":													//from index
			mav.setViewName("autobiography/autobiography0");
			break;
		case "Technical details":												//from index
			mav = techDetails(mav);
			break;
		case "References":														//from index
			mav.setViewName("bibliography");
			break;
		case "Spring+SQL":														//from techDetail
			mav = techDetSpringSQL(mav);
			break;
		case "Spring+Hibernate":												//from techDetail
			mav = techDetSpringHibernate(mav);
			break;
		case "Spring Web Flow":													//from techDetail
			mav.setViewName("techDetails/techDetSpringWebFlow");
			break;
		case "Code examples":													//from techDetail
			mav.setViewName("techDetails/techDetCodeExample");
			break;
		case "Registration":													//from any place
			mav = registrationPage(mav);
			break;
		case "Sign in":												//from any place
			mav.setViewName("login");
			break;
		case "Bug!!!":													//from any place
			mav.setViewName("bugReport");
			break;
		case "Main page":											//from any place
			mav.setViewName("index");
			break;
		case "Sitemap":													//from any place
			mav.setViewName("siteMap");
			break;
		case "Change tables":											//from techDetSpringSQL
			mav = changeSQLTable(mav);
			break;
		case "Thank you for your time":										//from bugReport
			mav.setViewName("tnxForBugReport");
			break;
		case "Source code, GitHub":									//from bugReport
			String projectUrl = "https://github.com/raffenkaf";
			mav = new ModelAndView("redirect:" + projectUrl);
            break;
		case "User homepage":											//from bugReport
			mav.setViewName("home");
			break;
		}
		
		return mav;
	}

    private ModelAndView techDetSpringHibernate(ModelAndView mav) {
    	
       	if (!isHibernateTablesCreated()) {
    		HibernateCustomer customer = new HibernateCustomer(
				533,"OOO KysKys","313-48-48","Smolnaja St., 7",1000);
    		customerService.saveCustomer(customer);
    		customer = new HibernateCustomer(
				534,"Petrov","112-14-15","Rokotova St., 8",1500);
    		customerService.saveCustomer(customer);
    		customer = new HibernateCustomer(
				536,"Krulov","444-78-90","Zelenaja St., 22",1000);
    		customerService.saveCustomer(customer);
		
		
			HibernateProduct product = new HibernateProduct(
				1, "Obogrevatel 12g", "Power 400/800/1200 W", 1145);
			productService.saveProduct(product);
			product = new HibernateProduct(
				2, "Gril ST14", "Power 1200 W", 2115);
			productService.saveProduct(product);
			product = new HibernateProduct(
				3, "Kofevarka EKL32", "Power 450 W", 710);
			productService.saveProduct(product);
			product = new HibernateProduct(
				4, "Chainik MN3", "Power 2200 W", 925);
			productService.saveProduct(product);
			product = new HibernateProduct(
				5, "Utug AB20", "Power 1400 W", 518);
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

	private boolean isHibernateTablesCreated() {
		if (customerService.findAllCustomers().isEmpty()) {
			return false;
		}
		return true;
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
		
		List<CustomerJoinOrder> selectCustomerOrderLeftJoin = 
				customerJoinOrderDAO.selectLeftJoin();
		mav.addObject("selectCustomerOrderLeftOuterJoin", selectCustomerOrderLeftJoin);

		return mav;
	}
	
	public String getSessionId() {
    	ServletRequestAttributes reqAttr = 
    			(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	
    	String sessionId = 
    			reqAttr.getSessionId().substring(0, reqAttr.getSessionId().length()-4);
    	
    	if (SecurityContextHolder.getContext().getAuthentication().getName() != "anonymousUser") {
    		
        	UserProfileDAO userProfileDAO = new UserProfileDAOImpl(dataSource);

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