
package com.qa.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.locators.allPages.HomePO;
import com.qa.baseclass.Baseclass;
import com.qa.selenium.core.element.IElementActions;
import com.qa.selenium.core.element.IWaitStrategy;
import com.testdata.allPages.AllPagesUrls;

public class HomePage extends Baseclass {

	public static void loadHomePage() {
		// Open dweb login page as per the environment selected by user
		node.log(Status.INFO, "SUB-STEP_0.001 : Opening the home page by fetching the url from AllPagesUrls interface");
		System.out.println("SUB-STEP_0.001 : Opening the home page by fetching the url from AllPagesUrls interface");

		driver.get(AllPagesUrls.openSourceLoginPage);

		// Wait for the visibility of login form
		node.log(Status.INFO, "SUB-STEP_0.002 : Waiting for the visibility of openSourceLoginPage.");
		System.out.println("SUB-STEP_0.002 : Waiting for the visibility of openSourceLoginPage.");

		IWaitStrategy.waitForVisiblity(node, driver, HomePO.loginBanner);

		node.log(Status.INFO, "SUB-STEP_0.003 : openSourceLoginPage is visible now.");
		System.out.println("SUB-STEP_0.003 : openSourceLoginPage is visible now.");

	}

	

}
