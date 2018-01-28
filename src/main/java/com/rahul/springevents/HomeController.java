package com.rahul.springevents;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope(value  = "singleton")
public class HomeController implements ApplicationEventPublisherAware {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// Used to publish event to the listener
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	
	@Autowired
	EventPublisher ep;

	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher arg0) {
		this.applicationEventPublisher = arg0;
		
	}
	
	/**
	 * Registering the user and publishing the event 
	 * @param registerMap
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/register.do" ,  method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestParam("un") String un,@RequestParam("pwd") String pwd, Locale locale){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		RegisterEvent my  = new RegisterEvent(this,un,pwd,formattedDate);
		applicationEventPublisher.publishEvent(my);
		return "success";
	}
	
	/**
	 * Sends event from server to client side 
	 * @return
	 */
	@RequestMapping("/getNotifications.do")
	public SseEmitter getRealTimeMessageAction(){
		return ep.getSSE();
	}
	
}
