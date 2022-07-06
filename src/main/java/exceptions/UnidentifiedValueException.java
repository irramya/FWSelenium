package exceptions;

import fwconstants.FWStrings;

public class UnidentifiedValueException extends Exception {

	public UnidentifiedValueException() {
		super(FWStrings.uvMessage);
	}
	
	

}
