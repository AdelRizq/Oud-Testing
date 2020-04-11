import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentBDDReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;

public class extentReports {

	ExtentAventReporter avent;
	ExtentBDDReporter bdd;
	ExtentHtmlReporter htmlReporter;

	ExtentReports Home_Account;

	@BeforeSuite
	public void reportSetup() {
		htmlReporter = new ExtentHtmlReporter("reports/Home_Account.html");
		avent = new ExtentAventReporter("reports/HomeAccount_A.html");
		bdd = new ExtentBDDReporter("reports/HomeAccount_B.html");

		Home_Account = new ExtentReports();
		Home_Account.attachReporter(avent, bdd, htmlReporter);
	}

	@AfterSuite
	public void tearDown() {
		// calling flush writes everything to the log file
		Home_Account.flush();
	}
}
