package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DashboardPage;
import pages.LoginPage;
import pages.MyInfoPage;
import testbase.Base;
import testbase.TestBase;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class MyInfoTest {
	private static final Logger logger = (Logger) LogManager.getLogger(MyInfoTest.class);

	private TestBase tb;
	private WebDriver dr;
	private LoginPage lp;
	private DashboardPage dp;
	private MyInfoPage mip;

	@Test(priority=1,description="Count Menu items",groups="Sanity")
	public void Verify_The_Menu_Item_Count_On_MyInfo_TC005() throws IOException {
		int act = mip.getItemCountForMenu();
		tb.attachScreenshot();
		int exp = 12;
		logger.info("Actual:"+act+"--- Expected:"+exp);
		Reporter.log("Actual:"+act+"--- Expected:"+exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority=2,description="Menu Item text Verification",dependsOnMethods="Verify_The_Menu_Item_Count_On_MyInfo_TC005")
	public void Verify_The_Menu_Item_Text_On_MyInfo_TC006() throws IOException {
		List<String> act = mip.getItemTextForMenu();
		List<String> exp = new ArrayList<String>();
		exp.add("Personal Details");
		exp.add("Contact Detail");
		exp.add("Emergency Contacts");
		exp.add("Dependents");
		exp.add("Immigration");
		exp.add("Job");
		exp.add("Salary");
		exp.add("Tax Exemption");
		exp.add("Report-to");
		exp.add("Qualifications");
		exp.add("Membership");
		SoftAssert sa = new SoftAssert();
		int c=0;
		for(String s:act)
		{
			logger.info("Actual:"+s+"---Expected:"+exp.get(c));
			Reporter.log("Actual:"+s+"---Expected:"+exp.get(c));
			sa.assertEquals(s, exp.get(c));
			c++;
		}
		sa.assertAll();
		tb.attachScreenshot();
			//Assert.assertEquals(act, exp, "My Info Menu Check");

	}

	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws IOException {
		tb = new TestBase();
		dr = tb.getInstance();
		lp = new LoginPage(dr);
		dp = new DashboardPage(dr);
		mip = new MyInfoPage(dr);
		logger.info("Login to application");
		Reporter.log("Login to application");
		lp.loginToApplication("Admin", "admin123");
		Assert.assertEquals(dp.isUsernameDisplayed(), true);
		logger.info("Dashboard Displayed");
		Reporter.log("Dashboard Displayed");
		dp.clickOnMyInfoTab();
		Assert.assertEquals(mip.isMyInfoPageDisplayed(), true);
		logger.info("My Info page displayed");
		Reporter.log("My Info page displayed");
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		dp.logout();
		dr.quit();
	}

}
