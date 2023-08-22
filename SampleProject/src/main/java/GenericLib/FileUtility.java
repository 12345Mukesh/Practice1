package GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtility 
{

	public String readdatafrompropfile(String key) throws IOException
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\Mukesh\\SampleProject\\src\\test\\resources\\FileUtility.txt");
		Properties prop= new Properties();
		prop.load(fis);
		return prop.getProperty(key,"Incorrect key");
		
	}
	
}
