package com.maybank.labs.starter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.labs.starter.bean.StarterRequestBean;
import com.maybank.labs.starter.cache.StarterCacheServices;
import com.maybank.labs.starter.entities.Config;
import com.maybank.labs.starter.entities.StarterPerson;
import com.maybank.labs.starter.events.StarterEvent;
import com.maybank.labs.starter.events.StarterEventPublisher;
import com.maybank.labs.starter.events.StarterEventTypesEnums;
import com.maybank.labs.starter.exception.StarterCustomException;
import com.maybank.labs.starter.service.ConfigService;
import com.maybank.labs.starter.service.StarterPersonService;
import com.maybank.labs.starter.utils.CommonFunctions;
import com.maybank.labs.starter.utils.StarterConstants;
import com.maybank.labs.starter.utils.StarterFileSynchronization;


@RestController
@RequestMapping("/starter-test")
@CrossOrigin
public class StarterTestController {

	private static final Logger LOGGER = LogManager.getLogger(StarterTestController.class);

	private StarterPersonService starterPersonService;
	
	private StarterEventPublisher starterEventPublisher;
	
	private ConfigService configService;
	
	private StarterCacheServices starterCacheServices;
	
	private CommonFunctions commonFunctions;
	
	private StarterFileSynchronization starterFileSynchronization;

	//Always try to follow constructor level dependency injection.
	public StarterTestController(StarterPersonService starterPersonDao
			, StarterEventPublisher starterEventPublisher
			, ConfigService configService
			, StarterCacheServices starterCacheServices
			, CommonFunctions commonFunctions
			, StarterFileSynchronization starterFileSynchronization) {
		this.starterPersonService = starterPersonDao;
		this.starterEventPublisher = starterEventPublisher;
		this.configService = configService;
		this.starterCacheServices = starterCacheServices;
		this.commonFunctions = commonFunctions;
		this.starterFileSynchronization = starterFileSynchronization;
	}


	@GetMapping("/starter-hello")
	public String sayHello(final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		return "Hello!";
	}

	@GetMapping("/starter-person/{id}")
	public StarterPerson getStarterPersonById(@PathVariable("id") Integer id, final HttpServletRequest request) throws StarterCustomException {
		commonFunctions.auditRequest(request);
		System.out.println(StarterConstants.PROJECT_IDENTIFIER + "The id is " + id);
		return starterPersonService.getPersonById(id);
	}

	@GetMapping("/starter-person")
	public List<StarterPerson> getAllStarterPerson(final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		return starterPersonService.findAll();
	}

	@PostMapping("/starter-person")
	public StarterPerson createNewPerson(@RequestBody StarterPerson person, final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		return starterPersonService.save(person);
	}

	@PutMapping("/starter-person")
	public StarterPerson updatePerson(@RequestBody StarterPerson person, final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		return starterPersonService.save(person);
	}

	@GetMapping("/test-log")
	public String index(final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		LOGGER.trace("A TRACE Message");
		LOGGER.debug("A DEBUG Message");
		LOGGER.info("An INFO Message");
		LOGGER.warn("A WARN Message");
		LOGGER.error("An ERROR Message");

		return "Check out the Logs to see the output...";
	}

	
	@PostMapping("/test-event")
	public String testRestEvent(@RequestBody String input, final HttpServletRequest request) {
		commonFunctions.auditRequest(request);

		JSONObject eventJson = new JSONObject(input);
		LOGGER.info(eventJson.toString());
		String eventData = eventJson.getString("eventData");
		String eventType = eventJson.getString("eventType");
		StarterEventTypesEnums typeEnum = StarterEventTypesEnums.ASYNC.getEventType().equalsIgnoreCase(eventType) ? 
		StarterEventTypesEnums.ASYNC : StarterEventTypesEnums.SYNC;
		StarterEvent se = new StarterEvent("Test from Controller", typeEnum, eventData, "StarterExampleEventExecutor");
		starterEventPublisher.publishEvent(se);
		return "Event Called";
	}


	//CACHE TRACKER (CONFIG)
	@GetMapping("/test-config-val/{configEntry}/{configShortCode}")
	public Config testConfig(@PathVariable("configEntry") final String configEntry
			, @PathVariable("configShortCode") final String configShortCode
			, final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		return configService.getConfig(configEntry, configShortCode);
	}

	//CACHE MANAGEMENT

	@GetMapping("/cache")
	public List<String> getAllCacheDetails(final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		return starterCacheServices.getAllCacheDetails();
	}

	@GetMapping("/cache/{cacheName}")
	public String getCacheDetails(@PathVariable final String cacheName, final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		return starterCacheServices.getCacheDetails(cacheName);
	}

	@PutMapping("/cache")
	public List<String> clearAllCaches(final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		starterCacheServices.evictAllCaches();
		return starterCacheServices.getAllCacheDetails();
	}

	@PutMapping("/cache/{cacheName}")
	public String clearCache(@PathVariable final String cacheName, final HttpServletRequest request) {
		commonFunctions.auditRequest(request);
		starterCacheServices.evictCache(cacheName);
		return starterCacheServices.getCacheDetails(cacheName);
	}

	//END CACHE MANAGEMENT
	
	@GetMapping("/syncro/{toPrint}")
	public void testSync(@PathVariable final String toPrint, final HttpServletRequest request) throws StarterCustomException {
		commonFunctions.auditRequest(request);
		starterFileSynchronization.doSynchronizedTask(toPrint);
	}
	
}
