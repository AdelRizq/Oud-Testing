import java.net.MalformedURLException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.And;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class SEARCH extends BaseClass {
    ExtentTest searchRep;
    String  libraryIconID = "com.spotify.music:id/your_library_tab",
            searchIconID = "com.spotify.music:id/search_tab",
            searchBarID = "com.spotify.music:id/find_search_field",
            inputTextID = "com.spotify.music:id/query",
            clearSearchID = "com.spotify.music:id/search_right_button",
            searchTextID = "com.spotify.music:id/text1",
            textAfterSearchID = "com.spotify.music:id/glue_toolbar_title",  
            recentResearchXPath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]",
            clearRecentSearchesButtonXPath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[6]/android.widget.LinearLayout/android.widget.TextViewx",
            noRecentSearchesID="com.spotify.music:id/text1",
            garbageInput = "ahsdckadscm";
    String seeAll [] = {"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[9]/android.widget.LinearLayout/android.widget.TextView",
    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[8]/android.widget.LinearLayout/android.widget.TextView",
     "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[7]/android.widget.LinearLayout/android.widget.TextView",
     "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[6]/android.widget.LinearLayout/android.widget.TextView",
     "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[5]/android.widget.LinearLayout/android.widget.TextView",
     "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.TextView"};    
    String Names [] = {"Profiles","Genres & Moods","Albums","Playlist","Songs","Artists"};
    String FirstItems []={
        "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.TextView",
         "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.TextView",
         "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]",
        "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView",
        "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]",
         "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.TextView",};
    String titles []={
        "com.spotify.music:id/profile_title",
        "com.spotify.music:id/header_title",
        "com.spotify.music:id/title",
        "android:id/text1",
        "com.spotify.music:id/title",
        "com.spotify.music:id/txt_title",
    };

    @Test(priority = 1)
    void Search() throws MalformedURLException, InterruptedException
    {
        try
        {
            searchRep = search.createTest("search test");
            testInput ("A",1);
            testInput ("As",2);
            testInput(garbageInput,0);

            getElementById(searchIconID).click();
            getElementById(searchBarID).click();
            while(getItemWebViewXpath(recentResearchXPath).size()==0){}
            if (getElementByXpath(recentResearchXPath).getText().toLowerCase().contains("as"))   searchRep.log(Status.PASS, "recent searches passes");
            else searchRep.log(Status.FAIL, "recent searches fails");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void testInput(String input,int type) {
        getElementById(searchIconID).click();
        getElementById(searchBarID).click();
        while(getItemWebViewId(inputTextID).size()==0){}
        getElementById(inputTextID).sendKeys(input);

        if (type==0) {
            if (getElementById(searchTextID).getText().contains(input))   searchRep.log(Status.PASS, "search in  with garbage input passes");
            else searchRep.log(Status.FAIL, "search in  with garbage input  fails"); 
            back();
        }
        else{
            ScrollUntilXpath(seeAll[0], true, 0);
            for (int i = 0; i < seeAll.length; i++) {
                SeeAll(seeAll[i], Names[i],input,FirstItems[i],titles[i],type);
            }
        }
    }

    void SeeAll(String XPath, String Name, String searchText, String firstItem,String Title,int type)
    {
        try{
            getElementByXpath(XPath).click();
            
            if(getElementById(textAfterSearchID).getText().contains(Name))     searchRep.log(Status.PASS, "search title in "+ Name + " with input "+searchText+" passes");
            else searchRep.log(Status.FAIL, "search title in "+ Name+ " with input "+searchText+" fails");

            MobileElement FirstItem = getElementByXpath(firstItem);
            if(FirstItem.getText().toLowerCase().contains(searchText.toLowerCase()))     searchRep.log(Status.PASS, "search in "+ Name + " with input "+searchText+" passes");
            else searchRep.log(Status.FAIL, "search in "+ Name+ " with input "+searchText+" fails");

            if (type==2)
            {
                FirstItem.click();
                if(getElementById(Title).getText().toLowerCase().contains(searchText.toLowerCase()))    searchRep.log(Status.PASS, "clicking on the result in "+ Name + " with input "+searchText+" passes");
                else searchRep.log(Status.FAIL, "clicking on the result in "+ Name + " with input "+searchText+" fails");
                back();
            }

            
        }
        catch(Exception e)
        {
            searchRep.log(Status.FAIL, "search in "+ Name+ " with input "+searchText+" fails");
            System.out.println(e.getMessage());
        }
        back();
    }

    
}