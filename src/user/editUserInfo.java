/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2f - editUserInfo - Editing information of an existing user
 * Pre-Conditions: The user should exists in the collaborator system
 * Output: Existing user's information should be edited
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

public class editUserInfo {
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
	  public void editInformation() {
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
		  Select select = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/select")));
		  select.selectByValue("Active");
		  
		  //Click on search Button
		  util.waitForPageLoad(driver);
		  WebElement searchButton1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[2]"));
		  searchButton1.click();
		  
		  //Click on the first user
		  util.waitForPageLoad(driver);
		  WebElement editOption = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[1]/div[2]"));
		  editOption.click();
		  
		  //Clear birthday
		  util.waitForPageLoad(driver);
		  WebElement clearBday = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[9]/td/div/a"));
		  clearBday.click(); 
		  
		  //Choose the birthday
		  util.waitForPageLoad(driver);
		  WebElement birthday = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[9]/td/div/input"));
		  birthday.click();
		  for(int i=0;i<=25;i++) {
			  WebElement prevYear = driver.findElement(By.xpath("/html/body/div[7]/table/thead/tr[1]/th/div[1]/span[1]"));
			  prevYear.click();
		  }
		  WebElement prevMonth = driver.findElement(By.xpath("/html/body/div[7]/table/thead/tr[1]/th/div[1]/span[2]"));
		  prevMonth.click();
		  WebElement day = driver.findElement(By.xpath("/html/body/div[7]/table/tbody/tr[3]/td[6]"));
		  day.click();
		  
		  //Enter the degree
		  util.waitForPageLoad(driver);
		  WebElement degreeText = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[12]/td/input"));
		  degreeText.clear();
		  degreeText.sendKeys("B.Sc");
		  
		  //Enter the location
		  util.waitForPageLoad(driver);
		  WebElement locationText = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[13]/td/input"));
		  locationText.clear();
		  locationText.sendKeys("Berlin");
		  
		  //Enter the cost center
		  util.waitForPageLoad(driver);
		  WebElement ccText = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[14]/td/input"));
		  ccText.clear();
		  ccText.sendKeys("Germany");
		  
		  //Click on save button
		  util.waitForPageLoad(driver);
		  WebElement saveButton = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[19]/td/input[1]"));
		  saveButton.click();
		  
		  // Print success message
		  Reporter.log("User Information has been edited");
	  }
	  
	  @Test(priority = 3, dependsOnMethods = {"editInformation"}, groups= {"Smoke Testing"})
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
		  Reporter.log("Start of editUserInfo class");
	  }

	  @AfterClass
	  public void afterClass() {
		  Reporter.log("End of editUserInfo class");
	  }

}
