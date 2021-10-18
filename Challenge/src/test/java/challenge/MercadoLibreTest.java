package challenge;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Test
public class MercadoLibreTest {
	
	WebDriver driver ;
	
	//String url = "https://www.mercadolibre.com.ar/";
	
	MarketPlacePage ml = new MarketPlacePage(driver, "mercadolibre", "https://www.mercadolibre.com.ar/");
	
	By inputSearch = By.className("nav-search-input");
	
	By searchButton = By.className("nav-search-btn");
	
	By results = By.className("ui-search-result__wrapper");
	
 @BeforeClass
  public void beforeClass() {
	  driver = ml.chromeDriverConnect();
	  ml.before(driver, ml);
  }
 
  public void search10ItemMercadoLibre() {
	  //search the product 
	  ml.searchProduct(inputSearch, searchButton);
	  
	  ml.screenShot("mercadolibre-capture-" + ml.getI());
	  ml.setI();
	  //put in the list all result of this product
	  List<WebElement> listResult = ml.findElements(results);
	  
	  System.out.println(listResult.size());
	  
	  if(listResult.size() == 0) {
		  System.out.print("Don´t found elements." + "\n");
	  }else {
		  int i = 1;
		  for(WebElement we:listResult) {
			  //only first 10 element
			  if(i<=10) {

				  WebElement title = we.findElement(By.tagName("h2"));
				  System.out.println(ml.getText(title));
				  
				  WebElement price = we.findElement(By.className("price-tag-fraction"));
				  System.out.println(ml.getText(price));
				  
				  WebElement link = we.findElement(By.tagName("a"));
				  System.out.println(link.getAttribute("href"));
				  
				  System.out.println("\n");
				  
				  try {
					ml.writeCSV("./mercadolibre.xlsx", i, ml.getText(title), ml.getText(price), link.getAttribute("href"));
				  } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
				  i++;
			  }else {
				  break;
			  }
		  }
	  }
  }
 

  @AfterClass
  public void afterClass() {
	  ml.close(driver);
  }

}
