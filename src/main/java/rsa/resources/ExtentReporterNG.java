package rsa.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		// ExtentReports and ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter esr = new ExtentSparkReporter(path);
		esr.config().setReportName("Web Automation Results");
		esr.config().setDocumentTitle("Test Results");

		ExtentReports er = new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Tester", "Shubham Rana");
		er.createTest(path);
		return er;
	}

}
