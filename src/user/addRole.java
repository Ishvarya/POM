/*
 * TEST SCENARIO 2 - Testing the functionalities under Administration -> User
 * Test Case 2c - addRole - Adding role(s) to the user(s)
 * Pre-Conditions: User(s) should be created. The user/users should be active.
 * Output: The user/users are de-activated
 */
package user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import utility.utilFunctions;

public class addRole {
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
	  public void addRoles() {
		  
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
		  
		  //Select user1
		  util.waitForPageLoad(driver);
		  WebElement userCheck1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[1]/div[1]/img"));
		  userCheck1.click();
		  
		  //Select user2
		  util.waitForPageLoad(driver);
		  WebElement userCheck2 = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div/div[2]/div[1]/img"));
		  userCheck2.click();
		  
		  //Click on add Roles Button
		  util.waitForPageLoad(driver);
		  WebElement addRolesButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[3]/input[4]"));
		  addRolesButton.click();
		  
		  //Enter filter text
		  util.waitForPageLoad(driver);
		  WebElement filterText = driver.findElement(By.className("ddf_input"));
		  filterText.sendKeys("Role");
		  
		  //Click on search button
		  util.waitForPageLoad(driver);
		  List<WebElement> list1 = driver.findElements(By.className("ddf_button"));
		  WebElement searchButton = null;
		    for(WebElement el1 : list1) {
		       System.out.println(el1.getText());
		       if(el1.getText().equals("Search")) {
		    	   searchButton = el1;
		       }
		    }
		   searchButton.click();
		  
		  //Select first role
		  util.waitForPageLoad(driver);
		  List<WebElement> list3 = driver.findElements(By.xpath("//div[contains(@id,'G_') and @class='clickable']"));
		  int i=0;
		  Actions action = new Actions(driver);
		  for(WebElement el3 : list3) {
			  System.out.println(el3.getText());
			  if(i==0) {
				  el3.click();
				  action.keyDown(Keys.LEFT_CONTROL);
			  }
			  else {
				  action.click(el3);  
			  }
			  i++;
		  }
		  action.keyUp(Keys.LEFT_CONTROL).build().perform();
		  //WebElement role1 = driver.findElement(By.xpath("//*[@id=\"G_6D578BCAC556455C90B42D954B668BFA\"]"));
		  
		  //Click on select Button
		  util.waitForPageLoad(driver);
		  List<WebElement> list2 = driver.findElements(By.className("ddf_button"));
		  WebElement selectButton = null;
		  for(WebElement el2 : list2) {
		       System.out.println(el2.getText());
		       if(el2.getText().equals("Select")) {
		    	   selectButton = el2;
		       }
		  }
		  selectButton.click();
		  
		  // Print success message
		  Reporter.log("Selected Roles have been added to the first two Users");
	  }
	  
	  @Test(priority = 3, dependsOnMethods = {"addRoles"}, groups= {"Smoke Testing"})
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
		  Reporter.log("Start of addRole class");
	  }

	  @AfterClass
	  public void afterClass() {
		  Reporter.log("End of addRole class");
	  }

}
