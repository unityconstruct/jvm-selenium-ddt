package com.uc.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest {

	@Test(dataProvider="getData")
	public void addCustomer() {
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		return null;
	}
}
