package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;
import io.appium.java_client.clipboard.ClipboardContentType;

public class Sharing extends BaseClass {

	String homeId, settingAccId, viewProfileId, optionsButtonId, shareProfileButtonXpath, copyLinkXpath, randomPlaylistXpath,
		   firstSongInPlaylistXpath, shareSongButtonXpath, sharePlaylistButtonXpath, searchButtonId, searchFieldId,
		   searchQueryFieldId, firstAlbumInSearchXpath;

	ExtentTest shareProfile, shareSongs, sharePlaylists, shareAlbums;

	@BeforeTest
	void SetUP() {
		homeId = "com.spotify.music:id/home_tab";
		settingAccId = "Settings";
		viewProfileId = "com.spotify.music:id/username";
		optionsButtonId = "com.spotify.music:id/glue_overflow";
		shareProfileButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]";
		copyLinkXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[5]";

		randomPlaylistXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[1]/android.widget.ImageView";
		firstSongInPlaylistXpath = "(//android.widget.ImageButton[@content-desc=\"Show context menu\"])[1]";
		shareSongButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[5]";
		sharePlaylistButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[4]";

		searchButtonId = "com.spotify.music:id/search_tab";
		searchFieldId = "com.spotify.music:id/find_search_field";
		searchQueryFieldId = "com.spotify.music:id/query";
		firstAlbumInSearchXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout";
	}

	@Test(priority = 1)
	public void shareProfile()
	{
		shareProfile = sharing.createTest("share my profile");

		while(getItemWebViewId(homeId).size() == 0) {}

		driver.findElementByAccessibilityId(settingAccId).click();
		ScrollUntilId(viewProfileId).click();
		ScrollUntilId(optionsButtonId).click();
		ScrollUntilXpath(shareProfileButtonXpath).click();
		ScrollUntilXpath(copyLinkXpath).click();

		System.out.println(driver.getClipboardText());

		if (driver.getClipboardText().contains("https://open.spotify.com/user/")) {
			shareProfile.log(Status.PASS, "sharing the profile done successfully");
		} else {
			shareProfile.log(Status.FAIL, "sharing the profile failed");
		}

		assertEquals(true, driver.getClipboardText().contains("https://open.spotify.com/user/"));
	}

	@Test(priority = 2)
	public void shareSongs()
	{
		shareSongs = sharing.createTest("share songs");

		while(getItemWebViewId(homeId).size() == 0) {}
		getElementById(homeId).click();

		getElementByXpath(randomPlaylistXpath).click();
		ScrollUntilXpath(firstSongInPlaylistXpath).click();
		ScrollUntilXpath(shareSongButtonXpath, true, 0).click();
		ScrollUntilXpath(copyLinkXpath).click();

		System.out.println(driver.getClipboardText());

		if (driver.getClipboardText().contains("https://open.spotify.com/track/")) {
			shareSongs.log(Status.PASS, "sharing the song done successfully");
		} else {
			shareSongs.log(Status.FAIL, "sharing the song failed");
		}

		assertEquals(true, driver.getClipboardText().contains("https://open.spotify.com/track/"));
	}

	@Test(priority = 3)
	public void sharePlaylists()
	{
		sharePlaylists = sharing.createTest("share playlists");

		while(getItemWebViewId(homeId).size() == 0) {}
		getElementById(homeId).click();

		getElementByXpath(randomPlaylistXpath).click();
		ScrollUntilId(optionsButtonId).click();
		ScrollUntilXpath(sharePlaylistButtonXpath, true, 0).click();
		ScrollUntilXpath(copyLinkXpath).click();

		System.out.println(driver.getClipboardText());

		if (driver.getClipboardText().contains("https://open.spotify.com/playlist/")) {
			sharePlaylists.log(Status.PASS, "sharing playlists done successfully");
		} else {
			sharePlaylists.log(Status.FAIL, "sharing playlists failed");
		}

		assertEquals(true, driver.getClipboardText().contains("https://open.spotify.com/playlist/"));
	}

	@Test(priority = 4)
	public void shareAlbums()
	{
		shareAlbums = sharing.createTest("share albums");

		while(getItemWebViewId(searchButtonId).size() == 0) {}
		getElementById(searchButtonId).click();
		ScrollUntilId(searchFieldId).click();
		ScrollUntilId(searchQueryFieldId).sendKeys("album");
		ScrollUntilXpath(firstAlbumInSearchXpath).click();
		ScrollUntilId(optionsButtonId).click();
		ScrollUntilXpath(sharePlaylistButtonXpath, true, 0).click();
		ScrollUntilXpath(copyLinkXpath).click();

		System.out.println(driver.getClipboardText());

		if (driver.getClipboardText().contains("https://open.spotify.com/album/")) {
			shareAlbums.log(Status.PASS, "sharing albums done successfully");
		} else {
			shareAlbums.log(Status.FAIL, "sharing albums failed");
		}

		assertEquals(true, driver.getClipboardText().contains("https://open.spotify.com/album/"));
	}


}
