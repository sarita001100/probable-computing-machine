package briqProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadAttachments {
    public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://the-internet.herokuapp.com/download");

        WebElement downloadLink = driver.findElement(By.linkText("file.txt"));
        
        Thread.sleep(8000);
        // Replace with the actual link text of the file you want to download
        downloadLink.click();

        // You can add code here to verify that the file is downloaded successfully to the desired location.
        
        driver.quit();
    }
}


