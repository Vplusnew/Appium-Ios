package TestCase;
import org.testng.annotations.Test;
import java.io.IOException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.jcraft.jsch.JSchException;

import IOSDriverManager.GetValueFromDatabase;
import appiumtest.PercobaanOTPFromDb;

import java.io.IOException;

public class TCGetOtpfromDb {
	 private static ChromeDriver driver;
    @Test
    public void main() throws JSchException, IOException, InterruptedException {
    	GetValueFromDatabase extractor = new GetValueFromDatabase();
        
        try {
            // Extract OTP
            String otpValue = extractor.OTP("otp1");
            
            char firstChar = otpValue.charAt(0);

            // Set up WebDriver
            WebDriverManager.chromedriver().setup();
            ChromeDriver driver = new ChromeDriver();
            driver.get("https://www.google.com/");

            // Send OTP to a textarea
            if (otpValue != null) {
                driver.findElement(By.xpath("(//textarea[@id='APjFqb'])[1]")).sendKeys(String.valueOf(firstChar));
            } else {
                System.out.println("No OTP found. Unable to fill the textarea.");
            }

            Thread.sleep(10000); // You may want to handle InterruptedException appropriately
        } catch (JSchException | IOException e) {
            // Handle JSchException and IOException as needed
            e.printStackTrace();
        } finally {
        	if (driver != null) {
                driver.quit();
                driver.close();            }
        }
        
    }
}