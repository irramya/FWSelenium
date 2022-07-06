package exceptions;

import fwconstants.FWStrings;

public class InvalidKeyException extends Exception {

	public InvalidKeyException(String message) {
		super(FWStrings.ikMessage+message);
	}
	
	

}
