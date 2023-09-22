package briqProject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFile {
    public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://the-internet.herokuapp.com/upload");

        // Replace with the actual path to the downloaded file
        String filePath = "C:\\Users\\COMTECH\\eclipse-Sarita\\briq\\target\\file.txt";

        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        WebElement uploadButton = driver.findElement(By.id("file-submit"));

        uploadInput.sendKeys(filePath);
        uploadButton.click();
    }}
