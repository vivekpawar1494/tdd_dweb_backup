package com.qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.qa.BrowserStackSetup.BrowserStackSetup;
import com.qa.paths.BaseclassPaths;
import com.qa.paths.WebdriverPaths;
import com.qa.selenium.core.driver.IScreenAction;
import com.qa.selenium.core.element.IElementActions;
import com.qa.testlistener.TestListeners;

@Listeners(TestListeners.class)
public class Baseclass {

	public static String env;
	public static String user_Name;

	public static String url;
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest extenttest;
	public static ExtentTest node;
	public static WebDriver driver;
	public static Properties configFile = new Properties();
	public static String systemOsName = System.getProperty("os.name");
	public static String commonChromeWebdriverPath = System.getProperty("user.dir") + "/Chromedriver/chromedriver";
	public static String commonFirefoxWebdriverPath = System.getProperty("user.dir") + "/FirefoxDriver/geckodriver";
	public static String commonChromeWebdriverPath_mac = System.getProperty("user.dir")
			+ "/Chromedriver/chromedrivermac";
	public static String windowsOS = "Windows 10";
	public static String linuxOS = "Linux";
	public static String Mac = "Mac OS X";
	public static String sanitySuite_DWEB = "SanitySuite_DWEB";
	public static String regressionSuite_DWEB = "RegressionSuite_Dweb";

	public static File src;

	@Parameters({ "Browser" })
	@BeforeSuite
	public static void setUp(String Browser) throws Exception {

		// Config Property File
		File ConfigFile = new File(System.getProperty("user.dir") + "/ConfigFile/Config.properties");

		FileInputStream fileInput3 = null;
		fileInput3 = new FileInputStream(ConfigFile);
		configFile.load(fileInput3);

		// Initializing the property method by calling property function from
		// BaseclassPaths
		BaseclassPaths.property();

		// Create object of baseclasspath and access its variable
		BaseclassPaths obj = new BaseclassPaths();
		env = configFile.getProperty("Environment");
		user_Name = configFile.getProperty("User_Name");
		url = obj.siteUrl;
	//	Utils util = new Utils();

		// Create report folder in project root
		IScreenAction.createDirectoryForReport();

		// Launch the webdriver as per browser selected in config file
		Browser = configFile.getProperty("Browser");

		if (Browser.equalsIgnoreCase("chrome")) {

			// Call Chrome function WebdriverPaths class
			WebdriverPaths.Webdriver_chrome();

		}

		else if (Browser.equalsIgnoreCase("firefox")) {

			// Call Firefox function for WebdriverPaths class
			WebdriverPaths.Webdriver_firefox();
		}

		// Input the Browser=BrowserStackChrome in Config file
		// if you want to
		// run your code in Browserstack Chrome
		else if (Browser.equalsIgnoreCase("BrowserStackChrome")) {

			System.out.println("STEP1 : User is runing the code in Browserstack Chrome");

			// Call setupBrowserstack from Browserstack class
			driver = BrowserStackSetup.setupChromeBrowserstack();

		}

		// Extent Report Setup
		reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/QAAutomationReport/Report_"
				+ IScreenAction.getFileExtension() + "Spark.html").viewConfigurer().viewOrder()
						.as(new ViewName[] { ViewName.CATEGORY, ViewName.DASHBOARD, ViewName.TEST, ViewName.EXCEPTION,
								ViewName.AUTHOR, ViewName.DEVICE, ViewName.LOG })
						.apply();
		extent = new ExtentReports();
		extent.attachReporter(reporter);

		extent.setSystemInfo("OS", systemOsName);
		extent.setSystemInfo("Host Name", user_Name);
		extent.setSystemInfo("Environment", env);

		extent.setSystemInfo("Url", url);

