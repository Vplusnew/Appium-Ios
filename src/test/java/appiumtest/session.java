package appiumtest;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;

public class session {
	 
	protected AppiumDriver driver ;
	@BeforeTest
		 public  void setup () throws MalformedURLException, InterruptedException {
		
		try {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("automationName","XCUITest");
		cap.setCapability("platformName", "iOS");
		cap.setCapability("deviceName", "iPhone 11 Pro Max");
		cap.setCapability("platformVersion", "15.2");
		cap.setCapability("UDID", "519F38C9-23D1-44CF-8CD0-AAAB975A9B9B");
		cap.setCapability("browserName", "Safari");
		
		URL url = new URL ("http://127.0.0.1:4723/wd/hub") ;
		
		driver = new AppiumDriver (url,cap);
//		driver = new IOSDriver (url,cap);
//		driver.get("https://www.visionplus.id");
//		driver.findElement(By.xpath("(//img[@alt='others'])[1]")).click();
//		
		} catch(Exception exp) {
//		  System.out.println("Cause is  : "+exp.getCause());
//		  System.out.println("Message is :"+exp.getMessage());
		  exp.printStackTrace();
		}
	}
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
			String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
			
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent,
					new TypeReference<List<HashMap<String, String>>>() {
			});
			
			return data;	
		}
		
	}
