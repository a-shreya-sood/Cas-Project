package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) 
	{
		super(driver);
	
	}
	
	
     //Finding xPath of user info
	 @FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
     WebElement profileButton;

	
	//capturing the name of user
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement  profileName;
	

	//getting logo of around cognizant
	@FindBy(xpath="//*[@id=\"5d7d4eec-cbe0-4c55-ae2e-f38d926d82a0\"]/div/div/div/p/span/strong")
	WebElement aroundCognizant;
	

	//count the number of news
//	@FindBy(xpath="//div[@class='z_a_91bed31b']/div/div[2]/a")
	@FindBy(xpath="//div[@class='fj_j_91bed31b']//div/div[2]/a")
	List<WebElement> list_news;
	
	
	 //getting the tooltip
	@FindBy(xpath="//div[@class=\"ai_g_658c9e0a\"]/span")
	List<WebElement> ToolTip;
	
	//getting seeAll element
	@FindBy(xpath="//*[@id=\"c24ff0ed-b166-42e5-b7d5-57c9a9e573cb\"]/div/div/div/p/a/span/strong")
	WebElement seeAll;

	
	//performing click action for the account
	public void clickMyAccount() throws InterruptedException
	{
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(profileButton).click().perform();		 
	}
	
	//validation for the user name 
	public String getConfirmationMsg1()
	{
		try
		{
			return(profileName.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
		
	}
	
	
	//validating scroll
	public void scrollDown()
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView();",aroundCognizant);
	}
	

	//returning text of aroundCognizant
	public String aroundCognizantText() 
	{
		try
		{
		return(aroundCognizant.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
		
	}
	
	//returning news count of First page
	public int getNewsCount()
	{
		return list_news.size();
	}
	
	
	//returning headers of First page
	public List get_news_header()
	{
		return list_news;
	}
	
   
	//returning toolTips
	public List get_ToolTip()
	{
		return ToolTip;
	}
	
	//scrolling till seeAll
	public void scrollTillEnd()
	{
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView();",seeAll);
	}

	//click on see All
	public void click_seeAll()
	{
		seeAll.click();
	}
	
	
	//verification of headers of both page
	public boolean check_news()
	{
		NewsVerification nv =new NewsVerification(driver);
		if(list_news.containsAll(nv.newspgHeaders))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}

