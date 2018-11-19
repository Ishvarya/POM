/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2d - removeRole - Removing role(s) from the user(s)
 * Pre-Conditions: User(s) should be created. The user(s) should have roles assigned to them.(From test scenario 2, test case 3)
 * Output: Role(s) should be removed from the user(s)
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

public class removeRole {
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
	  public void removeRoles() {
		  //Enter the user's authentication name
		  util.waitForPageLoad(driver);
		  WebElement searchText = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[1]"));
		  searchText.clear();
		  searchText.sendKeys("user");
		  
		  //Select "only active" users
		  util.waitForPageLoad(driver);
		  Select select = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/select")));
		  select.selectByValue("Active");
		  
		  //Click on search Button
		  util.waitForPageLoad(driver);
		  WebElement searchButton1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[1]/div[2]/input[2]"));
		  searchButton1.click();
		  
		  //Select user1
		  util.waitForPageLoad(driver);
		  WebElement userCheck1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[1]/div[1]/img"));
		  userCheck1.click();
		  
		  //Select user2
		  util.waitForPageLoad(driver);
		  WebElement userCheck2 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[2]/div[1]/img"));
		  userCheck2.click();
		  
		  //Click on remove Roles Button
		  util.waitForPageLoad(driver);
		  WebElement removeRolesButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[3]/input[5]"));
		  removeRolesButton.click();
		  
		  //Select role1
		  util.waitForPageLoad(driver);
		  WebElement role1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[2]/td/div/div[1]/img"));
		  role1.click();
		  
		  //Select role2
		  util.waitForPageLoad(driver);
		  WebElement role2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[2]/td/div/div[2]/img"));
		  role2.click();
		  
		  //Select role3
		  util.waitForPageLoad(driver);
		  WebElement role3 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[2]/td/div/div[3]/img"));
		  role3.click();
		  
		  //Click on remove Button
		  util.waitForPageLoad(driver);
		  WebElement removeButton = driver.findElement(By.xpath("/html/body/div[5]/div[2]/table/tbody/tr[3]/td/input[1]"));
		  removeButton.click();
		  
		  // Print success message
		  Reporter.log("Role1, Role2 and Role3 have been removed from the first two users");  
	  }
	  
	  @Test(priority = 3, dependsOnMethods = {"removeRoles"}, groups= {"Smoke Testing"})
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
		  Reporter.log("Start of removeRole class");
	  }

	  @AfterClass
	  public void afterClass() {
		  Reporter.log("End of removeRole class");
	  }

}
