package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static ExtentTest test;

	private ExtentReportManager() {
		// Prevent object creation  --constructor
	}

	// Initialize and configure Extent Report
	public static void initializeReport() {

		String reportPath = System.getProperty("user.dir") + "/reports/API_ExtentReport_" + getTimeStamp() + ".html";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

		sparkReporter.config().setReportName("API Test Report");
		sparkReporter.config().setDocumentTitle("Automation Results");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		addSystemInformation();
	}

	// Add system information to report
	private static void addSystemInformation() {

		extent.setSystemInfo("Project", "API Automation");
		extent.setSystemInfo("Tester", "QA Engineer");
		extent.setSystemInfo("Framework", "REST Assured");
		extent.setSystemInfo("Language", "Java");

	}

	// Generate timestamp for unique report name
	private static String getTimeStamp() {

		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	}

	// Create a test in the report
	public static void createTest(String testName) {

		if (extent == null) {
			initializeReport();
		}

		test = extent.createTest(testName);
	}

	// Log information
	public static void info(String message) {

		if (test != null) {
			test.log(Status.INFO, message);
		}
	}

	// Log passed test
	public static void pass(String message) {

		if (test != null) {
			test.log(Status.PASS, message);
		}
	}

	// Log failed test
	public static void fail(String message) {

		if (test != null) {
			test.log(Status.FAIL, message);
		}
	}

	// Flush report
	public static void flushReport() {

		if (extent != null) {
			extent.flush();
		}
	}
}
