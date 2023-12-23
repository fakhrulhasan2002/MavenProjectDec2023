package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.Base;

public class LoginPage extends Base{
	private static final Logger logger = (Logger) LogManager.getLogger(LoginPage.class);
	private WebDriver dr;
	public LoginPage(WebDriver d)
	{
		this.dr=d;
		PageFactory.initElements(dr, this);
	}
	
	//locators
	//@FindBy or @FindBys or @FindAll
	@FindBy(name="username")
	private WebElement username;//=dr.findElement(By.name("username"));
	
	@FindBy(xpath="//*[@name='password']")
	private WebElement password;
	
	@FindBy(css="[type='submit']")
	private WebElement submit;
	
	
	@FindBy(css="[class$='orangehrm-login-forgot-header']")
	private WebElement forgotPass;
	
	@FindBy(css="p[class$='alert-content-text']")
	private WebElement error;
	
	//business logics
	public void clickOnForgotPassword()
	{
		forgotPass.click();
	}
	
	public void loginToApplication(String user, String pass)
	{
		logger.info("Login to application with \""+user+"\" and passwrod \""+pass+"\"");
		username.sendKeys(user);
		password.sendKeys(pass);
		submit.click();
	}
	
	public boolean isErrorDisplayed()
	{
		logger.info("Check for error while login");
		boolean b=false;
		try {
			b = error.isDisplayed();
		}catch(Exception e)
		{
			logger.info(e);
		}
		logger.info("Value-->"+b);
		return b;
	}
	
	public boolean isLogoutSuccessfull()
	{logger.info("Check for logout success");
	boolean b=false;
	try {
		b = submit.isDisplayed();
	}catch(Exception e)
	{
		logger.info(e);
	}
	logger.info("Value-->"+b);
	return b;
		
	}
	
	public boolean isLoginPageDisplayed()
	{logger.info("Check for Login Page Display");
	boolean b=false;
	try {
		b = submit.isDisplayed();
	}catch(Exception e)
	{
		logger.info(e);
	}
	logger.info("Value-->"+b);
	return b;
		
	}
}
