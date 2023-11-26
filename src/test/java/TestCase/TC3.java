package TestCase;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;

import IOSDriverManager.GetValueFromDatabase;
import IOSDriverManager.IOSDriverManager;

import org.testng.annotations.DataProvider;

import IOSDriverManager.GetRandomNumber;

public class TC3 extends IOSDriverManager {

	@Test(dataProvider = "dataLogin")
	public void TC2(HashMap<String, String> data) throws JSchException, IOException, InterruptedException {
		IOSDriverManager generated = new IOSDriverManager ();
		String Hasil = generated.generateRandomPhoneNumber();
		driver.get("https://www.visionplus.id");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//img[@alt='others'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/aside/div/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@placeholder='8123456xxx'])[1]")).sendKeys(Hasil);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@placeholder='Your password'])[1]")).sendKeys(data.get("password"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
		Thread.sleep(500);

		IOSDriverManager extractor = new IOSDriverManager();

		try {
			// Extract OTP
			String otpValue = extractor.OTPTry("otp1");

			char otp1 = otpValue.charAt(0);
			char otp2 = otpValue.charAt(1);
			char otp3 = otpValue.charAt(2);
			char otp4 = otpValue.charAt(3);

			driver.findElement(By.xpath("(//input[@placeholder='-'])[1]")).sendKeys(String.valueOf(otp1));
			Thread.sleep(500);
			driver.findElement(By.xpath("(//input[@placeholder='-'])[2]")).sendKeys(String.valueOf(otp2));
			Thread.sleep(500);
			driver.findElement(By.xpath("(//input[@placeholder='-'])[3]")).sendKeys(String.valueOf(otp3));
			Thread.sleep(500);
			driver.findElement(By.xpath("(//input[@placeholder='-'])[4]")).sendKeys(String.valueOf(otp4));

			Thread.sleep(10000); // You may want to handle InterruptedException appropriately
		} catch (JSchException | IOException e) {
			// Handle JSchException and IOException as needed
			e.printStackTrace();

		} finally {
			if (driver != null) {
//				driver.quit();

			}
		}

	}

	@DataProvider
	public Object[][] dataLogin() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "/src/test/java/com.vision_plus.data/datalogin.json");
		return new Object[][] { { data.get(0) } };

	}
}

// MKM TECHNIVAL ACCOUNT
// user : adityo.putro, pass : Adityo@430