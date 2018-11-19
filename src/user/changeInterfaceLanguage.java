/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2g - changeInterfaceLanguage - Changing the interface language of existing users
 * Pre-Conditions: The users should exists in the collaborator system
 * Output: The interface language of the selected users should be changed
 */
package user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import utility.utilFunctions;

public class changeInterfaceLanguage {
	  public WebDriver driver;
	  public static utilFunctions util = new utilFunctions();
	  
	  @DataProvider(name = "Authentication")
	  public static Object[][] setCredentials(){
		  return util.getCredentials();	  
	  }
	  
	  @Test(priority = 0,groups= {"Smoke Testing"})
	  @Parameters({"browserType"})
	  public void openBrowser(String browserType) {
		  //call utility function
		  driver = util.selectBrowser(browserType);
	      util._openBrowser(driver);
	  }
	  
	  @Test(priority = 1, dependsOnMethods = {"openBrowser"}, groups= {"Smoke Testing"}, dataProvider = "Authentication")
	  public void logIn(String sUsername, String sPassword) {
		  //Call utility function
		  util._logIn(sUsername, sPassword, driver);
	  }
	  
	  @Test(priority = 2, dependsOnMethods = {"logIn"}, groups= {"Smoke Testing"})
	  public void changeLanguage() {
		  //Click on "Administration" menu
		  util.waitForPageLoad(driver);
		  WebElement adminMenu = driver.findElement(By.xpath("/html/body/div[2]/div/a[2]/span"));
		  adminMenu.click();
		  
		  //Click on "User" option
		  util.waitForPageLoad(driver);
		  WebElement userOption = driver.findElement(By.xpath("/html/body/div[2]/div/a[2]/div/a[1]"));
		  userOption.click();
		  
		  //Select the root OU
		  util.waitForPageLoad(driver);
		  WebElement rootOU = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[2]/div/div/div[1]/a"));
		  rootOU.click();
		  
		  //Enter the user's authentication name
		  util.waitForPageLoad(driver);
		  WebElement searchText = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[1]"));
		  searchText.sendKeys("user");
		  
		  //Select "only active" users
		  util.waitForPageLoad(driver);
		  Select select1 = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/select")));
		  select1.selectByValue("Active");
		  
		  //Click on search Button
		  util.waitForPageLoad(driver);
		  WebElement searchButton1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[2]"));
		  searchButton1.click();
		  
		  //Select first two users
		  util.waitForPageLoad(driver);
		  WebElement user1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[1]/div[1]/img"));
		  user1.click();
		  util.waitForPageLoad(driver);
		  WebElement user2 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[2]/div[1]/img"));
		  user2.click();
		  
		  //Select the interface language as German
		  util.waitForPageLoad(driver);
		  Select select = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[3]/select")));
		  select.selectByValue("1031");
		  
		  //Click on change language button
		  util.waitForPageLoad(driver);
		  WebElement changeButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[3]/input[1]"));
		  changeButton.click();
		  
		  // Print success message
		  Reporter.log("User's interface language has been changed");
	  }
	  
	  @Test(priority = 3, dependsOnMethods = {"changeLanguage"}, groups= {"Smoke Testing"})
	  public void logOut() {
		  //Call utility function
		  util.waitForPageLoad(driver);
		  util._logOut(driver);
	  }
	  
	  @Test(priority = 4, dependsOnMethods = {"logOut"}, groups= {"Smoke Testing"})
	  public void closeBrowser() {
		  //Call utility function
		  util._closeBrowser(driver);
	  }
	  
	  @BeforeClass
	  public void beforeClass() {
		  Reporter.log("Start of changeInterfaceLanguage class");
	  }

	  @AfterClass
	  public void afterClass() {
		  Reporter.log("End of changeInterfaceLanguage class");
	  }

}
