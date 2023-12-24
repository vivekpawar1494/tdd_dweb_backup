package com.qa.paths;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;

public class WebdriverPaths extends Baseclass {

	public static void Webdriver_chrome() {
		System.out.println("SETUP-STEP1 : SETUP using Chrome driver");

		// Check the system OS and launch the WebDriver accordingly
		System.out
		.println("SETUP-STEP2 : Checking the system OS to launch the webdriver accordingly");

		if (systemOsName.contentEquals(linuxOS)) {

			System.out
			.println("SETUP-STEP3 : Code is running in---->"
					+ linuxOS
					+ "---->system, so launching the Chrome webdriver as per that");

			System.setProperty("webdriver.chrome.driver",
					commonChromeWebdriverPath);

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

		} else if (systemOsName.contentEquals(windowsOS)) {

			System.out
			.println("SETUP-STEP3 : Code is running in---->"
					+ windowsOS
					+ "---->system, so launching the Chrome webdriver as per that");

			System.setProperty("webdriver.chrome.driver",
					commonChromeWebdriverPath + ".exe");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

		}

		else if (systemOsName.contentEquals(Mac)) {

			System.out
			.println("STEP4 : Code is running in---->"
					+ Mac
					+ "---->system, so launching the Chrome webdriver as per that");

			System.setProperty("webdriver.chrome.driver",
					commonChromeWebdriverPath_mac);

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

		}
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		System.out.println("SETUP-STEP5 : Window size Maximized");

	}

	public static void Webdriver_firefox() {
		System.out.println("SETUP-STEP1 : SETUP using Firefox driver");
		node.log(Status.INFO, "SETUP-STEP1 : SETUP using Firefox driver");

		// Check the system OS and launch the webdriver accordingly
		System.out
		.println("SETUP-STEP2 :Checking the system OS to launch the webdriver accordingly");

		if (systemOsName.contentEquals(linuxOS)) {

			System.out
			.println("SETUP-STEP3 :Code is running in---->"
					+ linuxOS
					+ "---->system, so launching the Firefox webdriver as per that");

			System.setProperty("webdriver.gecko.driver",
					commonFirefoxWebdriverPath);

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

		} else if (systemOsName.contentEquals(windowsOS)) {

			System.out
			.println("SETUP-STEP3 : Code is running in---->"
					+ windowsOS
					+ "---->system, so launching the Firefox webdriver as per that");

			System.setProperty("webdriver.gecko.driver",
					commonFirefoxWebdriverPath + ".exe");

			System.out.println("SETUP-STEP4 : Code is running in---->"
					+ configFile.getProperty("Environment")
					+ "---->Enviornment");

		}

		driver = new FirefoxDriver();

		((Options) driver.manage().timeouts()
				.setScriptTimeout(60, TimeUnit.SECONDS)).window().maximize();

		System.out.println("SETUP-STEP5 : Window size Maximized.");

	}

}
