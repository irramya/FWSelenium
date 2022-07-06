package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import base.EnvConfig;
import base.Refs;
import exceptions.InvalidKeyException;
import exceptions.UnidentifiedValueException;
import fwconstants.FilePath;

public class WebOps {

	private WebDriver driver;
	private WebDriverWait lwait;
	private WebDriverWait swait;
	private PropOps setupProps;

	public WebOps(WebDriver driver, PropOps setupProps) {
		this.driver = driver;
		lwait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.LETO));
		swait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.SETO));
		this.setupProps = setupProps;
	}

	public By getBy(String selector) throws UnidentifiedValueException {
		String[] sel = selector.split("==");
		switch (sel[0]) {
		case "id":
			return By.id(sel[1]);
		case "name":
			return By.name(sel[1]);
		case "css":
			return By.cssSelector(sel[1]);
		case "xp":
			return By.xpath(sel[1]);
		case "class":
			return By.className(sel[1]);
		case "tag":
			return By.tagName(sel[1]);
		case "lt":
			return By.linkText(sel[1]);
		case "plt":
			return By.partialLinkText(sel[1]);
		default:
			throw new UnidentifiedValueException();
		}
	}

	public WebElement getElement(String selector) {
		try {
			return driver.findElement(getBy(selector));
		} catch (UnidentifiedValueException e) {
			Reporter.log(e.getMessage(), 0, true);
		} catch (NoSuchElementException e) {
			Reporter.log(e.getMessage(), 0, true);
		}
		return null;
	}

	public List<WebElement> getElements(String selector) {
		try {
			return driver.findElements(getBy(selector));
		} catch (UnidentifiedValueException e) {
			Reporter.log(e.getMessage(), 0, true);
		} catch (NoSuchElementException e) {
			Reporter.log(e.getMessage(), 0, true);
		}
		return null;
	}
	public void navigate(Refs type) throws InvalidKeyException {
		switch (type) {
		case TO:
			driver.navigate().to(setupProps.getProp("url"));
			break;
		case NEXT:
			driver.navigate().forward();
			break;
		case PREV:
			driver.navigate().back();
			break;
		case RELOAD:
			driver.navigate().refresh();
			break;
		};
	}

	public String title() {
		return driver.getTitle();
	}
	
	public String getContent(String selector) {
		return getElement(selector).getText();
	}
	
	public List<String> getContents(String selector) throws UnidentifiedValueException {
		List<String> values= new ArrayList<String>();
		lwait.until(ExpectedConditions.numberOfElementsToBeMoreThan(getBy(selector), 5));
		for (WebElement we : getElements(selector)) {
			values.add(we.getText());			
		}
		return values;
	}
	
	public void screenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(FilePath.screenshotPath+"temp.png");
        FileUtils.copyFile(src, dest);
	}
	
}
