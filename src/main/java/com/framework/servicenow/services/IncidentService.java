package com.framework.servicenow.services;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import com.framework.pojos.desrialization.Root;
import com.framework.pojos.desrialization.RootCallerId;

import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class IncidentService {
	
	private Response response;
	private Root root = null;
	private RootCallerId rootCallerId = null;
	
	private Response createIncident(String payload) {
		return given()		           
		           .headers("Content-Type", "application/json")		           
		           .when()
		           .body(payload)
		           .post("/incident");
	}
	
	private Response createIncidentWithCallerId(String payload) {
		return given()		          
		           .headers("Content-Type", "application/json")
		           .when()
		           .body(payload)
		           .post("/incident");
	}
	
	private Response getIncidents() {
		return given()
				   .when()
				   .get("/incident");
	}
	
	private Response getIncidents(Map<String, String> map) {
		return given()
				   .queryParams(map)
				   .when()
				   .get("/incident");
	}
	
	private Response getIncident(String sysId) {
		return given()
				   .when()
				   .get("/incident/"+sysId);
	}
	
	private Response getIncident(String sysId, Map<String, String> map) {
		return given()
				   .queryParams(map)
				   .when()
				   .get("/incident/"+sysId);
	}
	
	public IncidentService create(String payload) {
		response = createIncident(payload);
		root = createIncident(payload).as(Root.class);
		return this;
	}
	
	public IncidentService createWithCallerId(String payload) {
		response = createIncidentWithCallerId(payload);
		rootCallerId = createIncidentWithCallerId(payload).as(RootCallerId.class);
		return this;
	}
	
	public IncidentService get() {
		response = getIncidents();
		return this;
	}
	
	public IncidentService get(String sys_id) {
		response = getIncident(sys_id);
		return this;
	}
	
	public IncidentService get(Map<String, String> map) {
		response = getIncidents(map);
		return this;
	}
	
	public IncidentService get(String sys_id, Map<String, String> map) {
		response = getIncident(sys_id, map);
		return this;
	}
	
	public IncidentService validateStatusCode(int statusCode) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		return this;
	}
	
	public IncidentService validateContentType(String contentType) {
		assertThat(response.getContentType(), containsString(contentType));
		return this;
	}
	
	public IncidentService validateShortDescription(String expected) {
		if (root != null) {
			assertThat(root.getResult().getShort_description(), equalTo(expected));
		} else {
			assertThat(rootCallerId.getResult().getShort_description(), equalTo(expected));
		}
		return this;
	}
	
	public IncidentService validateDescriptionIsEmpty() {
		if (root != null) {
			assertThat(root.getResult().getDescription(), is(emptyString()));
		} else {
			assertThat(rootCallerId.getResult().getDescription(), is(emptyString()));
		}
		return this;
	}	
	
	public IncidentService validateCallerIdIsEmpty() {
		assertThat(root.getResult().getDescription(), is(emptyString()));
		return this;
	}
	
	public IncidentService validateDescription(String expected) {
		if (root != null) {
			assertThat(root.getResult().getDescription(), equalTo(expected));
		} else {
			assertThat(rootCallerId.getResult().getDescription(), equalTo(expected));
		}
		return this;
	}
	
	public IncidentService validateCallerIdValue(String expected) {
		assertThat(rootCallerId.getResult().getCaller_id().getValue(), equalTo(expected));
		return this;
	}
	
	public IncidentService validateCallerIdLinkIsNotEmpty() {
		assertThat(rootCallerId.getResult().getCaller_id().getLink(), is(not(emptyString())));
		return this;
	}
	
	public IncidentService validateResultSize(int size) {
		assertThat(response.body().jsonPath().getList("result"), hasSize(size));
		return this;
	}
	
	public IncidentService validateCategoryValue(String value) {
		List<String> categories = response.body().jsonPath().getList("result.category");
		for (String category : categories) {
			assertThat(category, equalTo("hardware"));
		}
		return this;
	}

}