/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2h - searchUser - Search for existing user(s)
 * Pre-Conditions: The user(s) should exists in the collaborator system
 * Output: The collaborator user should be able to search for an existing user
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

public class searchUser {
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
	  public void showAllUser() {
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
		  
		  //Click on the check box "Show sub-elements from the tree"
		  util.waitForPageLoad(driver);
		  WebElement checkBox = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[1]/input"));
		  checkBox.click();
		  
		  // Print success message
		  Reporter.log("All users of the system are displayed on the screen");
	  }
	  @Test(priority = 3, dependsOnMethods = {"showAllUser"}, groups= {"Smoke Testing"})
	  public void searchActiveUsers() {
		  
		  //Enter the user's authentication name
		  util.waitForPageLoad(driver);
		  WebElement searchText = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[1]"));
		  searchText.sendKeys("user");
		  
		  //Select "only active" users
		  util.waitForPageLoad(driver);
		  Select select = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/select")));
		  select.selectByValue("Active");
		  
		  //Click on search Button
		  util.waitForPageLoad(driver);
		  WebElement searchButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[2]"));
		  searchButton.click();
		  
		  // Print success message
		  Reporter.log("All active users with name user% are displayed on the screen");
	  }
	  
	  @Test(priority = 4, dependsOnMethods = {"searchActiveUsers"}, groups= {"Smoke Testing"})
	  public void logOut() {
		  //Call utility function
		  util.waitForPageLoad(driver);
		  util._logOut(driver);
	  }
	  
	  @Test(priority = 5, dependsOnMethods = {"logOut"}, groups= {"Smoke Testing"})
	  public void closeBrowser() {
		  //Call utility function
		  util._closeBrowser(driver);
	  }
	  
	  @BeforeClass
	  public void beforeClass() {
		  Reporter.log("Start of searchUser class");
	  }

	  @AfterClass
	  public void afterClass() {
		  Reporter.log("End of searchUser class"); 
	  }

}
