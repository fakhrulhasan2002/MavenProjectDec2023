package testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Base {

	public WebDriver driver;
	public void ScrollToElement(WebElement ele) {
		new Actions(driver).scrollToElement(ele).perform();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)", "");
	}

	public void ClickOnElementActions(WebElement ele) {
		new Actions(driver).click(ele).perform();
	}

	public void ClickOnElementJS(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ele);
	}

	public void captureScreenshot() throws IOException {
		File loc = new File("ScreenshotFolder");
		String path = null;
		if (loc.exists()) {
			path = loc.getAbsolutePath();
		} else {
			loc.mkdirs();
			path = loc.getAbsolutePath();
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(from, new File(path + "/" + getDate() + "_image.jpg"));// jpg & png

	}
	
	public void attachScreenshot() throws IOException
	{
		File loc = new File("ScreenshotFolder");
		String path = null;
		if (loc.exists()) {
			path = loc.getAbsolutePath();
		} else {
			loc.mkdirs();
			path = loc.getAbsolutePath();
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(from, new File(path + "/" + getDate() + "_image.jpg"));// jpg & png

		TakesScreenshot ts1 = (TakesScreenshot) driver;
		String src = ts1.getScreenshotAs(OutputType.BASE64);
		String image="<img src=\"data:image/png;base64,"+src+"\" height=\"600\" width=\"800\" />";
		Reporter.log(image);
	}

	public void waitForElementToClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForElementToVisible(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToVisiblePooling(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.pollingEvery(Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(ele));
	}

	public String getDate() {

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MMM_dd_HH_mm_ss_SSS");
		String dd = sdf.format(d);
		return dd;
	}
}
