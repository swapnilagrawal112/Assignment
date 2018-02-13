package Assignment;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VerifyInstaWork
{
	WebDriver driver;
  @Test
public void validateInstaWork()
  {
	  //Initializing WebDriver wait to use implicit or Explicit waits
	  driver.get("https://www.google.com");
	  WebDriverWait wait = new WebDriverWait(driver,30);
	  
	  WebElement googleSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search']")));
	  googleSearch.clear();
	  googleSearch.sendKeys("Instawork");
	  
	  WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Google Search' and @type='submit']")));
	  searchButton.click();
	  
	  List<WebElement> searchResults = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/cite[@class='_Rm']")));
	  System.out.println(searchResults.size());
	  String expectedResult = "www.instawork.com";
	  for(int i = 0; i<=searchResults.size()-1; i++)
	  {
		  
			  if(searchResults.get(i).getText().contains(expectedResult))
			  {
				  System.out.println(searchResults.get(i).getText());
				  System.out.println("Instawork link is displayed on " +(i+1)+ " Position");
			  }
	  }
  }

  @BeforeMethod
  @Parameters("browser")
  public void browserSetting(String browser) throws Exception
  {
	  		//Check if parameter passed from TestNG is 'firefox'
			if(browser.equalsIgnoreCase("internetexplorer"))
			{
				//set path for Firefox driver.
				System.setProperty("webdriver.ie.driver", "C:\\Users\\pendu\\Desktop\\IEDriverServer_Win32_2.53.0\\IEDriverServer.exe");
				/*DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.*/
				driver = new InternetExplorerDriver();
				
				driver.manage().window().maximize();
			}
			//Check if parameter passed as 'chrome'
			else if(browser.equalsIgnoreCase("chrome"))
			{
				//set path to chromedriver.exe
				System.setProperty("webdriver.chrome.driver","C:\\Users\\pendu\\Desktop\\chromedriver_win32\\chromedriver.exe");
				//create chrome instance
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}

  }

@AfterMethod
  public void afterMethod()
  {
	  driver.close();
  }
}
