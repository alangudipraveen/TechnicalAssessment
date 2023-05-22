package com.reusablelibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonOperation {

	public static WebDriver driver;

	public static void driverPath(String driver, String driverPath) {

		System.setProperty(driver, driverPath);
	}

	public static void getDriver(String browserName, String driverPath) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", driverPath);
			driver = new EdgeDriver();
		}
	}

	public static void getUrl(String url) {

		driver.get(url);
	}

	public static void maximize() {

		driver.manage().window().maximize();
	}

	public static void quit() {

		driver.quit();
	}

	public static void pageLoadTimeout(int sec) {
		driver.manage().timeouts().pageLoadTimeout(sec,TimeUnit.SECONDS);

	}
	public static void implicitWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

	}
	public static void deleteAllCookies() {
		
       driver.manage().deleteAllCookies();
	}

	public WebElement findWebelementByText(String textTosearch) {
		return driver.findElement(By.xpath("//*[contains(text(),'" + textTosearch + "')]"));

	}

	public void selectByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

	public void selectByValue(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByValue(text);

	}

	public void selectByIndex(WebElement element, int text) {
		Select select = new Select(element);
		select.selectByIndex(text);

	}

	public String currentSysDate(String dateform) {
		DateFormat dateformat = new SimpleDateFormat(dateform);
		Date date1 = new Date();
		String date2 = dateformat.format(date1);
		return date2;

	}

	public static String getscreenshot(WebDriver driver,String screenshotName) throws IOException {

		String dateName = new SimpleDateFormat(" yyyy-MM-dd (hh-mm)").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/test-output/Reports/TestsScreenshots/" + screenshotName
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
			
	}

	public void uploadFile(String documentpath) throws AWTException {
		Robot robot = new Robot();
		robot.setAutoDelay(5000);
		StringSelection selection = new StringSelection(documentpath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}
}
