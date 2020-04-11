import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentBDDReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;

public class extentReports {

	ExtentHtmlReporter  signupReporter, loginReporter;
	ExtentAventReporter avent;
	ExtentBDDReporter bdd;
	ExtentHtmlReporter htmlReporter ;

	ExtentReports signup, login;

	@BeforeSuite
	public void reportSetup() {
		signupReporter = new ExtentHtmlReporter("reports/signup.html");
		loginReporter = new ExtentHtmlReporter("reports/login.html");
		signup = new ExtentReports();
		login = new ExtentReports();
		signup.attachReporter(signupReporter);
		login.attachReporter(loginReporter);
	}

	public void tearDown() {
		// calling flush writes everything to the log file
		login.flush();
		signup.flush();
	}

public void PlayerreportSetup() {
		htmlReporter = new ExtentHtmlReporter("reports/Player.html");
		avent = new ExtentAventReporter("reports/Player_A.html");
		bdd = new ExtentBDDReporter("reports/Player_B.html");

		PlayerRepo = new ExtentReports();
		PlayerRepo.attachReporter(avent, bdd, htmlReporter);
	}
        public void HomeAccountreportSetup() {
		htmlReporter = new ExtentHtmlReporter("reports/HomeAccount.html");
		avent = new ExtentAventReporter("reports/HomeAccount_A.html");
		bdd = new ExtentBDDReporter("reports/HomeAccount_B.html");

		Home_Account = new ExtentReports();
		Home_Account.attachReporter(avent, bdd, htmlReporter);
	}

	@AfterSuite
	public void PlayertearDown() {
		// calling flush writes everything to the log file
		PlayerRepo.flush();
	}
        public void HomeAccounttearDown() {
		// calling flush writes everything to the log file
		Home_Account.flush();
	}
}