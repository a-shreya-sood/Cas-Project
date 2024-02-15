package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
 
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
 
public class NewsVerification extends BasePage {
 
//	public WebDriver driver;
	public NewsVerification(WebDriver driver) {
		super(driver);
	}

	
	//heading of each news
	@FindBy(xpath = "//*[@id='news_text_title']")
	List<WebElement> newspgHeaders;
	
	//news header after clicking
	@FindBy(xpath = "//*[@id='title_text']")
	WebElement Title;
	
	//news content
	@FindBy(xpath="//div[@id='fa45f946-463e-428f-a84b-0bbbde09c3ba']/div")
	WebElement article;
	
	
//		
		
		public List second_page_headers_list()
		{
			return newspgHeaders;
		}
		
		public WebElement news_header_after_clicking()
		{
			return Title;
		}
		
		public WebElement news_content()
		{
			return article;
		}
		
		
}