		reporter.config().setDocumentTitle("Automation-Testing");
		reporter.config().setReportName("OrangeHRM-DwebAutomation-Windows");
		reporter.config().setTheme(Theme.DARK);

	}

	@AfterMethod
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			node.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " :Test case FAILED due to below issues:",
					ExtentColor.RED));
			node.log(Status.FAIL, result.getThrowable());

			// test1.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			node.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " :Test Case PASSED", ExtentColor.GREEN));

		} else if (result.getStatus() == ITestResult.SKIP) {
			node.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " :Test Case SKIPPED ", ExtentColor.YELLOW));

		}
	}

	public static String getExcelSheetData(int col2RowValue) throws IOException, FileNotFoundException {
		if (BaseclassPaths.environment.equalsIgnoreCase("opensource")) {

			System.out.println("User is running the code in Opensource_Env so fetching the TestData as per that");

			// Import excel sheet.
			src = new File(".DataFiles/OpensourceTestData.xlsx");

		}

		ArrayList<Object> arrObj = new ArrayList<Object>();
		FileInputStream fs = new FileInputStream(src);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet workSheet = workbook.getSheetAt(0);
		int totalRowCount = workSheet.getLastRowNum() + 1;
		int totalColoumnCount = workSheet.getRow(0).getLastCellNum();
		Iterator<Row> row = workSheet.rowIterator();
		int column_num = 0;
		for (int i = 0; i < totalColoumnCount; i++) {
			if (workSheet.getRow(0).getCell(i).getStringCellValue().trim().equals("InputDataValue"))
				column_num = i;
		}

		if (workSheet.getRow(col2RowValue).getCell(column_num).getCellTypeEnum() == CellType.STRING) {
			return workSheet.getRow(col2RowValue).getCell(column_num).toString();
		} else {
			return NumberToTextConverter
					.toText(workSheet.getRow(col2RowValue).getCell(column_num).getNumericCellValue());
		}

	}

	public static String getGiftCardExcelSheetData(int row, int column) throws IOException, FileNotFoundException {
		System.out.println("User is fetching the data from GiftCards Sheet");
		// creating a new file instance
		src = new File("./GiftCards.xlsx");
		// obtaining bytes from the file
		FileInputStream fs = new FileInputStream(src);

		// creating Workbook instance that refers to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet workSheet = null;

		if (BaseclassPaths.environment.equalsIgnoreCase("opensource")
				|| BaseclassPaths.environment.equalsIgnoreCase("opensource")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("opensource");

		} else if (BaseclassPaths.environment.equalsIgnoreCase("pod1")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("pod1");
		} else if (BaseclassPaths.environment.equalsIgnoreCase("pod2")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("pod2");
		} else if (BaseclassPaths.environment.equalsIgnoreCase("pod3")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("pod3");
		}

		if (workSheet.getRow(row).getCell(column).getCellType() == CellType.STRING) {
			return workSheet.getRow(row).getCell(column).toString();
		} else {
			return NumberToTextConverter.toText(workSheet.getRow(row).getCell(column).getNumericCellValue());
		}
		// return workSheet.getRow(row).getCell(column).toString();
	}

	public static void deleteRowFromGiftCardExcelSheet(int rowIndex) throws IOException, FileNotFoundException {
		System.out.println("User is fetching the data from GiftCards Sheet");
		// creating a new file instance
		src = new File("./GiftCards.xlsx");
		// obtaining bytes from the file
		FileInputStream fs = new FileInputStream(src);

		// creating Workbook instance that refers to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet workSheet = null;

		if (BaseclassPaths.environment.equalsIgnoreCase("opensource")
				|| BaseclassPaths.environment.equalsIgnoreCase("opensource")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("opensource");

		} else if (BaseclassPaths.environment.equalsIgnoreCase("pod1")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("pod1");
		} else if (BaseclassPaths.environment.equalsIgnoreCase("pod2")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("pod2");
		} else if (BaseclassPaths.environment.equalsIgnoreCase("pod3")) {

			// creating a Sheet object to retrieve object
			workSheet = workbook.getSheet("pod3");
		}

		int lastRowNum = workSheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			workSheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			Row removingRow = workSheet.getRow(rowIndex);
			if (removingRow != null) {
				workSheet.removeRow(removingRow);
			}
		}
		FileOutputStream outputStream = new FileOutputStream(src);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	public static String getExcelSheetDataUsingSheet(String sheet, int col2RowValue)
			throws IOException, FileNotFoundException {
		System.out.println("User is fetching the data from " + sheet + " Sheet as per that");
		if (BaseclassPaths.environment.equalsIgnoreCase("opensource")
				|| BaseclassPaths.environment.equalsIgnoreCase("opensource")) {

			src = new File(".DataFiles/OpensourceTestData.xlsx");

		} else if (BaseclassPaths.environment.equalsIgnoreCase("pod1")) {

			// Import excel sheet.
			src = new File("./Pod1TestData.xlsx");

		}

		ArrayList<Object> arrObj = new ArrayList<Object>();
		// obtaining bytes from the file
		FileInputStream fs = new FileInputStream(src);
		// creating Workbook instance that refers to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		// creating a Sheet object to retrieve object
		XSSFSheet workSheet = workbook.getSheet(sheet);
		int totalRowCount = workSheet.getLastRowNum() + 1;
		int totalColoumnCount = workSheet.getRow(0).getLastCellNum();
		Iterator<Row> row = workSheet.rowIterator();
		int column_num = 0;
		for (int i = 0; i < totalColoumnCount; i++) {
			if (workSheet.getRow(0).getCell(i).getStringCellValue().trim().equals("InputDataValue"))
				column_num = i;
		}

		if (workSheet.getRow(col2RowValue).getCell(column_num).getCellType() == CellType.STRING) {
			return workSheet.getRow(col2RowValue).getCell(column_num).toString();
		} else {
			return NumberToTextConverter
					.toText(workSheet.getRow(col2RowValue).getCell(column_num).getNumericCellValue());
		}

	}

	@BeforeTest
	public void closeChildWindowsIfAny() throws InterruptedException, IOException {

		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		IElementActions.closeChildWindowAndSwitchToParent(mainWindowHandle);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		extent.flush();

	}

}
