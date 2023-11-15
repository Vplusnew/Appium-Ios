package baseclass;

import appiumtest.sample;
import baseclass.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC1  extends sample{
	
@Test
	public void Test1() throws InterruptedException {
		driver.get("https://www.visionplus.id");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//img[@alt='others'])[1]")).click();
		

	}

}
