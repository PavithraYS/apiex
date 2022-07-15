package Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	public static String empId()
	{
		String generatedString=RandomStringUtils.randomNumeric(3);
		return(generatedString);
	}
	public static String empTitle()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return(generatedString);
	}
	public static String dueDate()
	{
		String generatedString=RandomStringUtils.randomNumeric(9);
		return(generatedString);
	}
	public static String completed()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(4);
		return(generatedString);
	}
}
