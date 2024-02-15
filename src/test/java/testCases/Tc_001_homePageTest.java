package testCases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestBase.BaseClass;
import Utilities.ExcelUtility;
import pageObjects.HomePage;
import pageObjects.NewsVerification;


public class Tc_001_homePageTest extends BaseClass 
{
 
	HomePage hp;
	NewsVerification nv;
	
	@Test(priority=1)
	public void verify_home_page() throws IOException, InterruptedException 
	{
		hp = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		Thread.sleep(2000);
		hp.clickMyAccount();
		Thread.sleep(2000);
		logger.info("-----Capturing the profile screen Screenshot------");
		captureScreen("img_userProfile");
		
		logger.info("-----Capturing the profile screen------");
		Thread.sleep(2000);
		String confirmMsg=hp.getConfirmationMsg1();
		System.out.println(confirmMsg);
		Assert.assertEquals(confirmMsg,"Kashyap, Shriya (Cognizant)","User not matched");	
		
	}
	
	@Test(priority=2)
	public void verify_around_cognizant() throws IOException, InterruptedException
	{

		
		hp = new HomePage(driver);
		hp.scrollDown();
		Thread.sleep(2000);
		logger.info("-----Capturing the AroundCognizant screen Screenshot------");
		captureScreen("Around_Cognizant");
		logger.info("------Capturing Around Cognizant------");
		Thread.sleep(1000);
		String expected ="Around Cognizant";
		String actual = hp.aroundCognizantText();
		System.out.println(actual);
		logger.info("-----Performing validation on Around Cognizant-----");
		Assert.assertEquals(expected,actual,"Around Cognizant Logo Text not matched");
		
		int news_count=hp.getNewsCount();
		logger.info("------Capturing the count of news-------");
		System.out.println("The size of the list is :"+news_count);
		
	}
	@Test(priority=3)
	public void writeData() 
	{
		hp = new HomePage(driver);
		logger.info("-----Capturing the header of the news-------");
		List<WebElement> list= hp.get_news_header();
		int row=1;
		String heading="";
		for(WebElement e: list)
		{
			heading=e.getText();
			
			try 
			{
				System.out.println(heading);
				ExcelUtility.write("Sheet1",0,0,"News Headers");
				ExcelUtility.write("Sheet1",row,0,heading);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			row++;
		}
		
	}
	
	@Test(priority=4)
	public void verify_getTool_Tip() throws IOException
	{
		hp = new HomePage(driver);
		logger.info("-------Capturing the Tool-tips of the news------- ");
		List<WebElement> List_ToolTip=hp.get_ToolTip();
		int row=1;
		//ExcelUtility.delete_Sheets();
		for(WebElement tips:List_ToolTip)
		{
			String tips_tool=tips.getText();
			//System.out.println(tips_tool);
			try 
			{
				System.out.println("The toolTips are:"+tips_tool);
				ExcelUtility.write("Sheet1",0,1,"Tool Tips");
				ExcelUtility.write("Sheet1",row,1,tips_tool);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			row++;
		
		
		}
		
	}
	
	
	@Test(priority=5)
	public void verify_click_seeAll()
	{
		hp = new HomePage(driver);
		logger.info("------Capturing till the scroll-----");
		hp.scrollTillEnd();
		hp.click_seeAll();
	}
	


	
	@Test(priority = 6)
	public void verify_second_page_header() throws InterruptedException, IOException
	{
		nv = new NewsVerification(driver);
		hp = new HomePage(driver);
		logger.info("------Capturing the Header of second page------");
		List<WebElement> news_headers=nv.second_page_headers_list();
		
		Thread.sleep(7000);
		logger.info("-----Capturing the 2nd page Screenshot------");
		captureScreen("2nd Page Content");
		int row=1;
		
		logger.info("------Printing the Header of second page in Excel------");
		for(WebElement e:news_headers)
		{
			String header=e.getText();
			try 
			{
				System.out.println(header);
				ExcelUtility.write("Sheet2",0,0,"News Headers");
				ExcelUtility.write("Sheet2",row,0,header);
			} catch (IOException e1) 
			{	
				e1.printStackTrace();
			}
			row++;
		 }
		
	}

    @Test(priority=7)
    public void verify_header()
    {
    	hp = new HomePage(driver);
    	boolean result=hp.check_news();
    	Assert.assertEquals(result, true,"News are not matching");
    }
	
	
	@Test(priority=8)
	public void verify_news() throws IOException 
	{
		nv = new NewsVerification(driver);
		logger.info("-----Capturing the content of each news-----");
		List<WebElement> newspgHeaders =nv.second_page_headers_list();
		WebElement Title  = nv.news_header_after_clicking();
		WebElement article  = nv.news_content();
		
		logger.info("-----STORING NEWS HEADER AND CONTENT INTO EXCEL-----");
		int row=1;
		ExcelUtility.write("Sheet3",0,0,"News Header and Content");
		for(int i=1;i<6;i++) 
		{
			int z=i+1;
		    String str = Integer.toString(z);
		    String news_number= "NEWS "+ str;
		    ExcelUtility.write("Sheet3",row,0,news_number);
		    

			logger.info("-----NEWS HEADERS GETTING CLICKED-----");
			WebElement nw= newspgHeaders.get(i);
			String abc = nw.getText();
			nw.click();
			ExcelUtility.write("Sheet3",(row+1),0,abc);
	 		

	 		logger.info("-----COMPARING THE NEWS HEADERS-----");
	 		String yuj= Title.getText();
	 		Assert.assertEquals(abc, yuj,"NEWS HEADERS NOT MATCHED");
	 		
	 		String content=article.getText();
	 		ExcelUtility.write("Sheet3",(row+2),0,content);
	 		

	 		logger.info("-----NAVIGATING BACK TO COGNIZANT NEWS PAGE-----");
	 		driver.navigate().back();
	 		
	 		row=row+3;
			}
		}
}