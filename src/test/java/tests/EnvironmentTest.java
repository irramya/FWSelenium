package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import base.Initializer;
import base.Refs;
import elements.GoogleHomePage;
import fwconstants.FilePath;
import utils.XLOps;

public class EnvironmentTest extends Initializer {

//	@Test
	public void VerifyEnvironmentVariables() {
		Reporter.log("Project Path: " + FilePath.projectPath, 0, true);
	}

	@Test
	public void VerifyInitialLaunch() {
		try {
			Reporter.log(webOps.title(), 0, true);
			webOps.screenshot();
//			webOps.getElement(GoogleHomePage.searchBox).sendKeys("Hello");
//			for(String s: webOps.getContents(GoogleHomePage.suggestions)) {
//				Reporter.log(s, 0, true);
//			}
		} catch (Exception e) {
			Reporter.log(e.getMessage(), 0, true);
		}
	}
	
	
//	@Test
	public void VerifyXLComms() {
		try {
			XLOps.loadXL("Login.xlsx", "Sheet1");
			Reporter.log(XLOps.readXL(0, 0), 0, true);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
