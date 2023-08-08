package com.zoho.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.zoho.base.pages.Constants;
import com.zoho.listener.ZohoEventListener;

public class ZohoDriver extends ZohoValidationDriver {
	public void logout() {

	}

	public void openBrowser(String bName) {
		log("Opening the browser " + bName);
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\sachidananda.sahoo\\Downloads\\drivers\\chromedriver_win32\\chromedriver.exe");

		if (Constants.GRIDRUN.equals("Y")) {
			// desired capabilities
			DesiredCapabilities cap = new DesiredCapabilities();
			if (bName.equalsIgnoreCase("chrome")) {
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.WINDOWS);
			} else if (bName.equalsIgnoreCase("mozilla")) {
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.WINDOWS);
			}

			try {
				driver = new EventFiringWebDriver(new RemoteWebDriver(new URL("http://localhost:4444"), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				setStopExecution(true);
				fail("Unable to launch test on hub " + e.getMessage());
			}

		} else {// normal run

			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");
			driver = new EventFiringWebDriver(new ChromeDriver(opt));// Launching the LaunchPage
			// driver = new EventFiringWebDriver(new FirefoxDriver());
			/*
			 * if(bName.equalsIgnoreCase("chrome")) { ChromeOptions opt = new
			 * ChromeOptions(); opt.addArguments("--disable-notifications"); driver = new
			 * EventFiringWebDriver(new ChromeDriver(opt));// Launching the LaunchPage }else
			 * if(bName.equalsIgnoreCase("mozilla")) { driver = new EventFiringWebDriver(new
			 * FirefoxDriver());// Launching the LaunchPage
			 * 
			 * }
			 */
		}

		driver.register(new ZohoEventListener());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void navigate(String url) {
		log("URL" + url);
		driver.get(url);

	}

	public void quit() {
		if (driver != null)
			driver.quit();

	}

	public EventFiringWebDriver getCurrentDriver() {
		return driver;
	}

	public void waitForElementLoad() {

	}
}
