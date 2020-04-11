
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.io.IOException;
import java.lang.String;

public class SignUp_tests extends BaseClass {

	private String usernameId, passwordId, displayNameId, emailId;
	private String countryListId, countryId, monthXpath, dayXpath, yearXpath;
	private String maleId, femaleId, buttonId, errorMessageId;
	ExtentTest test;

	@BeforeTest
	void SetUP() throws Exception {
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
	}

	@Test
	public void SignUP() throws Exception
	{
		driver.findElementById("com.example.oud:id/btn_to_signup_fragment").click();

        test = signup.createTest("sign-up test");
		test.pass("starting of sign-up");

		test.log(Status.FAIL, "already signed up by this mail");

		setUsername("Dandosh");
		setDisplayName("Dandosh");
		setEmail("cry.try124@gmail.com");
		setPassword("12345678");
		changeCountry(12);
		setDate("29", "Nov", "1998");

		ScrollUntilId(maleId, true, 0).click();
		ScrollUntilId(buttonId, true, 0).click();

		String errorMessage = driver.findElementById(errorMessageId).getText();
		System.out.println(errorMessage);
		if (errorMessage == "Duplicate field value: \"cry.try124@gmail.com\". Please use another value!")
		{
			test.log(Status.FAIL, "already signed up by this mail");
		}
		else
		{
			test.log(Status.PASS, "sign-up is done");
		}
	}

	public void setUsername(String username)
	{
		getElementById(usernameId).sendKeys(username);
	}
	public void setEmail(String email)
	{
		getElementById(emailId).sendKeys(email);
	}
	public void setPassword(String password)
	{
		getElementById(passwordId).sendKeys(password);
	}
	public void setDisplayName(String displayName)
	{
		getElementById(displayNameId).sendKeys(displayName);
	}
	public void setDate(String day, String month, String year)
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
	public void changeCountry(int idx)
	{
		getElementById(countryListId).click();
		ScrollUntilId(countryId, true, idx).click();
	}

}
