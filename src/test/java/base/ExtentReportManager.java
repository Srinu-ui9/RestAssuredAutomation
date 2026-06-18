package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	public static ExtentReports extent;

	public static ExtentReports getReport() {

		String path = System.getProperty("user.dir") + "/reports/report.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("API Test Report");
		reporter.config().setDocumentTitle("Automation Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);

		extent.setSystemInfo("Project", "API Automation");
		extent.setSystemInfo("Tester", "QA Engineer");

		return extent;
	}
}
