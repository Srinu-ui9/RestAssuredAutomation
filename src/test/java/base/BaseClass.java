package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import utils.ExtentReportManager;

public class BaseClass {

	protected static final String EXCEL_PATH = "src/test/resources/testdata/UserTestData.xlsx";
	public static final String BASE_URL = "https://petstore.swagger.io/v2";

	@BeforeSuite
	public void setUpReport() {
		ExtentReportManager.initializeReport();
	}

	@BeforeClass
	public void setup() {

		RestAssured.baseURI = BASE_URL;
	}

	@AfterSuite
	public void close() {
		ExtentReportManager.flushReport();
	}
}
