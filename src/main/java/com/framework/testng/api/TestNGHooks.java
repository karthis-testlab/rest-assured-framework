package com.framework.testng.api;

import org.testng.annotations.BeforeMethod;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basic;
import static com.framework.utils.PropertiesHandler.*;

public class TestNGHooks {
	
	@BeforeMethod
	public void setUp() {
		baseURI = config("service.now.instance.uri");
		basePath = config("service.now.instance.basePath");
		authentication = basic(config("service.now.instance.username"), secret("service.now.instance.password"));		
	}

}