
import java.net.MalformedURLException;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

interface IDSX
{
String HomeButtonId = "com.example.oud:id/btn_home_test";
    String PlayListXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.ImageView";
     String track0 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]";
     String progressBarId = "com.example.oud:id/exo_progress";
      String pause = "com.example.oud:id/exo_pause";
      String next = "com.example.oud:id/exo_next";
      String nextxPath = "(//android.widget.ImageButton[@content-desc=\"Next track\"])[1]";
      String prev = "com.example.oud:id/exo_prev";
      String repeat = "com.example.oud:id/exo_repeat_toggle";
      String Playerstr = "com.example.oud:id/content_control";
      String shuffle = "com.example.oud:id/exo_shuffle";
      String durationTime = "com.example.oud:id/exo_duration";
      String currentTime = "com.example.oud:id/exo_position";
      String play = "com.example.oud:id/exo_play";

}
public class Player  extends BaseClass {

     ExtentTest test;

     @Test
     void playerTest() throws MalformedURLException {
    	 Wait(3000);
    	 SoftAssert sa = new SoftAssert();
    	 test = PlayerRepo.createTest("PLayer test");
    	 Wait(3000);
    	 System.out.println("Application started...");
    	 // open the player
    	 driver.findElementById(IDSX.HomeButtonId).click();
    	 Wait(3000);
    	 driver.findElement(By.xpath(IDSX.PlayListXPath)).click();
    	 Wait(3000);
    	 driver.findElement(By.xpath(IDSX.track0)).click();
    	 Wait(3000);
    	 WebElement pauseButton = driver.findElement(By.id(IDSX.pause));
    	 Assert.assertTrue(pauseButton.isDisplayed());
    	 if (pauseButton.isDisplayed())      test.log(Status.PASS, "pause is Visible after clicking on the track");
    	 else    test.log(Status.FAIL, "pause is Not Visible after clicking on the track");


    	 pauseButton.click();
    	 WebElement playButton = driver.findElement(By.id(IDSX.play));
    	 sa.assertTrue(playButton.isDisplayed());
    	 if (playButton.isDisplayed())      test.log(Status.PASS,"play is Visible after clicking on pause" );
    	 else   test.log(Status.FAIL,  "play is Not Visible after clicking on pause");


    	 playButton.click();
    	 Assert.assertTrue(pauseButton.isDisplayed());
    	 if (pauseButton.isDisplayed())      test.log(Status.PASS,"pause is Visible after clicking on play");
    	 else      test.log(Status.FAIL,"pause is Not Visible after clicking on play");


    	 pauseButton.click();
    	 driver.findElement(By.id(IDSX.Playerstr)).click();
    	 driver.findElement(By.id(IDSX.progressBarId)).click();
    	 String cTime = driver.findElement(By.id(IDSX.currentTime)).getText();
    	 sa.assertTrue(cTime.equals("01:34"));
    	 if (cTime.equals("01:34") || cTime.equals("01:33") || cTime.equals("01:36"))      test.log(Status.PASS,"progress Bar is working while pausing");
    	 else      test.log(Status.FAIL,"progress Bar is not working while pausing");

    	 cTime = driver.findElement(By.id(IDSX.currentTime)).getText();
    	 Wait(5000);
    	 String dTime = driver.findElement(By.id(IDSX.currentTime)).getText();
    	 sa.assertEquals(cTime, dTime);
    	 if (cTime.equals(dTime))       test.log(Status.PASS,"pause is working proberly");
    	 else   test.log(Status.FAIL,"pause is not working proberly");


    	 driver.findElement(By.id(IDSX.play)).click();
    	 Wait(5000);
    	 cTime = driver.findElement(By.id(IDSX.currentTime)).getText();
    	 sa.assertTrue(cTime.equals("01:39") || cTime.equals("01:40") || cTime.equals("01:41"));

    	 if (cTime.equals("01:39") || cTime.equals("01:40") || cTime.equals("01:41"))       test.log(Status.PASS,"play is working proberly");
    	 else   test.log(Status.FAIL,"play is not working proberly");

    	 WebElement prevButton =  driver.findElement(By.id(IDSX.prev));

    	 prevButton.click();
    	 cTime = driver.findElement(By.id(IDSX.currentTime)).getText();
    	 sa.assertTrue(cTime.equals("00:00") || cTime.equals("00:01") ||  cTime.equals("00:02"));
    	 if (cTime.equals("00:00") || cTime.equals("00:01") ||  cTime.equals("00:02"))    test.log(Status.PASS,"previous button is Working");
    	 else      test.log(Status.FAIL,"previous button is not Working");

    	 WebElement repeatButton =  driver.findElement(By.id(IDSX.repeat));
    	 WebElement nextButton =  driver.findElement(By.xpath(IDSX.nextxPath));

    	 sa.assertFalse(nextButton.isEnabled());
    	 repeatButton.click();
    	 sa.assertFalse(nextButton.isEnabled());
    	 repeatButton.click();
    	 sa.assertTrue(nextButton.isEnabled());

    	 if (nextButton.isEnabled())    test.log(Status.PASS,"repeat is Working");
    	 else      test.log(Status.FAIL,"repeat is not Working");


    	 driver.findElement(By.xpath(IDSX.nextxPath)).click();
    	 cTime = driver.findElement(By.id(IDSX.currentTime)).getText();
    	 sa.assertTrue(cTime.equals("00:00") || cTime.equals("00:01") || cTime.equals("00:02"));
    	 if (cTime.equals("00:00") || cTime.equals("00:01") || cTime.equals("00:02"))    test.log(Status.PASS,"next button is Working");
    	 else      test.log(Status.FAIL,"next button is not Working");


    	 System.out.println("End of test");
    	 sa.assertAll();


     }

}