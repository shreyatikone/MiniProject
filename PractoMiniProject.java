package sel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PractoMiniProject {

	public static void main(String args[]) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\QA testing\\seleniumC\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.practo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Actions act = new Actions(driver);

		driver.findElement(By.xpath("//input[@placeholder='Search location']")).clear();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@placeholder='Search location']")).sendKeys("Pune");
		Thread.sleep(3000);

		act.sendKeys(Keys.ENTER).build().perform();

		driver.findElement(By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']")).sendKeys("Hospital");
				
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='c-omni u-clearfix']//div[1]//div[1]//span[1]//div[1]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@data-qa-id='Accredited_checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@data-qa-id='Accredited_label']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[contains(text(),'All Filters')]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@data-qa-id='24x7_Pharmacy_checkbox']")).click();
		Thread.sleep(2000);

		List<WebElement> srchrslt = driver.findElements(By.xpath("//div[@class='c-card']"));
		int size = srchrslt.size();
		int i = 0;
		String lines[];
		for (i = 0; i < size -1; i++) {
			String details = srchrslt.get(i).getText();
			lines = details.split("[\\r\\n]+");
			if (isNumeric(lines[3])) {
				if (Double.parseDouble(lines[3]) >= 3.5)
					System.out.println("HOSPITAL HAS RATING > 3.5-" + lines[0]);
			}
			System.out.println("-----------------------");
		}

	}
	
	public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
}