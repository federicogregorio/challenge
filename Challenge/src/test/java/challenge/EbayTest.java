package challenge;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Test
public class EbayTest {
	
	WebDriver driver ;
	
	//String url = "https://ar.ebay.com/";
	
	MarketPlacePage ml = new MarketPlacePage(driver, "ebay", "https://ar.ebay.com/");
	
	By inputSearch = By.id("gh-ac");
	
	By searchButton = By.id("gh-btn");
	
	
 @BeforeClass
  public void beforeClass() {
	  driver = ml.chromeDriverConnect();
	  ml.before(driver, ml);
  }
 
  public void search10ItemEbay() {
	  //search the product 
	  ml.searchProduct(inputSearch, searchButton);
	  
	  ml.screenShot("ebay-capture-" + ml.getI());
	  ml.setI();
	  //put in the list all result of this product
	  
	  for (int i=1;i<=10;i++)
	  {
		  WebElement we = ml.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li["+i+"]/div/div[2]"));
	      
	      WebElement title = we.findElement(By.tagName("h3"));
		  System.out.println(ml.getText(title));
		  
		  WebElement price = we.findElement(By.className("s-item__price"));
		  System.out.println(ml.getText(price));
		  
		  WebElement link = we.findElement(By.tagName("a"));
		  System.out.println(link.getAttribute("href"));
		  
		  try {
			ml.writeCSV("./ebay.xlsx", i, ml.getText(title), ml.getText(price), link.getAttribute("href"));
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	  }
  }
 

  @AfterClass
  public void afterClass() {
	  ml.close(driver);
  }

}
