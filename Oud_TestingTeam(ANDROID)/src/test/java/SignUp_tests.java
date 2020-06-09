package tests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.lang.String;
import java.time.LocalDate;
import java.util.List;

public class SignUp_tests extends BaseClass {

	private String signUpPageXpath, usernameId, passwordId, displayNameId, emailId,
	countryListId, countryId, monthXpath, dayXpath, yearXpath, validUsername,
	maleId, femaleId, buttonId, errorMessageId, errorMessage, day, month, boundYear, curYear, validYear;

	ExtentTest alreadySignUp, invalidEmail, shortPassword, weakPassword, requiredGender,
			requiredUsername, requiredDisplayName, dateTest, successfulSignUp;

	LocalDate today;

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
		signUpPageXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout";
		usernameId = "com.example.oud:id/text_signup_username";
		passwordId = "com.example.oud:id/text_signup_password";
		displayNameId = "com.example.oud:id/text_signup_display_name";
		emailId = "com.example.oud:id/text_signup_email";
		countryListId = "com.example.oud:id/spinner_signup_country";
		countryId = "android:id/text1";
		monthXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.EditText";
		dayXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.EditText";
		yearXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[3]/android.widget.EditText";
		maleId = "com.example.oud:id/radio_button_signup_male";
		femaleId = "com.example.oud:id/radio_button_signup_female";
		buttonId = "com.example.oud:id/btn_signup";
		errorMessageId = "com.example.oud:id/text_view_signup_error_message";
		validUsername = "";

