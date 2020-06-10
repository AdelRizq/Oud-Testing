
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

@SuppressWarnings("deprecation")
public class extentReports {

	ExtentHtmlReporter homeReporter, signupReporter, loginReporter, playerReporter,libraryArtistReporter,libraryAlbumReporter,libraryPlaylistReporter,searchReporter, playlistReporter, sharingReporter;

	ExtentReports signup, home, login, Home_Account, PlayerRepo, libraryArtist, libraryAlbum, libraryPlaylist, search, playlist, sharing,Profile,FacebookProfile;

	@BeforeSuite
	public void reportSetup() {
		signupReporter = new ExtentHtmlReporter("reports/signup.html");
		homeReporter = new ExtentHtmlReporter("reports/Home_Account.html");
		loginReporter = new ExtentHtmlReporter("reports/login.html");
		playerReporter = new ExtentHtmlReporter("reports/Player.html");
		playlistReporter = new ExtentHtmlReporter("reports/playlist.html");
		sharingReporter = new ExtentHtmlReporter("reports/sharing.html");

		libraryArtistReporter = new ExtentHtmlReporter("reports/libraryArtist.html");
		libraryAlbumReporter= new ExtentHtmlReporter("reports/libraryAlbum.html");
		libraryPlaylistReporter = new ExtentHtmlReporter("reports/libraryPlaylist.html");
		searchReporter = new  ExtentHtmlReporter("reports/search.html");
		

		Home_Account = new ExtentReports();
		signup = new ExtentReports();
		login = new ExtentReports();
		PlayerRepo = new ExtentReports();
		playlist = new ExtentReports();
		sharing = new ExtentReports();

		libraryAlbum    = new ExtentReports();
		libraryArtist   = new ExtentReports();
		libraryPlaylist = new ExtentReports();
		search = new ExtentReports();
		Home_Account.attachReporter(homeReporter);
		PlayerRepo.attachReporter(playerReporter);
		signup.attachReporter(signupReporter);
		playlist.attachReporter(playlistReporter);
		sharing.attachReporter(sharingReporter);

		login.attachReporter(loginReporter);
		libraryAlbum.attachReporter(libraryAlbumReporter); 
		libraryArtist.attachReporter(libraryArtistReporter); 
		libraryPlaylist.attachReporter(libraryPlaylistReporter); 
		search.attachReporter(searchReporter);
	}
@BeforeSuite
         public void ProfileAccountreportSetup() {
		htmlReporter = new ExtentHtmlReporter("reports/Profile.html");
		avent = new ExtentAventReporter("reports/Profile_A.html");
		bdd = new ExtentBDDReporter("reports/Profile_B.html");

		Profile = new ExtentReports();
		Profile.attachReporter(avent, bdd, htmlReporter);
	}
          @BeforeSuite
         public void FacebookprofilereportSetup() {
		htmlReporter = new ExtentHtmlReporter("reports/FacebookProfile.html");
		avent = new ExtentAventReporter("reports/FacebookProfile_A.html");
		bdd = new ExtentBDDReporter("reports/FacebookProfile_B.html");

		FacebookProfile = new ExtentReports();
		FacebookProfile.attachReporter(avent, bdd, htmlReporter);
	}

	@AfterSuite
	public void artistFlush()
	{
		libraryArtist.flush();
	}
	@AfterSuite
	public void playlistFlush()
	{
		libraryAlbum.flush();
	}
	@AfterSuite
	public void albumFlush()
	{
		libraryPlaylist.flush();
	}
	@AfterSuite
	public void searchFlush()
	{
		search.flush();
	}

	@AfterSuite
	public void playlistsFlush()
	{
		playlist.flush();
	}
	@AfterSuite
	public void sharingFlush()
	{
		sharing.flush();
	}
      @AfterSuite
        public void ProfileAccounttearDown() {
		// calling flush writes everything to the log file
		Profile.flush();
	}
         @AfterSuite
        public void FacebookProfiletearDown() {
		// calling flush writes everything to the log file
		FacebookProfile.flush();
	}
}
