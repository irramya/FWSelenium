package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import exceptions.InvalidKeyException;
import exceptions.UnidentifiedValueException;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.PropOps;
import utils.WebOps;

public class Initializer {

	protected WebDriver driver;

	protected PropOps setupProps;

	protected WebOps webOps;


	public void initDriver() throws UnidentifiedValueException, InvalidKeyException {
		setupProps = new PropOps("setup.properties");
		String browser = setupProps.getProp("browser");

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		default:
			throw new UnidentifiedValueException();
		};
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.ITO));
		webOps = new WebOps(driver, setupProps);	
		webOps.navigate(Refs.TO);
	}

	

	@BeforeTest
	public void setup() {
		try {
			initDriver();
		} catch (InvalidKeyException e) {
			Reporter.log(e.getMessage(), 0, true);
			e.printStackTrace();
		}catch (UnidentifiedValueException e) {
			Reporter.log(e.getMessage(), 0, true);
			e.printStackTrace();
		}
		
	}
}
