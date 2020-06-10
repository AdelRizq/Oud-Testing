
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

@SuppressWarnings("deprecation")
public class extentReports {

	ExtentHtmlReporter homeReporter, signupReporter, loginReporter, playerReporter,libraryArtistReporter,libraryAlbumReporter,libraryPlaylistReporter,searchReporter;

	ExtentReports signup, home, login, Home_Account, PlayerRepo,libraryArtist,libraryAlbum,libraryPlaylist,search;

	@BeforeSuite
	public void reportSetup() {
		signupReporter = new ExtentHtmlReporter("reports/signup.html");
		homeReporter = new ExtentHtmlReporter("reports/Home_Account.html");
		loginReporter = new ExtentHtmlReporter("reports/login.html");
		playerReporter = new ExtentHtmlReporter("reports/Player.html");

		libraryArtistReporter = new ExtentHtmlReporter("reports/libraryArtist.html");
		libraryAlbumReporter= new ExtentHtmlReporter("reports/libraryAlbum.html");
		libraryPlaylistReporter = new ExtentHtmlReporter("reports/libraryPlaylist.html");
		searchReporter = new  ExtentHtmlReporter("reports/search.html");
		


		
		Home_Account = new ExtentReports();
		signup = new ExtentReports();
		login = new ExtentReports();
		PlayerRepo = new ExtentReports();

		libraryAlbum    = new ExtentReports();
		libraryArtist   = new ExtentReports();
		libraryPlaylist = new ExtentReports();
		search = new ExtentReports();
		Home_Account.attachReporter(homeReporter);
		PlayerRepo.attachReporter(playerReporter);
		signup.attachReporter(signupReporter);

		login.attachReporter(loginReporter);
		libraryAlbum.attachReporter(libraryAlbumReporter); 
		libraryArtist.attachReporter(libraryArtistReporter); 
		libraryPlaylist.attachReporter(libraryPlaylistReporter); 
		search.attachReporter(searchReporter);
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
}
