package com.framework.servicenow.tests;

import org.testng.annotations.Test;
import com.framework.servicenow.services.IncidentService;

public class CreateIncidentsTest {
	
	@Test
	public void userShouldAbleToCreateNewIncident() {
		String payload = """
				{
				 "short_description": "Short Description 001"
				}
				""";
		new IncidentService()
		    .create(payload)
		    .validateStatusCode(201)
		    .validateContentType("application/json")
		    .validateShortDescription("Short Description 001")
		    .validateDescriptionIsEmpty()
		    .validateCallerIdIsEmpty();
	}
	
	@Test
	public void userShouldAbleToCreateNewIncidentWithCallerIdObject() {
		String payload = """
				{
				 "caller_id": "681ccaf9c0a8016400b98a06818d57c7",
				 "short_description": "001",
				 "description": "Short Description 001"
				}
				""";
		new IncidentService()
	        .createWithCallerId(payload)
	        .validateStatusCode(201)
	        .validateContentType("application/json")
	        .validateShortDescription("001")
		    .validateDescription("Short Description 001")
		    .validateCallerIdLinkIsNotEmpty()
		    .validateCallerIdValue("681ccaf9c0a8016400b98a06818d57c7");
	}

}