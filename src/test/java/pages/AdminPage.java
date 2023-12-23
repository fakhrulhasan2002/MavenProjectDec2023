package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import testbase.Base;

public class AdminPage extends Base{
	private static final Logger logger = (Logger) LogManager.getLogger(AdminPage.class);

	private WebDriver dr;
	
	public AdminPage(WebDriver d)
	{
		this.dr=d;
		PageFactory.initElements(dr, this);
	}
	
	//locators
	@FindBy(xpath="//*[contains(@class,'breadcrumb-module')][text()='Admin']")
	private WebElement title;
	
	//business logic
	public boolean isAdminPageDisplayed()
	{
		logger.info("Admin Page display check");
		boolean b = false;
		try {
			b = title.isDisplayed();
		}catch(Exception e) 
		{
			logger.info(e);
		}
		return b;
	}
	
	public boolean verifyTheUser(String name, String role) throws InterruptedException
	{
		boolean b = false;
		//Thread.sleep(2000);
		//waitForElementToClickable(dr.findElements(By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div/div")).get(0));
		int row = dr.findElements(By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div/div")).size();
		logger.info("Total Rows:"+row);
		int userCol = 2;
		logger.info("User Col"+userCol);
		int roleCol = 3;
		logger.info("Role Col"+roleCol);
		for(int i = 1;i<=row;i++)
		{
		   logger.info("Row-->"+i);
		   String userNameInCol = dr.findElement(By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div["+i+"]/div/div["+userCol+"]/div")).getText();
		   String roleFound = dr.findElement(By.xpath("//div[@role='table']/div[@class='oxd-table-body']/div["+i+"]/div/div["+roleCol+"]/div")).getText();
		   if(userNameInCol.trim().equalsIgnoreCase(name.trim()) && roleFound.trim().equalsIgnoreCase(role.trim()))
		   {
			   b=true;
			   logger.info("Found User-->"+ userNameInCol+"--->"+name);
			   logger.info("Found Role-->"+roleFound+"--->"+role);
			   Reporter.log("Found User-->"+ userNameInCol+"--->"+name);
			   Reporter.log("Found Role-->"+roleFound+"--->"+role);
			   break;
		   }else
		   {
			   logger.info("Not Found User-->"+ userNameInCol+"--->"+name);
			   logger.info("Not Found Role-->"+roleFound+"--->"+role);
			   Reporter.log("Found User-->"+ userNameInCol+"--->"+name);
			   Reporter.log("Found Role-->"+roleFound+"--->"+role);
		   }
		}
		
			return b;
	}
	
}