		today = LocalDate.now();
		day = Integer.toString(today.getDayOfMonth());
		month = today.getMonth().toString();
		boundYear = Integer.toString(today.getYear() - 10);
		curYear = Integer.toString(today.getYear());
		validYear = Integer.toString(today.getYear() - 20);
	}

	@Test(priority = 1)
	public void AlreadySignedUP()
	{
		System.out.println("sign-up again test started");

		driver.findElementById("com.example.oud:id/btn_to_signup_fragment").click();

		alreadySignUp = signup.createTest("already signed-up");

		setUsername("Dandosh");
		setDisplayName("Dandosh");
		setEmail("cry.try124@gmail.com");
		setPassword("12345678");
		changeCountry(12);
		setDate("29", "Nov", "1998");
		setGender('m');

		submit();

		//if (getElementById())

		errorMessage = getElementById(errorMessageId).getText();

		alreadySignUp = (errorMessage.equals("Duplicate field value: \"cry.try124@gmail.com\". Please use another value!") ||
				errorMessage.equals("Duplicate field value: \"Dandosh\". Please use another value!")) ?
						alreadySignUp.log(Status.PASS, "already signed up test passed"):
						alreadySignUp.log(Status.FAIL, "duplicated data is permitted with error Message: \" + errorMessage");
	}

	@Test(priority = 2)
	public void invalidEmails() {
		System.out.println("invalid emails test started");

		invalidEmail = signup.createTest("invalid emails");
		for (String i : invalidEmails)
		{
			setEmail(i);

			submit();
			errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

			if (!errorMessage.equals("please enter a valid email address") &&
					!errorMessage.equals("\"email\" must be a valid email"))
			{
				invalidEmail.log(Status.FAIL, "this invalid email " + i + " is permitted");
			}
		}
		invalidEmail.log(Status.PASS, "invalid emails test passed");
	}

	@Test(priority = 3)
	public void shortPasswords() {
		System.out.println("short passwords test started");

		shortPassword = signup.createTest("short passwords");

		resetPassword();
		resetUsername();
		resetDisplayName();

		setEmail("oudTesting@gmail.com");
		boolean fail = false;

		for (String i : shortPasswords)
		{
			setPassword(i);

			submit();
			errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();
			if (!errorMessage.equals("password must be at least 8 characters long"))
			{
				shortPassword.log(Status.FAIL, "this invalid password " + i + " is permitted with error Message: \" + errorMessage");
				fail = true;
				//return;
			}
		}
		if (!fail)
			shortPassword.log(Status.PASS, "short passwords test passed");
	}

	@Test(priority = 4)
	public void weakPasswords() {
		System.out.println("weak passwords test started");
		weakPassword = signup.createTest("weak passwords");

		resetPassword();
		resetUsername();
		resetDisplayName();

		setEmail("oudTesting@gmail.com");
		boolean fail = false;

		for (String i : weakPasswords)
		{
			resetPassword();
			setPassword(i);

			submit();
			errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();
			if (!errorMessage.equals("password must be at least 8 characters long"))
			{
				weakPassword.log(Status.FAIL, "this invalid password " + i + " is permitted");
				fail = true;
			}
		}
		if (!fail)
			weakPassword.log(Status.PASS, "weak passwords test passed");
	}

	@Test(priority = 5)
	public void requiredGender() {
		System.out.println("required gender tests started");

		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		ScrollUntilId("com.example.oud:id/btn_to_signup_fragment", true, 0).click();

		requiredGender = signup.createTest("required Gender");

		setEmail("oudTesting@gmail.com");
		setPassword("12345678");

		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();


		requiredGender = (errorMessage.equals("please select a gender")) ?
				requiredGender.log(Status.PASS, "required gender test passed") :
				requiredGender.log(Status.FAIL, "required gender test failed");

		setGender('M');
		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		requiredGender = (!errorMessage.equals("please select a gender")) ?
				requiredGender.log(Status.PASS, "male gender test passed"):
				requiredGender.log(Status.FAIL, "male gender test failed");

		setGender('F');
		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		requiredGender = (!errorMessage.equals("please select a gender")) ?
				requiredGender.log(Status.PASS, "female gender test passed"):
				requiredGender.log(Status.FAIL, "female gender test failed");

	}

	@Test(priority = 6)
	public void requiredUsername() {
		System.out.println("required username test started");

		requiredUsername = signup.createTest("required Username");

		resetUsername();
		resetDisplayName();

		setEmail("oudTesting@gmail.com");
		setPassword("12345678");
		setGender('M');

		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		requiredUsername = (errorMessage.equals("\"username\" is not allowed to be empty")) ?
							requiredUsername.log(Status.PASS, "empty username test passed"):
							requiredUsername.log(Status.FAIL, "empty username test failed");

		setUsername("Dand");

		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		requiredUsername = (errorMessage.equals("\"username\" length must be at least 5 characters long")) ?
				requiredUsername.log(Status.PASS, "username length test passed"):
				requiredUsername.log(Status.FAIL, "username length test failed");

		setUsername("Dandosh");

		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		requiredUsername = (errorMessage.equals("\"username\" length must be at least 5 characters long") ||
							errorMessage.equals("\"username\" is not allowed to be empty")) ?
							requiredUsername.log(Status.FAIL, "simple username test failed"):
							requiredUsername.log(Status.PASS, "simple username test passed");
	}

	@Test(priority = 7)
	public void dateTests()
	{
		System.out.println("date tests started");

		dateTest = signup.createTest("date tests");

		resetUsername();
		resetDisplayName();

		setUsername("dandosh");
		setEmail("oudTesting@gmail.com");
		setPassword("12345678");
		setGender('M');

		setDate(day, month.substring(0, 3), curYear);
		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		dateTest = (errorMessage.equals("You must be at least 10 years old")) ?
				dateTest.log(Status.PASS, "today date: " + day + "-" + month + "-" + curYear + " test passed"):
				(errorMessage.equals("\"birthDate\" must be in iso format")) ?
					dateTest.log(Status.FAIL, "today date test failed with error Message: " + errorMessage):
					dateTest.log(Status.FAIL, "today date: " + day + "-" + today.getMonthValue() + "-" + curYear + " test failed with error Message: " + errorMessage);

		setDate(day, "jan", "2000");
		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		dateTest = (errorMessage.equals("\"birthDate\" must be in iso format")) ?
				dateTest.log(Status.FAIL, "boundary month (Jan) test failed with error Message: " + errorMessage):
				dateTest.log(Status.PASS, "boundary month (Jan) test passed");

		setDate(day, month.substring(0, 3), boundYear);
		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();
		dateTest = (errorMessage.equals("You must be at least 10 years old")) ?
				dateTest.log(Status.FAIL, "boundary date of signing-up test failed with error Message: " + errorMessage):
				(errorMessage.equals("\"birthDate\" must be in iso format")) ?
						dateTest.log(Status.FAIL, "boundary date of signing-up test failed with error Message: " + errorMessage):
						dateTest.log(Status.PASS, "boundary date of signing-up test passed");
	}

	@Test(priority = 8)
	public void requiredDisplayName()
	{
		System.out.println("required DisplayName tests started");
		requiredDisplayName = signup.createTest("required Display name");

		setUsername("dandosh");
		setEmail("oudTesting@gmail.com");
		setPassword("12345678");
		setGender('M');
		setDate(day, "nov", validYear);

		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		requiredDisplayName = (errorMessage.equals("\"displayName\" is not allowed to be empty")) ?
				requiredDisplayName.log(Status.PASS, "empty display name test passed"):
				requiredDisplayName.log(Status.FAIL, "empty display name test failed with error Message: " + errorMessage);

		setDisplayName("Dandosh");

		submit();
		errorMessage = ScrollUntilId(errorMessageId, true, 0).getText();

		requiredDisplayName = (errorMessage.equals("\"displayName\" is not allowed to be empty")) ?
				requiredDisplayName.log(Status.FAIL, "simple display name \"Dandosh\" test failed with error Message: " + errorMessage):
				requiredDisplayName.log(Status.PASS, "simple display name \"Dandosh\" test passed");
	}

	@Test(priority = 9)
	public void successfulSignUP()
	{
		System.out.println("successful sign-up test started");

		successfulSignUp = signup.createTest("successful sign-up");

		for (int i = 0; i < 18; i++)
		{
			String temp = String.valueOf((char)((int)(Math.random() * (i % 3 == 2 ? 9 : 25)) + (i % 3 == 0 ? 'a' : i % 3 == 1 ? 'A' : '0')));
			validUsername += temp;
		}

		System.out.println(validUsername);

		setUsername(validUsername);
		setDisplayName(validUsername);
		setEmail(validUsername + "@gmail.com");
		setPassword(validUsername);
		setDate("29", "Nov", "1998");
		setGender('m');

		submit();
		Wait(2000);

		successfulSignUp = (getElementById("com.example.oud:id/navigation_home").isDisplayed()) ?
				successfulSignUp.log(Status.PASS, "simple sign-up test passed, welcome home"):
				successfulSignUp.log(Status.FAIL, "simple sign-up test failed, we didn't get home :(");

		back();
		Wait(2000);

		successfulSignUp = !getElementByXpath(signUpPageXpath).isDisplayed() ?
				successfulSignUp.log(Status.PASS, "going back after sign-up test passed"):
				successfulSignUp.log(Status.FAIL, "going back after sign-up test failed => returned to the sign-up page with written data :(");

		// going back again is wrong :()
	}

	private void setUsername(String username)
	{
		resetUsername();
		ScrollUntilId(usernameId, false, 0).sendKeys(username);
	}
	private void setEmail(String email)
	{
		resetEmail();
		ScrollUntilId(emailId, false, 0).sendKeys(email);
	}
	private void setPassword(String password)
	{
		resetPassword();
		ScrollUntilId(passwordId, false, 0).sendKeys(password);
	}
	private void setDisplayName(String displayName)
	{
		resetDisplayName();
		ScrollUntilId(displayNameId, false, 0).sendKeys(displayName);
	}
	private void setDate(String day, String month, String year)
	{
		MobileElement Month = getElementByXpath(monthXpath);
		MobileElement Day = getElementByXpath(dayXpath);
		MobileElement Year = getElementByXpath(yearXpath);

		Month.sendKeys(month);
		Month.sendKeys(month);
		Month.click();
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));

		Day.sendKeys(day);
		Day.sendKeys(day);
		Day.click();
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));

		Year.sendKeys(year);
		Year.sendKeys(year);
		Year.click();
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}
	private void changeCountry(int idx)
	{
		getElementById(countryListId).click();
		ScrollUntilId(countryId, true, idx).click();
	}
	private void setGender(char gender)
	{
		ScrollUntilId((gender == 'm' || gender == 'M' ? maleId : femaleId), true, 0).click();
	}
	private void submit()
	{
		ScrollUntilId(buttonId, true, 0).click();
	}

	private void resetUsername()
	{
		ScrollUntilId(usernameId, false, 0).clear();
	}
	private void resetPassword()
	{
		ScrollUntilId(passwordId, false, 0).clear();
	}
	private void resetEmail()
	{
		ScrollUntilId(emailId, false, 0).clear();
	}
	private void resetDisplayName()
	{
		ScrollUntilId(displayNameId, false, 0).clear();
	}

}
