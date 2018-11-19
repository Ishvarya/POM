/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2a - activateUser - Activating existing inactive user/users
 * Pre-Conditions: User/Users should be created. The user/users should be inactive.
 * Output: The user/users are activated
 */
package user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import utility.utilFunctions;

public class activateGuestUser {
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
  public void activateGuestUsers() {
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
	  searchText.sendKeys("guest");
	  
	  //Select "only inactive" users
//	  util.waitForPageLoad(driver);
//	  Select select = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/select")));
//	  select.selectByValue("Inactive");
	  
	  //Click on search Button
	  util.waitForPageLoad(driver);
	  WebElement searchButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[2]"));
	  searchButton.click();
	  
	  //Select user1
	  util.waitForPageLoad(driver);
	  WebElement userCheck1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[1]/div[1]/img"));
	  userCheck1.click();
	  
	  //Click on activate Button
	  util.waitForPageLoad(driver);
	  WebElement activateButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[3]/input[2]"));
	  activateButton.click();
	  
	  // Print success message
	  Reporter.log("The guest account has been activated");
  }
  
  @Test(priority = 3, dependsOnMethods = {"activateGuestUsers"}, groups= {"Smoke Testing"})
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
	  Reporter.log("Start of activateGuestUser class");
  }

  @AfterClass
  public void afterClass() {
	  Reporter.log("End of activateGuestUser class");
  }

  @BeforeTest
  public void beforeSuite() {
	  Reporter.log("--------Start of Test Scenario 2-----------");
  }

  @AfterTest
  public void afterSuite() {
	  Reporter.log("---------End of Test Scenario 2------------");
  }

}
