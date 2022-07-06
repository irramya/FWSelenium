package fwconstants;

public interface FilePath {

	/**
	 * Generic path prefix
	 */
	String projectPath = System.getProperty("user.dir") + "/";
	String excelPath = projectPath + "src/test/resources/excel/";
	String propPath = projectPath + "src/test/resources/props/";
	String reportPath = projectPath + "src/test/resources/reports/";
	String screenshotPath = projectPath + "src/test/resources/screenshots/";

	/**
	 * Files
	 */

}
