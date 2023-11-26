package TestCase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;

import IOSDriverManager.GetRandomNumber;
import IOSDriverManager.IOSDriverManager;
import appiumtest.SSHManager2;
import io.github.bonigarcia.wdm.WebDriverManager; 

public class TCGetRandomPhone extends SSHManager2 {

    @Test
    public void getPhone() throws InterruptedException, JSchException, IOException {
        // Set up WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.visionplus.id/");
        GetRandomNumber generator = new GetRandomNumber ();
       	String  Hasil = generator.number("number1");
            
        // Generate a random phone number with "0800" prefix using the method from the superclass
   
         Thread.sleep(1000);
         driver.findElement(By.xpath("(//button[@class='q-btn inline q-btn-item non-selectable login q-btn--unelevated q-btn--rectangle q-focusable q-hoverable'])[1]"))
 .click();
         Thread.sleep(1000);
         driver.findElement(By.xpath("(//input[@placeholder='8123456xxx'])[1]")).sendKeys(Hasil);
         Thread.sleep(1000);
         driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
         Thread.sleep(1000);
         driver.findElement(By.xpath("(//input[@placeholder='Your password'])[1]")).sendKeys("4321lupa");
         Thread.sleep(1000);
         driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
         Thread.sleep(1000);
        // Use WebDriverWait for better synchronization
//      Thread.sleep(10000);
//        driver.quit();


SSHManager2 extractor = new SSHManager2();

try {
	// Extract OTP
	String otpValuew = extractor.getOtp("otpget");

	char otp01 = otpValuew.charAt(0);
	char otp02 = otpValuew.charAt(1);
	char otp03 = otpValuew.charAt(2);
	char otp04 = otpValuew.charAt(3);
//
	driver.findElement(By.xpath("(//input[@placeholder='-'])[1]")).sendKeys(String.valueOf(otp01));
	Thread.sleep(500);
	driver.findElement(By.xpath("(//input[@placeholder='-'])[2]")).sendKeys(String.valueOf(otp02));
	Thread.sleep(500);
	driver.findElement(By.xpath("(//input[@placeholder='-'])[3]")).sendKeys(String.valueOf(otp03));
	Thread.sleep(500);
	driver.findElement(By.xpath("(//input[@placeholder='-'])[4]")).sendKeys(String.valueOf(otp04));

	Thread.sleep(10000); // You may want to handle InterruptedException appropriately
	} catch (JSchException | IOException e) {
		// Handle JSchException and IOException as needed
		e.printStackTrace();
	}}
}