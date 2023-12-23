package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import testbase.TestBase;
import testutilities.ExcelData;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTestParameterFomExcel{
	private static final Logger logger = (Logger) LogManager.getLogger(LoginTestParameterFomExcel.class);

	private WebDriver dr;
	private TestBase tb;
	private LoginPage lp;
	private DashboardPage dp;
	private ForgotPasswordPage fgp;
	private ExcelData exlData = new ExcelData();


	@Test(priority=1, description="Login Success with valid credentials", groups="Sanity")
	public void Verify_Login_SuccessFul_TC001() throws IOException {
		tb.attachScreenshot();
		//lp.loginToApplication("Admin", "admin123");
		lp.loginToApplication(exlData.readTheDataCol("UserData",2, 0), exlData.readTheDataCol("UserData",2, 1));
		boolean act = dp.isUsernameDisplayed();
		tb.attachScreenshot();
		logger.info("Actual:" + act + "-->Expected:" + true);
		Assert.assertEquals(act, true);
		dp.logout();
		Assert.assertEquals(lp.isLogoutSuccessfull(), true);
		tb.attachScreenshot();
		
	}
	
	@Test(priority=2,description="verify unsuccessful credentials")
	public void Verify_Login_Unsuccessful_Wrong_Credentials_TC002() throws IOException {
		tb.attachScreenshot();
		lp.loginToApplication(exlData.readTheDataCol(1, 0), exlData.readTheDataCol(1, 1));
		boolean act = lp.isErrorDisplayed();
		tb.attachScreenshot();
		logger.info("Actual:" + act + "-->Expected:" + true);
		Assert.assertEquals(act, true);

	}


	@Test(priority=3,description="Verify Cancel feature in Reset Password",groups="Sanity")
	public void Verify_Forgot_Password_Cancel_TC004() throws IOException
	{
		lp.clickOnForgotPassword();
		tb.attachScreenshot();
		boolean d = fgp.isForgotPasswordPageDisplayed();
		Assert.assertEquals(d, true);
		tb.attachScreenshot();
		fgp.enterUsername(exlData.readTheDataCol(1, 0));
		fgp.clickOnCancelButton();
		boolean act = lp.isLoginPageDisplayed();
		tb.attachScreenshot();
		Assert.assertEquals(act, true);
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() throws IOException {
		logger.info("Before Method");
		tb = new TestBase();
		dr = tb.getInstance();
		lp = new LoginPage(dr);
		dp = new DashboardPage(dr);
		fgp = new ForgotPasswordPage(dr);

	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod() {
		logger.info("After Method");
		dr.quit();
	}

}
