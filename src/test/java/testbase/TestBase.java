package testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase extends Base{
	
	private static final Logger logger = (Logger) LogManager.getLogger(TestBase.class);
			
	//private WebDriver driver;
	private String browser;
	public Properties prop = new Properties();
	public WebDriver getInstance() throws IOException
	{
		logger.info("Read Configuration file");
		String configFile = "./src/test/resources/config/config.properties";
		FileInputStream inStream = new FileInputStream(configFile);
		prop.load(inStream);
		browser = prop.getProperty("browser");
		logger.info("Read browser key for browser type");
		if(browser.equalsIgnoreCase("chrome")) {
				ChromeOptions opt = new ChromeOptions();
				//opt.addArguments("--start-fullscreen");
				opt.addArguments("--start-maximized");
				System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
				driver = new ChromeDriver(opt);
				logger.info("Chrome Browser Selected");
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions opt = new FirefoxOptions();
			opt.addArguments("--start-maximized");
			System.setProperty("webdriver.gecko.driver", prop.getProperty("geckodriver"));
			driver = new FirefoxDriver(opt);
			logger.info("File fox browser selected");
		}else if(browser.equalsIgnoreCase("edge"))
		{
			EdgeOptions opt = new EdgeOptions();
			//opt.addArguments("--start-fullscreen");
			opt.addArguments("--start-maximized");
			System.setProperty("webdriver.edge.driver", prop.getProperty("edgedriver"));
			driver = new EdgeDriver(opt);
		}else if(browser.equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			driver = new RemoteWebDriver(new URL("http://localhost"), cap);
			logger.info("Remote Webdriver selected");
		}
		else
		{
			logger.info("No browser selected or defined in config file");
			Throwable thr = new Throwable();
			thr.initCause(null);
			
		}
		driver.manage().window().maximize();
		//driver.manage().window().fullscreen();
		//Dimension d = new Dimension(280, 653);
		//driver.manage().window().setSize(d);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//applicable to all element
		driver.get(prop.getProperty("uat.url"));
		logger.info("Url opened"+prop.getProperty("uat.url"));
		return driver;
	}
	
	public void closeBrowser()
	{
		driver.quit();
	}
}
