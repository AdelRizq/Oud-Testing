/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdalla mahmoud
 */
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

interface ids {
String Homeid="com.spotify.music:id/home_body"; 
String iconid="android:id/icon"; 
String Editid="com.spotify.music:id/edit_button"; 
String Editname= "com.spotify.music:id/edit_displayname"; 
String titleid = "com.spotify.music:id/profile_title"; 
String libid= "com.spotify.music:id/your_library_tab"; 
String editplaylistid="com.spotify.music:id/edit_text"; 
String createbutton="com.spotify.music:id/continue_button";
String playlistid="android:id/text1"; 
String hometabid= "com.spotify.music:id/home_tab"; 
String Profileplaylist="com.spotify.music:id/labels"; 
String playlisttab="com.spotify.music:id/playlists_layout"; 
String Iconid="com.spotify.music:id/glue_overflow"; 
String Deleteid="com.spotify.music:id/button_positive"; 
String countid="com.spotify.music:id/playlists_count"; 
String search_tab="com.spotify.music:id/search_tab"; 
String search_box="com.spotify.music:id/find_search_field_text"; 
String sia_id="com.spotify.music:id/row_view"; 
String search_query="com.spotify.music:id/query"; 
String imageid="com.spotify.music:id/img_picture" ; 
String followid = "com.spotify.music:id/follow_button";
String friendfollowerscounter="com.spotify.music:id/followers_count";
String Followingcounter="com.spotify.music:id/following_count";
String Filter="com.spotify.music:id/filter"; 
String track_cloud="com.spotify.music:id/track_cloud"; 
String bioid="com.spotify.music:id/biography"; 
};

interface xpath{

String FirstArtist="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget.RecyclerView[1]/android.view.ViewGroup[1]/android.widget.ImageView";
String icon="//android.widget.ImageButton[@content-desc=\"Settings\"]"; 
String Save="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button"; 
String createplaylist="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout"; 
String Profileplaylistname="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]";
String createdPlaylist="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]"; 
String PublicPlaylistname="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]"; 
String Credit="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout"; 
String Delete="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[4]"; 
String Creditname="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]"; 
String share="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[5]"; 
String copy="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[6]"; 
String rename="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[3]"; 
String find_friends="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]"; 
String friend="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.ListView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView"; 
String Followingcounter="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.TextView[1]"; 
String Farid="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.ListView/android.widget.FrameLayout[2]/android.widget.LinearLayout";
String Faridplaylist="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout"; 
String sellall="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[5]/android.widget.LinearLayout/android.widget.TextView"; 
String afterall="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[5]/android.widget.LinearLayout/android.widget.LinearLayout"; 
String loveid="//android.widget.ImageButton[@content-desc=\"Like\"]"; 
String FirstPlaylist="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]"; 
String unlike="//android.widget.ImageButton[@content-desc=\"Unlike\"]"; 
String FollowingCounter="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.TextView[1]"; 
String Gener1="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.TextView"; 
String Firstsong="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]"; 
String Gener2="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget.RecyclerView[1]/android.widget.LinearLayout/android.widget.TextView"; 
String relatedartist="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget.RecyclerView[2]/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView"; 
}
//Instrumental


public class Profile extends BaseClass {
   ExtentTest test;

   @Test(priority = 1)
   public void openHome(){
       while (getItemWebViewId(ids.Homeid).size()==0)
       {}
        Wait(4000);     
    }
   
