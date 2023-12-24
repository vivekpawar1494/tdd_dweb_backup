package com.qa.selenium.core.driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import com.qa.baseclass.Baseclass;

public class IWebWindow extends Baseclass {

	public static void switchWindow(ExtentTest test) throws AWTException {
		// Use robot class for window switch
		Robot robot = new Robot();  
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);

		// Release key Ctrl+Shift+i
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);
	}

	public static void switchTab(ExtentTest test) throws AWTException {
		// Use robot class for tab switch
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);

		// Release key tab+control
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	/**
	 * The "closeChildTabsAndSwitchToParentTab" function is used to close all child
	 * Tabs except parent window
	 *
	 * @param mainTabHandle Input parent Tab Handle
	 * @author Chirag Mittal
	 * @version 1.0
	 * @throws IOException
	 * @since 29.10.2022
	 */
	public static void closeChildWindowAndSwitchToParent(String mainTabHandle) throws InterruptedException {
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(mainTabHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
			driver.switchTo().window(mainTabHandle);
		}
	}
}
