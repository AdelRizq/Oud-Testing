import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentBDDReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;

public class extentReports {

	ExtentHtmlReporter homeReporter, signupReporter, loginReporter;

	ExtentReports signup, home, login, Home_Account;

	@BeforeSuite
	public void reportSetup() {
		signupReporter = new ExtentHtmlReporter("reports/signup.html");
		homeReporter = new ExtentHtmlReporter("reports/Home_Account.html");
		loginReporter = new ExtentHtmlReporter("reports/login.html");

		Home_Account = new ExtentReports();
		signup = new ExtentReports();
		home = new ExtentReports();
		login = new ExtentReports();

		Home_Account.attachReporter(homeReporter);
		home.attachReporter(homeReporter);
		signup.attachReporter(signupReporter);
		login.attachReporter(loginReporter);
	}

	public void tearDown() {
		// calling flush writes everything to the log file
		Home_Account.flush();
		home.flush();
		login.flush();
		signup.flush();
	}
}