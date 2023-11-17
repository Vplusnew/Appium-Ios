package baseclass;

import appiumtest.sample;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TC1  extends sample{
	@Test(dataProvider = "UserLogin")

	 void Test1(String data) throws InterruptedException {
		
		String users[]=data.split(",");
		
		driver.get("https://www.visionplus.id");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//img[@alt='others'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/aside/div/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@placeholder='8123456xxx'])[1]")).sendKeys(users[0]);
		Thread.sleep(500);
		driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("(//input[@placeholder='Your password'])[1]")).sendKeys(users[1]);
		driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
		Thread.sleep(1000);
//		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/img[1]")).click();
//		}
//		
//		catch (Exception e) {
//			e.printStackTrace();// TODO: handle exception
//		}
		
	}

	
}
