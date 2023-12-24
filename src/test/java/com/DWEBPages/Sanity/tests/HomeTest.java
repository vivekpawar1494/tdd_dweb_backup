package com.DWEBPages.Sanity.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.locators.allPages.HomePO;
import com.qa.baseclass.Baseclass;
import com.qa.selenium.core.driver.IScreenAction;
import com.qa.selenium.core.element.IElementActions;
import com.qa.selenium.core.element.IWaitStrategy;
import com.qa.utils.HomePage;

public class HomeTest extends Baseclass {

	String className = this.getClass().getSimpleName();

	@Test(priority = 1)
	public void validatingHomPage() throws InterruptedException, IOException {

		try {
			extenttest = extent.createTest("Validating the user is able to search for product by its name")
					.assignCategory(sanitySuite_DWEB + "_" + env);
			node = extenttest.createNode("Report");

			// to load homepage
			HomePage.loadHomePage();


			// // Take screenshot
			IScreenAction.captureScreenShot_PassScenario("validatingHomPage", "Pass", driver);

			System.out.println("STEP5 : Screenshot Taken");
			node.log(Status.INFO, "STEP5 : Screenshot Taken");
		}

		catch (AssertionError validatingHomPage) {

			System.out.println("Reason for Assertion error is : " + validatingHomPage);
			node.log(Status.INFO, "Reason for Assertion error is : " + validatingHomPage);

			// Call assertionErrorCatchBlock_Data from IScreenAction class
			IScreenAction.assertionErrorCatchBlock_Data(node, driver, "validatingHomPage",
					"validatingHomPage_Fail", "HomeTest");

			validatingHomPage.printStackTrace();
			node.fail(validatingHomPage);
			Assert.fail();
			extent.flush();
		}

/*		catch (Exception validatingSearchProductByNameInCatalog) {

			System.out.println("Reason for Exception error is : " + validatingSearchProductByNameInCatalog);
			node.log(Status.INFO, "Reason for Exception error  is : " + validatingSearchProductByNameInCatalog);

			// Call exceptionErrorCatchBlock_Data from IScreenAction class
			IScreenAction.exceptionErrorCatchBlock_Data(node, driver, "validatingSearchProductByNameInCatalog",
					"validatingSearchProductByNameInCatalog_Fail", "SearchProductByNameInCatalogTest");

			validatingSearchProductByNameInCatalog.printStackTrace();
			node.fail(validatingSearchProductByNameInCatalog);
			Assert.fail();
			extent.flush();

		}
		*/
	}

	

}
