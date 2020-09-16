package com.bankguru.customer;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.CommonPages.CommonTestcases;
import com.CommonPages.ExcelUtil;
import com.bankguru.HomePage;
import com.bankguru.LoginPage;
import com.bankguru.RegisterPage;

public class Register extends CommonTestcases {
	WebDriver driver;
	LoginPage loginPage;
	RegisterPage registerPage;
	HomePage homePage;
	String userPath = System.getProperty("user.dir");
	ExcelUtil readExcel;

	String email;
	static String emailLogin, passwordLogin;

	@Parameters({ "browser", "version", "url" })
	@BeforeClass
	public void beforeClass(String browser, String version, String url) {

		log.info("----------OPEN BROWSER-----------");
		driver = openMultiBrowser(browser, version, url);
		readExcel = new ExcelUtil();
		String excelPath = System.getProperty("user.dir").concat("/data/BinanceAccount.xlsx");
		
		List<String> listAccount = readExcel.getAccountInfo(excelPath);
	
		String username = listAccount.get(0);
		String passsword = listAccount.get(1);

		log.info("-----Email--------   =  " + email);

	}

	@Test
	public void getAccountRegister() {
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		registerPage.inputEmail(email);
//		registerPage.clickSubmitButton();
		emailLogin = registerPage.getUserIDInfo();
		passwordLogin = registerPage.getPasswordIDInfo();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
