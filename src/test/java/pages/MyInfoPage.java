package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.Base;


public class MyInfoPage extends Base{

	private static final Logger logger = (Logger) LogManager.getLogger(MyInfoPage.class);

	private WebDriver dr;
	public MyInfoPage(WebDriver d)
	{
		this.dr=d;
		PageFactory.initElements(dr, this);
	}
	
	//locators
	@FindBy(xpath="//*[contains(@class,'main-title')][text()='Personal Details']")
	private WebElement personalDetail;
	
	@FindBy(css="[role=tablist] a")
	private List<WebElement> menu;
	
	//business Logic
	public boolean isMyInfoPageDisplayed()
	{
		logger.info("Check My Info Page Displayed");
		boolean a= false;
		try {
			a = personalDetail.isDisplayed();
		}catch(Exception e)
		{
			logger.info(e);
		}
		return a;
	}
	
	public int getItemCountForMenu()
	{
		logger.info("total items in menu-->"+menu.size());
		return menu.size();
	}
	
	public List<String> getItemTextForMenu()
	{
		List<String> tmp = new ArrayList<String>();
		for(WebElement e:menu)
		{
			tmp.add(e.getText().trim());
		}
		logger.info(tmp);
		return tmp;
	}
	
}
