package TestCase;


import IOSDriverManager.IOSDriverManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import IOSDriverManager.IOSDriverManager;

import org.testng.annotations.DataProvider;

public class TC2 extends IOSDriverManager{
	
	@Test(dataProvider="dataLogin")
	public void TC2(HashMap<String,String> data ) throws InterruptedException {
		
		driver.get("https://www.visionplus.id");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//img[@alt='others'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/aside/div/div[1]/a")).click();
		Thread.sleep(1000);	
		driver.findElement(By.xpath("(//input[@placeholder='8123456xxx'])[1]")).sendKeys(data.get("phone"));
		Thread.sleep(500);
		driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@placeholder='Your password'])[1]")).sendKeys(data.get("password"));
		Thread.sleep(1000);	
		driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
		Thread.sleep(5000);
		
//		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/img[1]")).click();
		}


	@DataProvider
	public Object[][] dataLogin() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/com.vision_plus.data/datalogin.json");
		return new Object[][] {{data.get(0)}};
		
	
	
}
}
