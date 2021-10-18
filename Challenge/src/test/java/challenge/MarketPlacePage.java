package challenge;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MarketPlacePage extends MarketplaceBase {
	
	int i = 0;	
	
	String marketplace = "";
	
	String url = "";
	
	public MarketPlacePage(WebDriver driver, String name, String url) {
		super(driver);
		this.marketplace = name;
		this.url = url;
	}
	
	public void before(WebDriver driver, MarketPlacePage ml) {
		ml.start(driver);
		try {
			createCSV("./" + marketplace +".xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    	
	
	public void start(WebDriver driver) {
		maximize();
		implicitWait();
		visit(this.url);
		implicitWait();
	}
	
	public void searchProduct(By inputSearch, By button) {
		String input = getProduct();
		type(input, inputSearch);
		screenShot(marketplace + "-capture-" + getI());
		setI();
		click(button);
	}
	
	public int getI() {
		return this.i;
	}
	
	public void setI() {
		i++;
	}
}
