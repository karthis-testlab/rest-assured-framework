package com.framework.data.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestData {
	
	@DataProvider
	public String[][] excel() {
		return DataFactory.engine(DataEngine.EXCEL).getData("create-incidents");
	}
	
	@DataProvider
	public String[][] csv() {
		return DataFactory.engine(DataEngine.CSV).getData("incidents");
	}
	
	@Test(dataProvider = "excel")
	public void excelTestData(String shortDescription, String description, String urgency, String state, String callerId) {
		System.out.println(shortDescription);
		System.out.println(description);
		System.out.println(urgency);
		System.out.println(state);
		System.out.println(callerId);
	}
	
	@Test(dataProvider = "csv")
	public void csvTestData(String shortDescription, String description, String urgency, String state, String callerId) {
		System.out.println(shortDescription);
		System.out.println(description);
		System.out.println(urgency);
		System.out.println(state);
		System.out.println(callerId);
	}
	
	

}