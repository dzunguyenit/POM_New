package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.CommonPages.CommonTestcases;
import com.bankguru.HomePage;
import com.bankguru.LoginPage;
import com.bankguru.RegisterPage;

public class Register extends CommonTestcases {
	WebDriver driver;
	LoginPage loginPage;
	RegisterPage registerPage;
	HomePage homePage;
	String userPath = System.getProperty("user.dir");

	String email;
	static String emailLogin, passwordLogin;

	@Parameters({ "browser", "version", "url" })
	@BeforeClass
	public void beforeClass(String browser, String version, String url) {

		log.info("----------OPEN BROWSER-----------");
		driver = openMultiBrowser(browser, version, url);

		email = "vu" + randomEmail() + "@gmail.com";

		log.info("----------OPEN BROWSER-----------");

	}

	@Test
	public void getAccountRegister() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		registerPage = loginPage.clickHereLink();
		registerPage.inputEmail(email);
		registerPage.clickSubmitButton();
		emailLogin = registerPage.getUserIDInfo();
		passwordLogin = registerPage.getPasswordIDInfo();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
