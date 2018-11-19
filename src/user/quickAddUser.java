/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2e - quickAddUser - Quick addition of user
 * Pre-Conditions: Login to the collaborator
 * Output: New user should be added using the quick add feature
 */
package user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import utility.utilFunctions;

public class quickAddUser {
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
	  public void quickAddFeature() {
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
		  String sAuthName, sFirstName, sLastName, sEmail, sPassword;
		  int randomNumber,i;
		  for(i=0;i<2;i++) {
			  randomNumber = util.generateRandomNumber();
			  sAuthName = "user"+randomNumber;
			  sFirstName = sAuthName;
			  sLastName = sAuthName;
			  sEmail = sAuthName + "@datango.com";
			  sPassword = sAuthName;
			  
			  //Enter the Auth-name
			  util.waitForPageLoad(driver);
			  WebElement authNameText = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/input"));
			  authNameText.sendKeys(sAuthName);
			  
			  //Enter the first-name
			  util.waitForPageLoad(driver);
			  WebElement firstName = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[2]/input"));
			  firstName.sendKeys(sFirstName);
			  
			  //Enter the last-name
			  util.waitForPageLoad(driver);
			  WebElement lastName = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[4]/div[2]/input"));
			  lastName.sendKeys(sLastName);
			  
			  //Enter the email
			  util.waitForPageLoad(driver);
			  WebElement email = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[5]/div[2]/input"));
			  email.sendKeys(sEmail);
				  
			  //Enter the password
			  util.waitForPageLoad(driver);
			  WebElement passWord = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[6]/div[2]/input"));
			  passWord.sendKeys(sPassword);
			  
			  //Click on add button
			  util.waitForPageLoad(driver);
			  WebElement addButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[7]/div[2]/input"));
			  addButton.click();	  
		  }
		  
		  // Print success message
		  Reporter.log("Two users have been added using the quick add feature");
	  }
	  
	  @Test(priority = 3, dependsOnMethods = {"quickAddFeature"}, groups= {"Smoke Testing"})
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
		  Reporter.log("Start of quickAddUser class");
	  }

	  @AfterClass
	  public void afterClass() {
		  Reporter.log("End of quickAddUser class");
	  }

}
