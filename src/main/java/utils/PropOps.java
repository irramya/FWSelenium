package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;

import exceptions.InvalidKeyException;
import fwconstants.FilePath;

public class PropOps {

	Properties prop;

	public PropOps(String fileName) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(FilePath.propPath+fileName)));
		} catch (FileNotFoundException e) {
			Reporter.log(e.getMessage(), 0, true);
		} catch (IOException e) {
			Reporter.log(e.getMessage(), 0, true);
		}
	}

	public String getProp(String key) throws InvalidKeyException {
		String value = prop.getProperty(key);
		if (value == null)
			throw new InvalidKeyException(key);
		return value;
	}
	
	

}
