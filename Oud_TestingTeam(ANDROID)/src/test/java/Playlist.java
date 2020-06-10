package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;

public class Playlist extends BaseClass {

	String homeId, randomPlaylistXpath, firstSongInPlaylistXpath, addToPlaylistButtonXpath, firstPlaylistId, libraryId,
			firstPlaylistInLibXpath, firstSongInLibPlaylistXpath, removeSongButtonXpath, playlistPlayButtonXpath,
			playerBarId, createPlaylistXpath, playlistNameId, createPlaylistButtonId, addSuggestedSongToPlaylistXpath,
			closeAccId, playlistSongsId, renamePlaylistButtonId, optionsButtonId, copyLinkXpath, sharePlaylistButtonXpath,
			DeletePlaylistXpath, addSongsButtonId, renamePlaylistXpath, playlistNameXpath, firstPlaylistNameInLibXpath;

	ExtentTest addRemoveFromPlaylist, playPlaylistSeq, createEditDeletePlaylist;

	@BeforeTest
	void SetUP() {
		homeId = "com.spotify.music:id/home_tab";
		randomPlaylistXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[1]/android.widget.ImageView";
		firstSongInPlaylistXpath = "(//android.widget.ImageButton[@content-desc=\"Show context menu\"])[1]";
		addToPlaylistButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[3]";
		firstPlaylistId = "com.spotify.music:id/row_view";
		libraryId = "com.spotify.music:id/your_library_tab";
		firstPlaylistInLibXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout";
		firstSongInLibPlaylistXpath = "(//android.widget.ImageButton[@content-desc=\"Show context menu\"])[1]";
		removeSongButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]";
		playlistPlayButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.Button";
		playerBarId = "com.spotify.music:id/now_playing_bar_layout";

		createPlaylistXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout";
		playlistNameXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.EditText";
		createPlaylistButtonId = "com.spotify.music:id/continue_button";
		addSongsButtonId = "com.spotify.music:id/button_primary";

		addSuggestedSongToPlaylistXpath = "(//android.widget.ImageButton[@content-desc=\"Add song to playlist.\"])[1]";
		closeAccId = "Close";

		playlistSongsId = "com.spotify.music:id/track_cloud";
		renamePlaylistXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[3]";
		renamePlaylistButtonId = "com.spotify.music:id/continue_button";
		playlistNameId = "android:id/text1";

		optionsButtonId = "com.spotify.music:id/glue_overflow";
		copyLinkXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[5]";
		sharePlaylistButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[5]";

		DeletePlaylistXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[4]";
		firstPlaylistNameInLibXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]";
	}

	@Test(priority = 1)
	public void addAndRemoveFromPlaylist()
	{
		addRemoveFromPlaylist = playlist.createTest("add and remove from playlist");

		while(getItemWebViewId(homeId).size() == 0) {}

		ScrollUntilXpath(randomPlaylistXpath).click();
		ScrollUntilXpath(firstSongInPlaylistXpath).click();
		String songTitle = ScrollUntilId("com.spotify.music:id/title").getText();

		ScrollUntilXpath(addToPlaylistButtonXpath).click();
		ScrollUntilId(firstPlaylistId).click();
		ScrollUntilId(libraryId).click();
		ScrollUntilXpath(firstPlaylistInLibXpath).click();

		ScrollUntilId("com.spotify.music:id/track_cloud").click();
		ScrollUntilXpath(firstSongInPlaylistXpath).click();

		if (songTitle.equals(getElementById("com.spotify.music:id/title").getText()))
		{
			addRemoveFromPlaylist.log(Status.PASS, "adding to the playlist done successfully\n");
		} else {
			addRemoveFromPlaylist.log(Status.FAIL, "wrong in adding to the playlist\n");
		}
		assertEquals(songTitle, getElementById("com.spotify.music:id/title").getText());

		ScrollUntilXpath(removeSongButtonXpath).click();

		if (getItemWebViewXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.TextView[1]").size() == 0)
		{
			addRemoveFromPlaylist.log(Status.PASS, "removing from the playlist done successfully\n");
		} else {
			addRemoveFromPlaylist.log(Status.FAIL, "wrong in removing from the playlist\n");
		}
		assertEquals(true, getItemWebViewXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.TextView[1]").size() == 0);

		driver.findElementByAccessibilityId("Back").click();
	}

	@Test(priority = 2)
	public void playPlaylistSequentially() {
		playPlaylistSeq = playlist.createTest("play a playlist sequentially");

		while(getItemWebViewId(homeId).size() == 0) {}

		ScrollUntilId(homeId).click();
		ScrollUntilXpath(randomPlaylistXpath).click();

		ArrayList<String> songsNames = getPlaylistSongsNames();

		ScrollUntilXpath(playlistPlayButtonXpath).click();
		ScrollUntilId(playerBarId).click();

		for (int i = 0; i < 5; i++) {
			String temp = ScrollUntilId("com.spotify.music:id/marquee_track_info_view_title").getText();
			if (!temp.equals(songsNames.get(i)))
			{
				playPlaylistSeq.log(Status.FAIL, "current player song and playlist song isn't identical\n");
				return;
			} else System.out.println(i);

			ScrollUntilId("com.spotify.music:id/next_button").click();
		}

		playPlaylistSeq.log(Status.PASS, "play a playlist sequentially done successfully\n");

		ScrollUntilId("com.spotify.music:id/play_pause_button").click();
		ScrollUntilId("com.spotify.music:id/close_button").click();
	}

	private ArrayList<String> getPlaylistSongsNames() {
		ArrayList<String> ret = new ArrayList<String>();

		for (int i = 2; i < 2 + 5; i++) {
			ret.add(ScrollUntilXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[" + Integer.toString(i) + "]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]", true, 0)
					.getText());
		}

		return ret;
	}

	@Test (priority = 3)
	public void createEditDeletePlaylists() {
		createEditDeletePlaylist = playlist.createTest("create, edit and delete playlists");

		while(getItemWebViewId(libraryId).size() == 0) {}
		ScrollUntilId(libraryId).click();
		ScrollUntilXpath(createPlaylistXpath).click();
		Actions actionProvider = new Actions(driver);
		actionProvider.sendKeys("Adele").perform();

		ScrollUntilId(createPlaylistButtonId).click();

		while(getItemWebViewId("com.spotify.music:id/button_primary").size() == 0) {}

		if (getElementById(playlistNameId).getText().equals("Adele")) {
			createEditDeletePlaylist.log(Status.PASS, "create a playlist done successfully");
		} else {
			createEditDeletePlaylist.log(Status.FAIL, "create a playlist failed");
			return;
		}

		ScrollUntilId(addSongsButtonId).click();
		ScrollUntilXpath(addSuggestedSongToPlaylistXpath).click();
		driver.findElementByAccessibilityId(closeAccId).click();

		while(getItemWebViewId("com.spotify.music:id/cta_button").size() == 0) {}
		createEditDeletePlaylist.log(Status.PASS, "add songs to playlists done successfully");

		ScrollUntilId(optionsButtonId).click();
		ScrollUntilXpath(renamePlaylistXpath).click();
		ScrollUntilXpath(playlistNameXpath).clear();
		ScrollUntilXpath(playlistNameXpath).sendKeys("Dola");
		ScrollUntilId(renamePlaylistButtonId).click();

		while(getItemWebViewId("com.spotify.music:id/cta_button").size() == 0) {}

		if (getElementById(playlistNameId).getText().equals("Dola")) {
			createEditDeletePlaylist.log(Status.PASS, "rename a playlist done successfully");
		} else {
			createEditDeletePlaylist.log(Status.FAIL, "rename a playlist failed");
		}

		ScrollUntilId(optionsButtonId).click();
		ScrollUntilXpath(sharePlaylistButtonXpath, true, 0).click();
		ScrollUntilXpath(copyLinkXpath).click();

		System.out.println(driver.getClipboardText());

		if (driver.getClipboardText().contains("https://open.spotify.com/playlist/")) {
			createEditDeletePlaylist.log(Status.PASS, "sharing playlists done successfully");
		} else {
			createEditDeletePlaylist.log(Status.FAIL, "sharing playlists failed");
		}

		ScrollUntilId(optionsButtonId).click();
		ScrollUntilXpath(DeletePlaylistXpath).click();
		ScrollUntilId("com.spotify.music:id/button_positive").click();

		System.out.println(ScrollUntilXpath(firstPlaylistNameInLibXpath).getText());

		if (ScrollUntilXpath(firstPlaylistNameInLibXpath).getText().equals("oud Testing")) {
			createEditDeletePlaylist.log(Status.PASS, "delete playlists done successfully");
		} else {
			createEditDeletePlaylist.log(Status.FAIL, "delete playlists failed");
		}

	}

}