  @Test(priority=2)
   public void go_to_profile(){
   test=Profile.createTest("Editing Profile name "); // another test for the account to be written in the report

   getElementByXpath(xpath.icon).click();
   Wait(2000); 
   getElementById(ids.iconid).click(); 
   Wait(1000); 
   //  start Editing profile name  ; 
   getElementById(ids.Editid).click();
   MobileElement ProfileNameBox=getElementById(ids.Editname);
   ProfileNameBox.clear();
   ProfileNameBox.sendKeys("Test.Name");
   getElementByXpath(xpath.Save).click();
   Wait(500); 
   String Text = getElementById(ids.titleid).getText();
   boolean fail = false ;
         try{
          Assert.assertEquals(Text,"Test.Name");
         }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("Editing the profile name fails ");
           fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "Changing Profile name fails ");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Changing Profile name passes ");
         }
         
         fail = false ; 
   
   // Ending Editing 
   ///////////////////////////////////////
   // Reset profile name to the last one 
   getElementById(ids.Editid).click();
   ProfileNameBox=getElementById(ids.Editname);
   ProfileNameBox.clear();
   ProfileNameBox.sendKeys("Abdalla Mahmoud");
   getElementByXpath(xpath.Save).click();
   Wait(500);
   Text = getElementById(ids.titleid).getText();
  try{
          Assert.assertEquals(Text,"Abdalla Mahmoud");
         }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("reset the profile name fails ");
           fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "reset Profile name fails ");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Changing Profile name passes ");
         }
   
   // Ending reset profile name 
   }
   ////////////////////////////////////////////////////////////////
  @Test(priority=3)
   
   public void  createplaylist(){
       test=Profile.createTest("Creating Playlist");
      getElementById(ids.libid).click();
      getElementByXpath(xpath.createplaylist).click();
      
      
      MobileElement ProfileNameBox=getElementById(ids.editplaylistid);
      ProfileNameBox.clear();
      ProfileNameBox.sendKeys("Test.Name");
      getElementById(ids.createbutton).click();

      Wait(2000) ;
      
      
      String Text = getElementById(ids.playlistid).getText();
      //////////////////////////////////////////////////////////////////////////////////
      boolean fail = false ;
         try{
          Assert.assertEquals(Text,"Test.Name");
         }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("Creating Playlist  fails ");
           fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "Creating Playlist fails ");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Creating Playlist appears in Libirary ");
         }
         
         fail = false ; 

      
      getElementById(ids.hometabid).click();
      Wait(1000); 
      
      
     getElementByXpath(xpath.icon).click();
     Wait(2000); 
     getElementById(ids.iconid).click(); 
     Wait(1000); 

      Text = getElementByXpath(xpath.Profileplaylistname).getText();
      /////////////////////////////////////////////////////////////////////////////////////
      
     try{
          Assert.assertEquals(Text,"Test.Name");
         }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("Creating Playlist doesn't affect the Profile ");
           fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "Creating Playlist doesn't affect the Profile ");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Creating Playlist affect the Profile");
         }
         
         fail = false ; 

      
      getElementById(ids.playlisttab).click();
      
      Text = getElementByXpath(xpath.createdPlaylist).getText();
       /////////////////////////////////////////////////////////////////////////////////////
      
     try{
          Assert.assertEquals(Text,"Test.Name");
         }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("Creating Playlist doesn't affect Public library ");
           fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "Creating Playlist doesn't affect Public Library ");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Creating Playlist affect Public library");
         }
         
         fail = false ; 
        getElementById(ids.libid).click();
      getElementByXpath(xpath.createplaylist).click();
      
      
      ProfileNameBox=getElementById(ids.editplaylistid);
      ProfileNameBox.clear();
      ProfileNameBox.sendKeys("Credit");
      getElementById(ids.createbutton).click();

      Wait(4000) ;
   }
   
  @Test(priority=4)
   public void Delete_playlist(){
   test=Profile.createTest("Deleting Playlist");
   getElementById(ids.hometabid).click();
   getElementByXpath(xpath.icon).click();
   Wait(2000); 
   getElementById(ids.iconid).click(); 
   Wait(1000); 
   
   getElementByXpath(xpath.Credit).click();
   getElementById(ids.Iconid).click();
   
   getElementByXpath(xpath.Delete).click();
   
   getElementById(ids.Deleteid).click();
   Wait(3000); 
   String Text=getElementByXpath(xpath.Creditname).getText();
   
   //////////////////////////////////////////////////////////////////////////////////
    boolean fail = false ;
       try{
        Assert.assertNotEquals(Text,"Credit");
       }catch(AssertionError exp){

         System.out.println(exp.getMessage());
         System.out.println("Deleting Playlist fails");
         fail =true ;
       }
       if(fail){

           test.log(Status.FAIL, "Deleting Playlist fails ");
           fail = false ;
       }
       else {

           test.log(Status.PASS, "Deleting Playlist passes  ");
       }

       fail = false ; 

  Text= getElementById(ids.countid).getText();
  try{
          Assert.assertNotEquals(Text,"2");
         }catch(AssertionError exp){

           System.out.println(exp.getMessage());
           System.out.println("Counter after Deleting Playlist fails");
           fail =true ;
         }
         if(fail){

             test.log(Status.FAIL, "Counter after Deleting Playlist Fails  ");
             fail = false ;
         }
         else {

             test.log(Status.PASS, "Counter after Deleting Playlist Fails  ");
         }
         
         fail = false ; 
   }
   @Test(priority=5)
   public void share(){
      test=Profile.createTest("Sharing My own Playlist");

      getElementById(ids.hometabid).click();
      getElementByXpath(xpath.icon).click();
      Wait(2000); 
      getElementById(ids.iconid).click(); 
      Wait(3000); 
      getElementByXpath(xpath.Creditname).click();
      Wait(2000);
      getElementById(ids.Iconid).click();
      Wait(2000); 
      getElementByXpath(xpath.share).click();
      Wait(2000);
      getElementByXpath(xpath.copy).click();
      Wait(2000); 
      System.out.println(driver.getClipboardText());
     String Text = driver.getClipboardText(); 
      boolean fail = false ;
       try{
        Assert.assertNotEquals(Text,"?V?V");
       }catch(AssertionError exp){

         System.out.println(exp.getMessage());
         System.out.println("sharing Fails ");
         fail =true ;
       }
       if(fail){

           test.log(Status.FAIL, "Sharing  fails ");
           fail = false ;
       }
       else {

           test.log(Status.PASS, "Sharing passes  ");
       }

       fail = false ; 
      
   }
  
   @Test(priority=6)
   public void follow_unfollow(){
  //  setup();
  //   while (getItemWebViewId(ids.Homeid).size()==0)
    //  {}
     test=FacebookProfile.createTest("Follow Unfollow");
    getElementById(ids.hometabid).click();
    getElementByXpath(xpath.icon).click();
    Wait(5000); 
    getElementById(ids.iconid).click(); 
    Wait(5000); 
    getElementById(ids.Iconid).click();
    Wait(5000); 
    getElementByXpath(xpath.find_friends).click(); 
    getElementByXpath(xpath.friend).click(); 
    Wait(5000); 
    getElementById(ids.followid).click();
    String Text =getElementById(ids.followid).getText();
    
    boolean fail = false ;
       try{
        Assert.assertEquals(Text,"FOLLOWING");
       }catch(AssertionError exp){

         System.out.println(exp.getMessage());
         System.out.println("FOLLOWING Fails ");
         fail =true ;
       }
       if(fail){

           test.log(Status.FAIL, "FOLLOWING fails ");
           fail = false ;
       }
       else {

           test.log(Status.PASS, "FOLLOWING passes  ");
       }

       fail = false ; 
    Text =getElementById(ids.friendfollowerscounter).getText();
     try{
        Assert.assertEquals(Text,"1");
       }catch(AssertionError exp){

         System.out.println(exp.getMessage());
         System.out.println("FOLLOWERS counter Fails ");
         fail =true ;
       }
       if(fail){

           test.log(Status.FAIL, "FOLLOWERS  counter fails ");
           fail = false ;
       }
       else {

           test.log(Status.PASS, "FOLLOWERS counter  passes  ");
       }

       fail = false ; 
    getElementById(ids.hometabid).click();
    getElementByXpath(xpath.icon).click();
    Wait(5000); 
    getElementById(ids.iconid).click(); 
    Wait(5000); 
    Text=getElementById(ids.Followingcounter).getText(); 
    try{
        Assert.assertEquals(Text,"1");
       }catch(AssertionError exp){

         System.out.println(exp.getMessage());
         System.out.println("FOLLOWING counter Fails ");
         fail =true ;
       }
       if(fail){

           test.log(Status.FAIL, "FOLLOWING  counter fails ");
           fail = false ;
       }
       else {

           test.log(Status.PASS, "FOLLOWING counter  passes  ");
       }

       fail = false ; 
   }
   @Test(priority=7)
   public void adding_friend_playlist(){
    test=FacebookProfile.createTest("adding friend playlist");   
    getElementById(ids.hometabid).click();
    getElementByXpath(xpath.icon).click();
    Wait(5000); 
    getElementById(ids.iconid).click(); 
    Wait(5000); 
    getElementById(ids.Iconid).click();
    Wait(5000); 
    getElementByXpath(xpath.find_friends).click();
    Scroll(false);
    getElementById(ids.Filter).clear();
    getElementById(ids.Filter).sendKeys("abdel");

    Wait(5000);
   getElementByXpath(xpath.Farid).click();
   Wait(5000) ; 
   ScrollDownArtistPage(); 
  
   getElementByXpath(xpath.Faridplaylist).click();
   Wait(5000);
   getElementByXpath(xpath.loveid).click();
   getElementById(ids.libid).click();
   String Text = getElementByXpath(xpath.FirstPlaylist).getText();
  boolean fail = false ;
       try{
        Assert.assertEquals(Text,"headspace");
       }catch(AssertionError exp){

         System.out.println(exp.getMessage());
         System.out.println("Follwoing Frineds Playlists Fails  ");
         fail =true ;
       }
       if(fail){

           test.log(Status.FAIL, "Follwoing Frineds Playlists Fails  ");
           fail = false ;
       }
       else {

           test.log(Status.PASS, "Follwoing Frineds Playlists  passes  ");
       }

       fail = false ; 
   getElementByXpath(xpath.FirstPlaylist).click();
   Wait(5000); 
   getElementByXpath(xpath.unlike).click();
   Text=getElementById(ids.libid).getText();
    try{
        Assert.assertNotEquals(Text,"headspace");
       }catch(AssertionError exp){

         System.out.println(exp.getMessage());
         System.out.println("Unfollowing Friends list Fails ");
         fail =true ;
       }
       if(fail){

           test.log(Status.FAIL, "UnFollowing  Frineds Playlists Fails  ");
           fail = false ;
       }
       else {

           test.log(Status.PASS, "UnFollwoing Frineds Playlists  passes  ");
       }

       fail = false ; 
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
