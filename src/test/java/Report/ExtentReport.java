package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import settings.TestSettings;

public class ExtentReport {

    public static ExtentReports getReportObject() {

        String reportPath = TestSettings.ExtentReportFolder;
        ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
        report.config().setReportName("Task Results");
        report.config().setDocumentTitle("GET");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Eslam Mashaly", "Tester");
        return extent;
    }
}
