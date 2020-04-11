
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass extends extentReports {
	DesiredCapabilities cap;
	static AndroidDriver<MobileElement> driver;

	@BeforeTest
	public void setup() {
		try {
			setCaps();
		} catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
	}

	void setCaps() throws Exception {
		cap = new DesiredCapabilities();

		// this four capabilities for the used device
		cap.setCapability("deviceName", "G3312");
		cap.setCapability("udid", "WUJ01P6EQJ");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformversion", "7.0");
		//////////////////

		cap.setCapability("appPackage", "com.example.oud");
		cap.setCapability("appActivity", "com.example.oud.MainActivity");

		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(url, cap);
		System.out.println("Testing Started");
	}

	public MobileElement getElementById(String id) {
		return driver.findElementById(id);
	}

	public MobileElement getElementByXpath(String xpath) {
		return driver.findElementByXPath(xpath);
	}

	public void Scroll(boolean direction) { // direction == 1 ? down : up
		Dimension dimension = driver.manage().window().getSize();

		Double scrollHeightStart = dimension.getHeight() * (direction ? 0.5 : 0.2);
		int scrollStart = scrollHeightStart.intValue();

		Double scrollHeightEnd = dimension.getHeight() * (direction ? 0.2 : 0.5);
		int scrollEnd = scrollHeightEnd.intValue();

		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}

	public List<MobileElement> getItemWebViewId(String id) {
		return driver.findElements(By.id(id));
	}

	public List<MobileElement> getItemWebViewXpath(String Xpath) {
		return driver.findElements(By.xpath(Xpath));
	}

	public MobileElement ScrollUntilId(String id, boolean direction, int idx) {
		while (getItemWebViewId(id).size() == 0) {
			Scroll(direction);
		}
		if (getItemWebViewId(id).size() > 0) {
			return getItemWebViewId(id).get(idx);
		}
		return null;
	}

	public MobileElement ScrollUntilXpath(String xpath, boolean direction, int idx) {
		while (getItemWebViewXpath(xpath).size() == 0) {
			Scroll(direction);
		}
		if (getItemWebViewXpath(xpath).size() > 0) {
			return getItemWebViewXpath(xpath).get(idx);
		}
		return null;
	}

	public void Wait(long millis) {
		long timenow1 = System.currentTimeMillis();
		long timenow2 = 0;
		while (timenow2 - timenow1 <= millis) {
			timenow2 = System.currentTimeMillis();
		}
	}

	public void back() {
		driver.navigate().back();
	}
}
