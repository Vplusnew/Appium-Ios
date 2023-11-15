package IOSDriverManager;

import java.net.URL;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
public class IOSDriverManager {
	 
	public static IOSDriverManager driver;
	
	@Test
	 public static void test () throws MalformedURLException, InterruptedException {
		
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("automationName","XCUITest");
		cap.setCapability("platformName", "iOS");
		cap.setCapability("deviceName", "iPhone 11 Pro Max");
		cap.setCapability("platformVersion", "15.2");
		cap.setCapability("UDID", "519F38C9-23D1-44CF-8CD0-AAAB975A9B9B");
		cap.setCapability("browserName", "Safari");
		
		IOSDriver driver = new IOSDriver (new URL("http://127.0.0.1:4723/wd/hub"), cap );
		driver.get("https://www.visionplus.id");
		driver.findElement(By.xpath("(//img[@alt='others'])[1]")).click();
		
	}

	public void get(String string) {
		// TODO Auto-generated method stub
		
	}
	
	public static void TC1() throws InterruptedException {}
	

}
