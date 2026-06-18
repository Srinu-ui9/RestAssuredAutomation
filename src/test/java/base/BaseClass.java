package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;

public class BaseClass {
	public static final String BASE_URL = "https://petstore.swagger.io/v2";
	protected static ExtentReports extent;
	protected static ExtentTest test;
   
    
	@BeforeSuite
	public void setUpReport() {
		extent = ExtentReportManager.getReport();
	}

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = BASE_URL;
	}

	@AfterSuite
	public void close() {
		if (extent != null) {
			extent.flush();
		}
	}

}
