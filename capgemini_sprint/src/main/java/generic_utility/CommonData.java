package generic_utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonData{
	
	public String getCommonData(String value) throws IOException
	{
	FileInputStream fis=new FileInputStream("./src/main/resources/Swag_labs_data/common_data.properties");
	Properties p=new Properties();
	p.load(fis);
	return p.getProperty(value);
	}

}
