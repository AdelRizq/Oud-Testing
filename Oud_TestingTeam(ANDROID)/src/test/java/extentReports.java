
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

@SuppressWarnings("deprecation")
public class extentReports {

	ExtentHtmlReporter homeReporter, signupReporter, loginReporter, playerReporter;

	ExtentReports signup, home, login, Home_Account, PlayerRepo;

	@BeforeSuite
	public void reportSetup() {
		signupReporter = new ExtentHtmlReporter("reports/signup.html");
		homeReporter = new ExtentHtmlReporter("reports/Home_Account.html");
		loginReporter = new ExtentHtmlReporter("reports/login.html");
		playerReporter = new ExtentHtmlReporter("reports/Player.html");

		Home_Account = new ExtentReports();
		signup = new ExtentReports();
		login = new ExtentReports();
		PlayerRepo = new ExtentReports();

		Home_Account.attachReporter(homeReporter);
		PlayerRepo.attachReporter(playerReporter);
		signup.attachReporter(signupReporter);
		login.attachReporter(loginReporter);
	}

	public void tearDown() {
		// calling flush writes everything to the log file
		Home_Account.flush();
		PlayerRepo.flush();
		login.flush();
		signup.flush();
	}
}
