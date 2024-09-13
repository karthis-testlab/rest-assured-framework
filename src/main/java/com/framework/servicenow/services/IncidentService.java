package com.framework.servicenow.services;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.framework.pojos.Root;
import com.framework.pojos.RootCallerId;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class IncidentService {
	
	private Response response;
	private Root root = null;
	private RootCallerId rootCallerId = null;
	
	private Response createIncident(String payload) {
		return given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .headers("Content-Type", "application/json")
		           .log().all()
		           .when()
		           .body(payload)
		           .post("https://dev262949.service-now.com/api/now/table/incident");
	}
	
	private Response createIncidentWithCallerId(String payload) {
		return given()
		           .auth()
		           .basic("admin", "vW0eDfd+A0V-")
		           .headers("Content-Type", "application/json")
		           .log().all()
		           .when()
		           .body(payload)
		           .post("https://dev262949.service-now.com/api/now/table/incident");
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

}