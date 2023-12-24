package com.qa.BrowserStackSetup;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.baseclass.Baseclass;

public class BrowserStackSetup extends Baseclass {

	// Browsertack account configuration
	public static final String AUTOMATE_USERNAME = configFile.getProperty("BrowserstackUserName");
	public static final String AUTOMATE_ACCESS_KEY = configFile.getProperty("BrowserstackPassword");
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	// Browserstack setup on Chrome Browser For Windows 10
	public static WebDriver setupChromeBrowserstack() throws Exception {

		DesiredCapabilities caps = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "latest");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("resolution", "1440x900");
		// options.addArguments("start-maximized");
		// caps.setCapability("browserstack.local", true);
		caps.setCapability("unhandledPromptBehavior", "ignore");
		caps.setCapability("browserstack.idleTimeout", "300");
		HashMap<String, Boolean> networkLogsOptions = new HashMap<>();
		networkLogsOptions.put("captureContent", true);
		caps.setCapability("browserstack.networkLogs", true);

		caps.setCapability("browserstack.networkLogsOptions",
				networkLogsOptions);

		caps.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
		// Creates an instance of Local
		// Local bsLocal = new Local();

		// You can also set an environment variable - "BROWSERSTACK_ACCESS_KEY".
		// HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
		// bsLocalArgs.put("key",
		// configFile.getProperty("BrowserstackPassword"));

		// Starts the Local instance with the required arguments
		// bsLocal.start(bsLocalArgs);

		// Check if BrowserStack local instance is running
		// System.out.println(bsLocal.isRunning());

		driver = new RemoteWebDriver(new URL(URL), caps);
		return driver;

	}

}

// Stop the Local instance
// bsLocal.stop();

// //ChromeOptions options = new ChromeOptions();
// Map < String, Object > prefs = new HashMap < String, Object > ();
// Map < String, Object > profile = new HashMap < String, Object > ();
// Map < String, Object > contentSettings = new HashMap < String, Object > ();
//
// // SET CHROME OPTIONS
// // 0 - Default, 1 - Allow, 2 - Block
// contentSettings.put("geolocation", 1);
// profile.put("managed_default_content_settings", contentSettings);
// prefs.put("profile", profile);
// options.setExperimentalOption("prefs", prefs);
//
// // SET CAPABILITY
// caps.setCapability(ChromeOptions.CAPABILITY, options);
// WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
