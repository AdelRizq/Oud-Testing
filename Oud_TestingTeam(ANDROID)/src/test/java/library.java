import java.net.MalformedURLException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class library extends BaseClass {
    ExtentTest artist , album,playlist;
    String  libraryIconID = "com.spotify.music:id/your_library_tab",
            createPlaylistID = "com.spotify.music:id/empty_view_button",
            PlaylistTextID = "android:id/text1",
            editTextID = "com.spotify.music:id/edit_text",
            createButtonID = "com.spotify.music:id/continue_button",
            labelID = "com.spotify.music:id/labels",
            labelXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]",
            threeDotsID = "com.spotify.music:id/glue_overflow",
            deletePlaylistXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[4]",
            confirmDeleteButtonID ="com.spotify.music:id/button_positive";
    String  albumsIconXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]",
            albumTextID = "android:id/text1",
            searchIconID = "com.spotify.music:id/search_tab",
            searchBarID = "com.spotify.music:id/find_search_field",
            inputTextID = "com.spotify.music:id/query",
            albumXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout",
            albumBarXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View",
            likeButtonXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]",
            unlikeButtonXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]",
            albumTitleID = "com.spotify.music:id/title",
            seeAllAlbumsButtonXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[9]/android.widget.LinearLayout/android.widget.TextView";
    String  artistsIconXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]",
            addArtistButtonID = "com.spotify.music:id/empty_view_button",
            artistTextID = "android:id/text1",
            chooseArtistTextID = "com.spotify.music:id/title",
            searchArtistID ="com.spotify.music:id/search_background",
            inputArtistTextID = "com.spotify.music:id/query",
            firstArtistXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout",
            artistNameXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView",
            artistBarId = "com.spotify.music:id/img_picture",
            followingButtonXPath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]",
            artistTitle = "com.spotify.music:id/txt_title";



    @Test(priority = 1)
    void playlist() throws MalformedURLException, InterruptedException
    {
        try
        {
            artist = libraryArtist.createTest("library playlist test");
            getElementById(libraryIconID).click();
            if (getElementById(PlaylistTextID).getText().equals("Create your first playlist"))   artist.log(Status.PASS,"empty playlist text exist");
            else    artist.log(Status.FAIL,"empty playlist text doesn't exist");
            getElementById(createPlaylistID).click();
            getElementById(editTextID).sendKeys("Hello");
            getElementById(createButtonID).click();
            while (getItemWebViewId(libraryIconID).size()==0){}//this is made to wait untill the page loads
            getElementById(libraryIconID).click();
            MobileElement label = getElementByXpath(labelXPath);
            if (label.getText().equals("Hello"))   artist.log(Status.PASS,"added playlist exist in the library");
            else    artist.log(Status.FAIL,"added playlist doesn't exist in the library");
            label.click();
            if (getElementById(PlaylistTextID).getText().equals("Hello"))   artist.log(Status.PASS,"clicking on the playlist from library goes to the playlist");
            else    artist.log(Status.FAIL,"clicking on the playlist from library doesn't go to the playlist");
            getElementById(threeDotsID).click();
            getElementByXpath(deletePlaylistXpath).click();
            getElementById(confirmDeleteButtonID).click();
            getElementById(libraryIconID).click();
            if (getElementById(PlaylistTextID).getText().equals("Create your first playlist"))   artist.log(Status.PASS,"empty playlist text exists after deleting the playlist");
            else    artist.log(Status.FAIL,"empty playlist text doesn't exist after deleting the playlist");
            System.out.println("test Ended...");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 2)
    void album() throws MalformedURLException
    {
        try{
            album = libraryAlbum.createTest("Library albums Test");
            getElementById(libraryIconID).click();
            getElementByXpath(albumsIconXpath).click();
            while (getItemWebViewId(albumTextID).size()==0){}
            if(getElementById(albumTextID).getText().equals("Your albums will appear here")) album.log(Status.PASS, "empty albums text exists");
            else album.log(Status.PASS, "empty albums text doesn't exist");
            getElementById(searchIconID).click();
            getElementById(searchBarID).click();
            while(getItemWebViewId(inputTextID).size()==0){}
            getElementById(inputTextID).sendKeys("Hello");
            ScrollUntilXpath(seeAllAlbumsButtonXPath, true, 0);
            getElementByXpath(seeAllAlbumsButtonXPath).click();
            getElementByXpath(albumXPath).click();
            MobileElement albumBar= getElementByXpath(albumBarXPath);
            int width = albumBar.getSize().getWidth();
            Actions action = new Actions(driver);
            action.moveToElement(albumBar).moveByOffset((width/2)-10, 0).click().perform();
            getElementByXpath(likeButtonXPath).click();
            getElementById(libraryIconID).click();
            getElementByXpath(albumsIconXpath).click();
            while (getItemWebViewId(albumTextID).size()==0){}
            MobileElement label = getElementById(albumTextID);
            if (label.getText().equals("Hello"))  album.log(Status.PASS, "album added to the library after liking it");
            else album.log(Status.PASS, "album not added to the library after liking it");
            label.click();
            if (getElementById(albumTitleID).getText().equals("Hello")) album.log(Status.PASS, "clicking on the album goes to the album page");
            else album.log(Status.PASS, "clicking on the album goes to the album page");
            albumBar= getElementByXpath(albumBarXPath);
            width = albumBar.getSize().getWidth();
            action = new Actions(driver);
            action.moveToElement(albumBar).moveByOffset((width/2)-10, 0).click().perform();
            getElementByXpath(unlikeButtonXPath).click();
            getElementById(libraryIconID).click();
            getElementByXpath(albumsIconXpath).click();
            if(getElementById(albumTextID).getText().equals("Your albums will appear here")) album.log(Status.PASS, "the album is deleted from the library after unliking it");
            else album.log(Status.PASS, "the album is not deleted from the library after unliking it");
            System.out.println("test Ended...");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        
    }

    @Test(priority = 3)
    void artists() throws MalformedURLException
    {
        artist = libraryArtist.createTest("Library artists Test");
        getElementById(libraryIconID).click();
        getElementByXpath(artistsIconXpath).click();
        while (getItemWebViewId(artistTextID).size()==0){}
        if(getElementById(artistTextID).getText().equals("Your artists will appear here")) artist.log(Status.PASS, "empty artists text exists");
        else artist.log(Status.PASS, "empty artists text doesn't exist");
        getElementById(addArtistButtonID).click();
        while (getItemWebViewId(chooseArtistTextID).size()==0){}
        if(getElementById(chooseArtistTextID).getText().equals("Choose more artists you like")) artist.log(Status.PASS, "choose artist text exists");
        else artist.log(Status.PASS, "choose artist text doesn't exist");
        getElementById(searchArtistID).click();
        while(getItemWebViewId(inputArtistTextID).size()==0){}
        getElementById(inputArtistTextID).sendKeys("Sia");
        getElementByXpath(firstArtistXPath).click();
        back();
        MobileElement artistName = getElementByXpath(artistNameXPath);
        if(artistName.getText().equals("Sia")) artist.log(Status.PASS, "artist is added in the library after following him");
        else artist.log(Status.PASS, "artist is not added in the library after following him");
        artistName.click();
        if(getElementById(artistTitle).getText().equals("Sia")) artist.log(Status.PASS, "after clicking on artist goes to the artist page");
        else artist.log(Status.PASS, "after clicking on artist doesn't go to the artist page");
        back();
        WebElement artistName2 =  getElementByXpath(artistNameXPath);
        TouchAction action = new TouchAction(driver);
        action.longPress(LongPressOptions.longPressOptions().withElement (ElementOption.element (artistName2))).release().perform();
        System.out.println("long press");
        while(getItemWebViewXpath(followingButtonXPath).size()==0){}
        getElementByXpath(followingButtonXPath).click();
        getElementById(libraryIconID).click();
        getElementByXpath(artistsIconXpath).click();
        while (getItemWebViewId(artistTextID).size()==0){}
        if(getElementById(artistTextID).getText().equals("Your artists will appear here")) artist.log(Status.PASS, "artist is deleted from library after unfollowing him");
        else artist.log(Status.PASS, "artist is not deleted from library after unfollowing him");
        System.out.println("test Ended...");
    }
    
}