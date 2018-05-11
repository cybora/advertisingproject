package com.reporting.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reporting.model.Reporting;
import com.reporting.service.ReportingService;

@RestController
public class ReportingController {
	
	@Autowired
	private ReportingService reportingService;
	
	/**
	 * @param month
	 * @param site
	 * @return Reporting
	 */
	@GetMapping(value="/reports", produces=MediaType.APPLICATION_JSON_VALUE)
	public Reporting home(@RequestParam(name="month", required=false) String month,
			@RequestParam(name="site", required=false) String site) {
		
		reportingService.validateParameters(month, site);
		
		Reporting response = reportingService.createResponse(month, site);
		
		return response;
		
	}
	
	
	/**
	 * @param e
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
}
