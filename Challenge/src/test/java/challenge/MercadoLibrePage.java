package challenge;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MercadoLibrePage extends MarketplaceBase {
	
	String url = "https://www.mercadolibre.com.ar/";
	
	By inputSearch = By.className("nav-search-input");
	
	By searchButton = By.className("nav-search-btn");
	
	int i = 0;
	
	
	public MercadoLibrePage(WebDriver driver) {
		super(driver);
		//csvWriter = createCSV("./mercadoLibre.csv");
		// TODO Auto-generated constructor stub
	}
	
	public void before(WebDriver driver, MercadoLibrePage ml) {
		ml.start(driver);
		try {
			createCSV("./mercadolibre.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    	
	public void close(WebDriver driver) {
		driver.quit();
	}
	
	public void start(WebDriver driver) {
		maximize();
		implicitWait();
		visit(url);
		implicitWait();
	}
	
	public void searchProduct() {
		String input = getProduct();
		type(input, inputSearch);
		screenShot("mercadolibre-capture" + getI());
		setI();
		click(searchButton);
	}
	
	public int getI() {
		return this.i;
	}
	
	public void setI() {
		i++;
	}
}
