package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import testbase.Base;

public class DashboardPage extends Base{
	private static final Logger logger = (Logger) LogManager.getLogger(DashboardPage.class);

	private WebDriver dr;
	public DashboardPage(WebDriver d)
	{
		this.dr=d;
		PageFactory.initElements(dr, this);
	}

	//locators
	@FindBy(css="[class='oxd-userdropdown-name']")
	private WebElement user;
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	@FindBy(xpath="//span[text()='My Info']")
	private WebElement myInfo;
	
	@FindBy(xpath="//span[text()='Admin']")
	private WebElement admin;
	
	//business logic
	public boolean isUsernameDisplayed()
	{
		logger.info("Verify the user displayed after login");
		Reporter.log("Verify the user displayed after login");
		boolean b=false;
		try {
			b = user.isDisplayed();
		}catch(Exception e){
			logger.info(e);
		}
		logger.info("Value-->"+b);
		Reporter.log("Value-->"+b);
		return b;
	}
	
	public void logout()
	{
		user.click();
		logout.click();
	}
	
	public void clickOnMyInfoTab()
	{
		myInfo.click();
	}
	public void clickOnAdmin()
	{
		admin.click();
	}
}
