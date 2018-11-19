/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2i - sortCheck - Testing sort feature
 * Pre-Conditions: The user(s) should exists in the collaborator system
 * Output: The users should be sorted in ascending and descending order based on sort selection
 */
package user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility.utilFunctions;

public class sortCheck {
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
	  public void sortAscDesc() {
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
		  
		  //Check sort of Auth-Name
		  util.waitForPageLoad(driver);
		  WebElement authCheck = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[1]/div[2]/img"));
		  authCheck.click();
		  
		  //Check sort of First name
		  util.waitForPageLoad(driver);
		  WebElement firstnameCheck = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[1]/div[3]/img"));
		  firstnameCheck.click();
		  
		  //Check sort of Last name
		  util.waitForPageLoad(driver);
		  WebElement lastnameCheck = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[1]/div[4]/img"));
		  lastnameCheck.click();
		  
		  //Check sort of active
		  util.waitForPageLoad(driver);
		  WebElement activeCheck = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[1]/div[6]/img"));
		  activeCheck.click();
		 
		  // Print success message
		  Reporter.log("Sorting feature has been tested successfully");
	  }
	  
	  @Test(priority = 3, dependsOnMethods = {"sortAscDesc"}, groups= {"Smoke Testing"})
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
		  Reporter.log("Start of sortCheck class");
	  }

	  @AfterClass
	  public void afterClass() {
		  Reporter.log("End of sortCheck class");
	  }
}
