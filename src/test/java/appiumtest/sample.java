package appiumtest;
import java.net.URL;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.parser.ParserException;


import io.appium.java_client.AppiumDriver;

public class sample {
	 
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
		
		@DataProvider(name="UserLogin")
		public String[] readJson () throws IOException,ParserException, ParseException {
			String data= System.getProperty("user.dir"); 
			JSONParser jsonParser= new JSONParser();
			FileReader reader= new FileReader	(System.getProperty("user.dir") + "/src/test/java/com.vision_plus.data/datalogin.json");
			
			
			Object obj=jsonParser.parse(reader);
			
			JSONObject userLoginsJsonobj=(JSONObject) obj ;
			JSONArray userLoginsArray=(JSONArray) userLoginsJsonobj.get("user");
			
			String arr[]= new String[userLoginsArray.size()];
			for (int i=0; i<userLoginsArray.size();i++);
			{
				int i = 0;
				JSONObject users = (JSONObject) userLoginsArray.get(i);
				String user=(String) users.get("phone");
				String pwd = (String) users.get("password");
				
				
				arr[i]=user+","+pwd;
			}
			
					return arr;
//			
		}
}

