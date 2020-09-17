package com.bankguru.customer;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.CommonPages.CommonTestcases;
import com.CommonPages.ExcelUtil;
import com.bankguru.EmailOTPConMarketPage;
import com.bankguru.RegisterCoinMarketPage;

public class RegisterCoinMarket extends CommonTestcases {
	WebDriver driver;
	RegisterCoinMarketPage registerPage;
	EmailOTPConMarketPage emailOTPPage;
	String userPath = System.getProperty("user.dir");
	ExcelUtil readExcel;
	String randomUsername, password, currentRow, kavaAddress, memoAddress;
	String excelPath = System.getProperty("user.dir").concat("/data/BinanceAccount.xlsx");
	String urlMail = "https://www.guerrillamail.com/inbox";
	String urlCoinMarket = "https://accounts.coinmarketcap.com/signup?r=https%3A%2F%2Fcoinmarketcap.com%2F&utm_source=coinmarketcap&utm_content=nav";
	String emailRegex;

	@Parameters({ "browser", "version", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String version, String url) {
		driver = openMultiBrowser(browser, version, urlCoinMarket);
		registerPage = PageFactory.initElements(driver, RegisterCoinMarketPage.class);
		readExcel = new ExcelUtil();

		List<String> listAccount = readExcel.getAccountInfoCoinMarket(excelPath);

		randomUsername = randomCharacter(5) + randomUniqueNumber() + "@grr.la";

		password = listAccount.get(1);
		currentRow = listAccount.get(2);

		System.out.println("-----Username--------   = " + randomUsername);
		System.out.println("-----Password--------   = " + password);
		System.out.println("-----Dong hien tai---   = " + currentRow);

		emailRegex = getTextRegex("(.*)@.*", randomUsername);

		System.out.println("-----emailRegex---   = " + emailRegex);

	}

	@Test(invocationCount = 600)
	public void getAccountRegister() {

		registerPage.inputEmail(randomUsername);
		registerPage.inputPassword(password);
		registerPage.clickCheckboxGetA();
		registerPage.waitEmailSent();
		emailOTPPage = registerPage.openEmailUrl(urlMail);
		emailOTPPage.inputEmailOTP(emailRegex);
		emailOTPPage.selectSuffixEmail("grr.la");
		emailOTPPage.clickSet();
		emailOTPPage.clickLogInNow();
		Assert.assertEquals(emailOTPPage.getUrl(), "https://coinmarketcap.com/");

	}

	@AfterMethod
	public void afterMethod() {
		readExcel.updateAddressWalletCoinMarket(excelPath, Integer.parseInt(currentRow), randomUsername);
		closeBrowser();
	}

}
