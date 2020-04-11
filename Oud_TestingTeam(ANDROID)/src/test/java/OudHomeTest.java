
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

interface Xpath{
String SearchXpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.FrameLayout/android.widget.TextView";
String RenameXpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout";
String EditName="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]";
String LibraryXpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.FrameLayout/android.widget.TextView";
String PremuimXpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.FrameLayout/android.widget.TextView";
String SettingsXpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.widget.FrameLayout/android.widget.TextView";
String PlayList0="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[1]";
String artist10="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.TextView[1]";
String artist14_10="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.ImageView";
String artist14_14="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[3]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView[1]";

}
interface IDS{
    String Home_ButtonID="com.example.oud:id/btn_home_test";
    String Search_IconID="com.example.oud:id/navigation_search";
    String Library_IconID="com.example.oud:id/navigation_library";
    String Premium_IconID="com.example.oud:id/navigation_premium";
    String Setting_IconID="com.example.oud:id/navigation_settings";
    String ViewProfile_ButtonID="com.example.oud:id/btn_view_profile_test";
    String Rename_ButtonID="com.example.oud:id/btn_rename_profile";
    String Rename_BoxID="com.example.oud:id/edit_text_rename";
    String DisplayNameBoxID="com.example.oud:id/text_profile_fragment_display_name";
    String SettingsButtonID="com.example.oud:id/btn_profile_options";
    String HomeIconID="com.example.oud:id/navigation_home";
    String ChangePlayListNameButtonID="com.example.oud:id/btn_rename_playlist";
    String ChangePlayListNameBoxID="com.example.oud:id/edit_text_rename";
    String RenamePlayListButtonID="com.example.oud:id/btn_rename";
    String PlayListNameBoxID="com.example.oud:id/txt_playlist_name";
    String AboutArtist="com.example.oud:id/txt_artist_about_bio";
    String ArtistNameBoxID="com.example.oud:id/txt_artist_name";
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdalla mahmoud
 */
public class OudHomeTest extends BaseClass{
    ExtentTest test;

