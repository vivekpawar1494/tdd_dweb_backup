package com.qa.paths;

import java.io.IOException;

import com.qa.baseclass.Baseclass;

public class BaseclassPaths extends Baseclass {

	public static String environment;

	public static String siteUrl;

	public static String environementUrl;

	public static String company;

	public static void property() throws IOException {

		environment = configFile.getProperty("Environment");

		if (environment.equalsIgnoreCase("opensource")) {
			siteUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
			environementUrl = "opensource-";
		}

		company = "OrangeHRM";

	}
}
