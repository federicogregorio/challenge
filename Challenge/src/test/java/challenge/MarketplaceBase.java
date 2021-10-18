package challenge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MarketplaceBase {
	private WebDriver driver;
	
	public MarketplaceBase(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver chromeDriverConnect() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver94/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
	public void maximize() {
		driver.manage().window().maximize();
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> findElements(By locator){
		return driver.findElements(locator);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public void type(String input, By locator) {
		driver.findElement(locator).sendKeys(input);
	}
	
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	public Boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void visit(String url) {
		driver.get(url);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void close(WebDriver driver) {
		driver.quit();
	}
	
	public void screenShot(String name) {
	    File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
	    	FileUtils.copyFile(screenshotFile, new File(name+".png"));
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public String getProduct() {
		Properties config = new Properties();
	    InputStream configInput = null;
	    String product = "";
        try{
            configInput = new FileInputStream("./src/main/resources/config.properties");
            config.load(configInput);
            System.out.println(config.getProperty("product"));
            product = config.getProperty("product");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return product;
	}
	
	public void createCSV(String fileName) throws IOException {
		
		File file = new File(fileName);
		
		FileOutputStream fout=new FileOutputStream(file);
        
		XSSFWorkbook wb =new XSSFWorkbook();
		
		
		wb.createSheet("Sheet1");
		
		XSSFSheet sh1= (XSSFSheet) wb.getSheetAt(0);

		sh1.createRow(0);
		//crea una fila antes de escribir en ella
		sh1.getRow(0).createCell(0).setCellValue("Product name");
		sh1.getRow(0).createCell(1).setCellValue("Price");
		sh1.getRow(0).createCell(2).setCellValue("Link to prodcut listing");
				
		wb.write(fout);
		

	}
	
	public void writeCSV(String fileName, int row, String name, String price, String link) throws IOException {
		File file = new File(fileName);
		
		FileInputStream fis=new FileInputStream(file);
		
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet sh1= wb.getSheetAt(0);
		

		sh1.createRow(row);
		
		sh1.getRow(row).createCell(0).setCellValue(name);
		sh1.getRow(row).createCell(1).setCellValue(price);
		sh1.getRow(row).createCell(2).setCellValue(link);
		
		FileOutputStream outputStream = new FileOutputStream(fileName);

	    //write data in the excel file

	    wb.write(outputStream);

	    //close output stream

	    outputStream.close();
		
	}
}