    @Test(priority = 4)
    public void Home(){

        // Testing the routes and the Playlists ..
       test = Home_Account.createTest("Checking the Playlists and Artists ");// adding the test to the report..
       getElementById(IDS.HomeIconID).click();
       Wait(4000);
       getElementByXpath(Xpath.PlayList0).click();
       Wait(2000);
       getElementById(IDS.ChangePlayListNameButtonID).click();
       getElementById(IDS.ChangePlayListNameBoxID).clear();
       getElementById(IDS.ChangePlayListNameBoxID).sendKeys("Test.Name.Playlist");
       getElementById(IDS.RenamePlayListButtonID).click();
       boolean fail = false ;
       // Changing the playlist without changing the route this test for test the frontend ..
       String PlayListName=getElementById(IDS.PlayListNameBoxID).getText();
       try{
            Assert.assertEquals(PlayListName,"Test.Name.Playlist");
       }catch(AssertionError exp){
           System.out.println(exp.getMessage());
           System.out.println("Changing Playlist Name button Failed");
           fail = true ;
       }
       if(fail){
           test.log(Status.FAIL,"Changing Playlist name fails without changing the route");
           fail = false ;
       }
       else {
           test.log(Status.PASS,"Changing Playlist name passes without changing the route");
       }

       back();
       // Changing the playlist then change the route and go the the playlist again and check its name  this test for the backend ..
       String Text=getElementByXpath(Xpath.PlayList0).getText();
       try{
            Assert.assertEquals(Text,"Test.Name.Playlist");
       }catch(AssertionError exp){
           System.out.println(exp.getMessage());
           System.out.println("Changing Playlist Name Failed");
           fail = true ;
       }
       if(fail){
           test.log(Status.FAIL,"Changing Playlist name fails after  changing the route");
           fail = false ;
       }
       else {

           test.log(Status.PASS,"Changing Playlist name passes after changing the route");
       }

       // check the artists and its home
       getElementByXpath(Xpath.artist10).click();
       Wait(4000);
       ScrollDownArtistPage();
       // it is different from the usual scroll by the ratio of the scrolling
       for (int i = 0 ; i<3;i++)
           Scroll(true);

       getElementByXpath(Xpath.artist14_10).click();
       Wait(4000);
       ScrollDownArtistPage();
       for (int i = 0 ; i<3;i++)
           Scroll(true);
      // check if the artist appears in its home again !!
       Text=getElementByXpath(Xpath.artist14_14).getText();
       try{
           Assert.assertNotEquals(Text,"artist14");
       }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("Arist should not appear in his home's Similar artists");
           fail = true ;
       }
       if(fail){
           test.log(Status.FAIL,"Arist should not appear in his home's Similar artists");
           fail = false ;
       }
       else {

           test.log(Status.PASS,"Artist's home similar artist passes ");
       }
       // checking the bio
       Text=getElementById(IDS.AboutArtist).getText();
        try{
           Assert.assertEquals(Text,"I'm artist14");
       }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("The bio have some problem ");
           fail = true ;
       }
       back();
       Text=getElementById(IDS.ArtistNameBoxID).getText();
       try{
           Assert.assertEquals(Text,"artist10");
       }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("The routes have some problems");
           fail = true ;
       }
        if(fail){
           test.log(Status.FAIL,"Routes in the Home Fails ");
           fail = false ;
       }
       else {

           test.log(Status.PASS,"Routes in Home is correct ");
       }
    }

    @Test(priority = 1)
    public void openHome(){
       // opening the home for the testing
        MobileElement home = getElementById(IDS.Home_ButtonID);
        home.click();
        Wait(2000);
    }

    @Test(priority = 3)
    public void CheckAccount()
    {
         test=Home_Account.createTest("AccountTests"); // another test for the account to be written in the report
         // changing the Account Name by two methods the first method by the pen icon after the Name of hte account
         // the second method form the settings

         // check changing the Account name without changing the routes ..
         getElementById(IDS.ViewProfile_ButtonID).click();
         getElementById(IDS.Rename_ButtonID).click();
         MobileElement AccountNameBox=getElementById(IDS.Rename_BoxID);
         AccountNameBox.clear();
         AccountNameBox.sendKeys("Test.Name");
         Wait(2000);
         getElementByXpath(Xpath.RenameXpath).click();

         String Text = getElementById(IDS.DisplayNameBoxID).getText();
         boolean fail = false ;
         try{
          Assert.assertEquals(Text,"Test.Name");
         }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("Changing Name By Pen Icon test fails ");
           fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "Changing Account Name by pen Icon Fails");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Changing Account Name by pen Icon is correct without changing the route");
         }

         //changing the routes and check if the Name changed or not
         System.out.println(Text);
         getElementById(IDS.Search_IconID).click();
         getElementById(IDS.Setting_IconID).click();
         getElementById(IDS.ViewProfile_ButtonID).click();
         String Text2 = getElementById(IDS.DisplayNameBoxID).getText();

         try
         {
            Assert.assertEquals(Text2,Text);// printting for now but it means assertion ..
         }
         catch(AssertionError exp){
             System.out.println(exp.getMessage());
             System.out.println("Changing Name By Pen Icon test fails ");
             fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "Changing Account Name by pen Icon Fails after changing the route fails");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Changing Account Name by pen Icon after changing the route is correct ");
         }
         // Testing the rename button from the pen button
         // the same previous tests but with the settings .
         getElementById(IDS.SettingsButtonID).click();
         getElementByXpath(Xpath.EditName).click();

         AccountNameBox.clear();
         AccountNameBox.sendKeys("Test.Name");
         Wait(2000);
         getElementByXpath(Xpath.RenameXpath).click();
         Text = getElementById(IDS.DisplayNameBoxID).getText();
          try{
          Assert.assertEquals(Text,"Test.Name");
         }catch(AssertionError exp){

          System.out.println(exp.getMessage());
          System.out.println("Changing Name using the settings buttons Fails ");
          fail =false ;
         }
          if(fail){

             test.log(Status.FAIL, "Changing Account Name by settings button fails without changing the routes ");
             fail = true  ;
         }
         else {

             test.log(Status.PASS, "Changing Account Name by settings icon  is correct without changing the route");
         }
         getElementById(IDS.Search_IconID).click();
         getElementById(IDS.Setting_IconID).click();
         getElementById(IDS.ViewProfile_ButtonID).click();
         Text2 = getElementById(IDS.DisplayNameBoxID).getText();
         try
         {
             Assert.assertEquals(Text2,Text);
         }

         catch(AssertionError exp){

             System.out.println(exp.getMessage());
             System.out.println("Changing Name using the settings buttons Fails ");
             fail = true ;
         }
         if(fail){

             test.log(Status.FAIL, "Changing Account Name by settings   Icon Fails after chainging the routes ");
             fail = true ;
         }
         else {
             test.log(Status.PASS, "Changing Account Name by settings  Icon is correct after changing the route");
         }
    }

    @Test(priority = 2)
    public void CheckButtons()
    {
         test=Home_Account.createTest("HomeTest"); // Creat the test for the report
         // Test the routes after pressing on the buttons
         // the first one for the search button ..
         getElementById(IDS.Search_IconID).click();
         String TexT=getElementByXpath(Xpath.SearchXpath).getText();

         boolean Fail=false ;
         try{
            Assert.assertEquals(TexT,"Search");// printting for now but it means assertion ..
         }catch(AssertionError exp){
             System.out.println(exp.getMessage());
             Fail=true;
         }
         if(Fail){
             test.log(Status.FAIL,"wrong in the search route");
             Fail=false ;
         }
         else {
             test.log(Status.PASS, "Search route is Correct");
         }

         // the second one for the Library button
         getElementById(IDS.Library_IconID).click();
         TexT=getElementByXpath(Xpath.LibraryXpath).getText();

         try{
            Assert.assertEquals(TexT,"Library");
         }catch(AssertionError exp){
             System.out.println(exp.getMessage());
         }
          if(Fail){
             test.log(Status.FAIL,"wrong in the Library route");
             Fail=false ;
         }
         else {
             test.log(Status.PASS, "Library route is Correct");
         }
         // the thrid one for the premium button ..
         getElementById(IDS.Premium_IconID).click();
         TexT=getElementByXpath(Xpath.PremuimXpath).getText();

         try{
            Assert.assertEquals(TexT,"Premium");
         }catch(AssertionError exp){
             System.out.println(exp.getMessage());
         }
          if(Fail){

             test.log(Status.FAIL,"wrong in the premium route");
             Fail=false ;
         }
         else {
             test.log(Status.PASS, "Premium route is Correct");
         }

         // the last one for the  settings
         getElementById(IDS.Setting_IconID).click();
         TexT=getElementByXpath(Xpath.SettingsXpath).getText();

         try{
            Assert.assertEquals(TexT,"Settings");
         }catch(AssertionError exp){
             System.out.println(exp.getMessage());
         }
          if(Fail){

             test.log(Status.FAIL,"wrong in the settings route");
             Fail=false ;
         }
         else {
             test.log(Status.PASS, "Search Settings is Correct");
         }
    }

    public void ScrollDownArtistPage() {
        Dimension dimension = driver.manage().window().getSize();
        Double scrollHeightStart = dimension.getHeight() * 0.9;
        int scrollStart = scrollHeightStart.intValue();

        Double scrollHeightEnd = dimension.getHeight() * 0.2;
        int scrollEnd = scrollHeightEnd.intValue();

        new TouchAction((PerformsTouchActions) driver)
        .press(PointOption.point(0, scrollStart))
        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
        .moveTo(PointOption.point(0, scrollEnd))
        .release().perform();
    }
}
