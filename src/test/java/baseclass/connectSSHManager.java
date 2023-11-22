package baseclass;


import appiumtest.SSHManager2;
import appiumtest.sampleDB;
import appiumtest.session;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class connectSSHManager extends sampleDB{
	
	@Test(dataProvider="dataLogin")
	public void TC1(HashMap<String,String> data ) throws InterruptedException {
		
		driver.get("https://www.google.com/");
		
//		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/img[1]")).click();
		}


	@DataProvider
	public Object[][] dataLogin() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/com.vision_plus.data/datalogin.json");
		return new Object[][] {{data.get(0)}};
		
	
	
}
}
