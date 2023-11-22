package appiumtest;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;
import static net.sf.expectit.matcher.Matchers.contains;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import io.appium.java_client.AppiumDriver;
import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;

public class sampleDB {
	 
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
	public static void main(String[] args) throws JSchException, IOException, InterruptedException 
	 {

		String sshuser = "mncnow";
		String sshhost = "10.10.20.20";
		int sshport = 22;
		String sshpassword = "Welcome.21!--";
		String dbUser = "qa_vplus";
		String dbName = "sms_gateway";
		String dbHost = "10.10.128.146";
		String dbPort = "5432";
		String dbPassword = "qacredential";
		String SQLQuery = "SELECT otp FROM smsotp ORDER BY created_at desc LIMIT 1;"; // Replace with your SQL query

		JSch jsch = new JSch();
	
		Session session = jsch.getSession(sshuser, sshhost, sshport);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setPassword(sshpassword);
		session.connect();

		System.out.println("Connected");

		Channel channel = session.openChannel("shell");
		channel.connect();

		Expect expect = new ExpectBuilder().withOutput(channel.getOutputStream())
				.withInputs(channel.getInputStream(), channel.getExtInputStream()).withEchoInput(System.out)
				.withEchoOutput(System.err).withInputFilters(removeColors(), removeNonPrintable()).build();
		System.out.println("Channel Connected to machine ");


       expect.sendLine("sudo ssh ubuntu@10.10.128.146 -i keypem/p-sms-otp-db.pem" );
       expect.expect(contains("password"));
       expect.sendLine("Welcome.21!--");
       expect.expect(contains("ubuntu"));
       expect.sendLine("psql -U qa_vplus -d sms_gateway -h 10.10.128.146 -p 5432 -t -c \"" + SQLQuery + "\"");
       expect.expect(contains("Password"));
       expect.sendLine("qacredential");
       
       Thread.sleep(889);
	 }

	@AfterTest
	 public void tearDown() {
	 if (driver != null) {
	 driver.quit();
		
	 
	 }
	}
	
}
