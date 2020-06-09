package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Login_tests extends BaseClass {

	String loginPageXpath, loginId, emailId, passwordId, loginButtonId, errorMessageId, errorMessage, randomUsername ,
	forgotPasswordId, forgotPasswordErrorMessageId, forgotPasswordPageXpath, forgotPasswordEmailId, forgotPasswordButtonId;


	ExtentTest invalidEmail, shortPassword, weakPassword, invalidLogin, successfulLogin, forgotPassword;

	private String invalidEmails[] = {
			"oudTesting",
			"oudTesting@oud",
			"oudTesting@oud.",
			"oudTesting.oud",
			"@oud.oud",
			"d@.oud",
			"oud@oud@oud.oud",
			"$@oud.oud",
			"[]dsa@oud.oud"
	},
	shortPasswords[] = {"1234567", "testing", "TESTING", "tesT123"},
	weakPasswords[] = {
					"oudtesting12",
					"oudTESTING",
					"OUDTESTING12",
					"oudtesting__12",
					"oudTESTING__"
	};

	@BeforeTest
	void SetUP() {
		loginPageXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout";
		forgotPasswordPageXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout";
		loginId = "com.example.oud:id/btn_to_login_fragment";
		emailId = "com.example.oud:id/text_login_username";
		passwordId = "com.example.oud:id/text_login_password";
		loginButtonId = "com.example.oud:id/btn_login";
		forgotPasswordId = "com.example.oud:id/btn_to_forget_password";
		forgotPasswordEmailId = "com.example.oud:id/text_forget_password_email";
		forgotPasswordButtonId = "com.example.oud:id/btn_forget_password_get_link";
		forgotPasswordErrorMessageId = "com.example.oud:id/text_view_forget_password_error_message";
		errorMessageId = "com.example.oud:id/text_view_login_error_message";
		randomUsername = "";
	}

	@Test(priority = 1)
	public void invalidEmails()
	{
		System.out.println("empty and invalid emails test started");
		invalidEmail = login.createTest("empty and invalid emails");
		getElementById(loginId).click();

		submit();
		try {
			errorMessage = getElementById(errorMessageId).getText();
			invalidEmail = (errorMessage.equals("\"email\" is not allowed to be empty")) ?
					invalidEmail.log(Status.PASS, "empty email test passed"):
					invalidEmail.log(Status.FAIL, "empty email test failed with error message: " + errorMessage);
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println("couldn't find the error message :(");
		}

		boolean fail = false;
		for (String i : invalidEmails)
		{
			setEmail(i);

			submit();
			errorMessage = getElementById(errorMessageId).getText();

			if (!errorMessage.equals("please enter a valid email address") &&
				!errorMessage.equals("\"email\" must be a valid email"))
			{
				invalidEmail.log(Status.FAIL, "this invalid email " + i + " is permitted");
				fail = true;
			}
		}

		if (!fail) invalidEmail.log(Status.PASS, "invalid emails test passed");
	}

	@Test(priority = 2)
	public void shortPasswords() {
		System.out.println("empty and short passwords test started");

		shortPassword = login.createTest("empty short passwords");

		resetPassword();

		setEmail("oudTesting@gmail.com");
		submit();

		// test empty password
		try {
			errorMessage = getElementById(errorMessageId).getText();
			shortPassword = errorMessage.equals("\"password\" is not allowed to be empty") ?
				shortPassword.log(Status.PASS, "empty email test passed"):
				shortPassword.log(Status.FAIL, "empty email test failed with error message: " + errorMessage);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println("couldn't find the error message :(");
		}

		// test short passwords
		boolean fail = false;

		for (String i : shortPasswords)
		{
			setPassword(i);

			submit();
			try {
				errorMessage = getElementById(errorMessageId).getText();
				if (!errorMessage.equals("password must be at least 8 characters long") &&
					!errorMessage.equals("\"password\" length must be at least 8 characters long"))
				{
					shortPassword.log(Status.FAIL, "this invalid password \"" + i + "\" is permitted");
					fail = true;
				}
			} catch (Exception exp) {
				System.out.println(exp.getMessage());
				System.out.println("couldn't find the error message :(");
			}
		}
		if (!fail)
			shortPassword.log(Status.PASS, "short passwords test passed");
	}

	@Test(priority = 3)
	public void weakPasswords() {
		System.out.println("weak passwords test started");

		weakPassword = login.createTest("weak passwords");

		resetPassword();

		setEmail("oudTesting@gmail.com");

		// test weak passwords
		boolean fail = false;

		for (String i : weakPasswords)
		{
			setPassword(i);

			submit();
			try {
				errorMessage = getElementById(errorMessageId).getText();
				if (!errorMessage.equals("password must be at least 8 characters long"))
				{
					weakPassword.log(Status.FAIL, "this invalid password " + i + " is permitted");
					fail = true;
				}
			} catch (Exception exp) {
				System.out.println(exp.getMessage());
				System.out.println("couldn't find the error message :(");
			}
		}
		if (!fail)
			weakPassword.log(Status.PASS, "weak passwords test passed");
	}

	@Test(priority = 4)
	public void invalidLogin() {
		System.out.println("invalid login test started");
		invalidLogin = login.createTest("invalid login");

		for (int i = 0; i < 18; i++)
		{
			String temp = String.valueOf((char)((int)(Math.random() * (i % 3 == 2 ? 9 : 25)) + (i % 3 == 0 ? 'a' : i % 3 == 1 ? 'A' : '0')));
			randomUsername += temp;
		}

		setEmail(randomUsername + "@gmail.com");
		setPassword(randomUsername);
		submit();

		try {
			errorMessage = getElementById(errorMessageId).getText();
			invalidLogin = errorMessage.equals("Incorrect email or password!") ?
						invalidLogin.log(Status.PASS, "invalid login test passed"):
						invalidLogin.log(Status.FAIL, "invalid login test failed with error message: " + errorMessage);
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println("couldn't find the error message :(");
		}
	}

	@Test(priority = 5)
	public void successfulLogin()
	{
		System.out.println("successful login test started");
		successfulLogin = login.createTest("successful login");

		setEmail("cry.try124@gmail.com");
		setPassword("12345678");
		submit();
		Wait(2000);


		successfulLogin = (getElementById("com.example.oud:id/navigation_home").isDisplayed()) ?
				successfulLogin.log(Status.PASS, "simple login test passed"):
				successfulLogin.log(Status.FAIL, "simple login test failed");

		back();
		//Wait(2000);

		successfulLogin = (getElementByXpath(loginPageXpath).isDisplayed()) ?
				successfulLogin.log(Status.PASS, "going back after login test passed"):
				successfulLogin.log(Status.FAIL, "going back after login test failed => returned to the login page with written data :(");

		// going back again is wrong :()
	}

	@Test(priority = 6)
	public void forgotPasswords()
	{
		System.out.println("forgot password test started");
		forgotPassword = login.createTest("forgot password test");
		getElementById(forgotPasswordId).click();
		Wait(2000);

		forgotPassword = (!getElementByXpath(forgotPasswordPageXpath).isDisplayed()) ?
				forgotPassword.log(Status.FAIL, "couldn't open forgot password page"):
				forgotPassword.log(Status.PASS, "forgot password opened correctly");


		// empty email test
		getElementById(forgotPasswordButtonId).click();
		try {
			errorMessage = getElementById(forgotPasswordErrorMessageId).getText();
			forgotPassword = (errorMessage.equals("\"email\" is not allowed to be empty")) ?
					forgotPassword.log(Status.PASS, "empty email test passed"):
					forgotPassword.log(Status.FAIL, "empty email test failed with error message: " + errorMessage);
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println("couldn't find the error message :(");
		}

		// invalid email test
		boolean fail = false;
		for (String i : invalidEmails)
		{
			getElementById(forgotPasswordEmailId).clear();
			getElementById(forgotPasswordEmailId).sendKeys(i);

			getElementById(forgotPasswordButtonId).click();
			try {
				errorMessage = getElementById(forgotPasswordErrorMessageId).getText();

				if (!errorMessage.equals("please enter a valid email address") &&
					!errorMessage.equals("\"email\" must be a valid email"))
				{
					forgotPassword.log(Status.FAIL, "this invalid email " + i + " is permitted");
					fail = true;
				}
			} catch (Exception exp) {
				System.out.println(exp.getMessage());
				System.out.println("couldn't find the error message :(");
			}
		}
		if (!fail) forgotPassword.log(Status.PASS, "invalid emails test passed");


		// sending token successfully
		getElementById(forgotPasswordEmailId).clear();
		getElementById(forgotPasswordEmailId).sendKeys("oudTesting@gmail.com");
		getElementById(forgotPasswordButtonId).click();

		try {
			errorMessage = getElementById(forgotPasswordErrorMessageId).getText();

			forgotPassword = errorMessage.equals("Token sent to email!") ?
				forgotPassword.log(Status.PASS, "token sent to email successfully :)"):
				forgotPassword.log(Status.FAIL, "error with sending token => error message: " + errorMessage);
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println("couldn't find the error message :(");
		}

		// email that doesn't exist
		randomUsername = "";
		for (int i = 0; i < 18; i++)
		{
			String temp = String.valueOf((char)((int)(Math.random() * (i % 3 == 2 ? 9 : 25)) + (i % 3 == 0 ? 'a' : i % 3 == 1 ? 'A' : '0')));
			randomUsername += temp;
		}
		getElementById(forgotPasswordEmailId).clear();
		getElementById(forgotPasswordEmailId).sendKeys(randomUsername + "@gmail.com");
		getElementById(forgotPasswordButtonId).click();

		try {
			errorMessage = getElementById(forgotPasswordErrorMessageId).getText();

			forgotPassword = errorMessage.equals("Token sent to email!") ?
				forgotPassword.log(Status.FAIL, "token sent to user doesn't exist :("):
				forgotPassword.log(Status.PASS, "token sent to users that exist only :)");
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println("couldn't find the error message :(");
		}

	}

	public void setEmail(String email)
	{
		resetEmail();
		getElementById(emailId).sendKeys(email);
	}
	public void setPassword(String password)
	{
		resetPassword();
		getElementById(passwordId).sendKeys(password);
	}

	public void resetEmail()
	{
		getElementById(emailId).clear();
	}
	public void resetPassword()
	{
		getElementById(passwordId).clear();
	}


	public void submit()
	{
		getElementById(loginButtonId).click();
	}

}
