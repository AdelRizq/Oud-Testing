
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdalla mahmoud
 */

public class claculatortest {
        // selenuim driver . . 
        
        //WebDriver  driver ; 
      static  AppiumDriver<MobileElement> driver ; 
    
        public static void main(String[] args){
            try{
                openCalculator();
            }catch(Exception exp){
                
                System.out.println(exp.getCause());
                System.out.println(exp.getMessage());
            }
        }
        public static void openCalculator() throws MalformedURLException{
        
            DesiredCapabilities cap = new DesiredCapabilities(); 
            cap.setCapability("deviceName", "G3312");
            cap.setCapability("udid", "WUJ01P6EQJ");
            cap.setCapability("platformName", "Android");
            cap.setCapability("platformversion", "7.0");
            // this all about for the devices 
            cap.setCapability("appPackage", "com.android.calculator2");
            cap.setCapability("appActivity", "com.android.calculator2.Calculator");
            // I want to send this desired cap to the driver .. 
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            // the url for the port and the IP that is used in appium server 
            driver = new AppiumDriver<MobileElement> (url,cap); 
            // passig the elements to the driver . 
            
            System.out.println("claculatortest.openCalculator()");
            
            MobileElement one= driver.findElement(By.id("com.android.calculator2:id/digit_1"));
            MobileElement two= driver.findElement(By.id("com.android.calculator2:id/digit_2"));
            MobileElement plus= driver.findElement(By.id("com.android.calculator2:id/op_add"));
            MobileElement equal= driver.findElement(By.id("com.android.calculator2:id/eq"));
            MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));
            one.click();
            plus.click();  
            two.click(); 
            equal.click(); 
            String res = result.getText();
            System.out.println(res);
            System.out.println("Completed>>>>>");
            



                    
                    
            
            
        }
}
