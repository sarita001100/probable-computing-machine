package briqProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebTableToCSV {
    public static void main(String[] args) {
        // Set the WebDriver path and open the browser
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        // Navigate to the website
       driver.get("http://the-internet.herokuapp.com/challenging_dom");
        
        // Find the table element by its XPath
        WebElement tableElement = driver.findElement(By.xpath("//table"));
        
        // Read the table data and store it in a list of lists
        List<List<String>> tableData = new ArrayList();
        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<String> rowData = new ArrayList<>();
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            tableData.add(rowData);
        }
        
        // Generate a CSV file name with a timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        String csvFileName = "C:\\Users\\COMTECH\\eclipse-Sarita\\briq\\target" + timestamp + ".csv";
        
        // Write the table data to a CSV file
        try (FileWriter csvWriter = new FileWriter(csvFileName)) {
            for (List<String> rowData : tableData) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            System.out.println("CSV file generated successfully: " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Close the browser
        driver.quit();
    }
}

