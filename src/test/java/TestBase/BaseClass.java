package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass
  {
	public static WebDriver driver; 
	public Logger logger; 
	public Properties p;
	@BeforeClass
	@Parameters({"browser"})
	public void setUp(String br) throws IOException
	{

		FileReader file=new FileReader(".//src/test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		//loading logger file
		logger=LogManager.getLogger(this.getClass());
	
		if(br.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver(); 
		}
		else if(br.equalsIgnoreCase("Edge")) 
		{
			driver=new EdgeDriver(); 
		}
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

     @AfterClass
     public void tearDown()
    {
    	 driver.quit();
    }
     
     public void captureScreen(String tname) throws IOException 
   	{
    	 
 		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
 		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
 		
 		String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\" + tname + ".png";                          
 		File targetFile=new File(targetFilePath);
 		FileUtils.copyFile(sourceFile, targetFile);
 		
 	}
     
    		 
}
