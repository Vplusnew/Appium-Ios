package appiumtest;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {

    private static ChromeDriver driver;

    @Test
    public void main() throws JSchException, IOException, InterruptedException {
        SSHManager2 manager2 = new SSHManager2();
        
        System.out.println(manager2.getOtp());
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("(//textarea[@id='APjFqb'])[1]")).sendKeys(manager2.getOtp());

        
        Thread.sleep(10000);
        // Add assertions or other test logic as needed

        // Close the browser or perform any cleanup
        driver.quit();
    }
}
