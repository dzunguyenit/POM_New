package com.bankguru.customer;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.CommonPages.CommonTestcases;
import com.CommonPages.ExcelUtil;
import com.bankguru.EmailOTPPage;
import com.bankguru.RegisterPage;

public class Register extends CommonTestcases {
	WebDriver driver;
	RegisterPage registerPage;
	EmailOTPPage emailOTPPage;
	String userPath = System.getProperty("user.dir");
	ExcelUtil readExcel;
	String username, passsword, currentRow, kavaAddress, memoAddress;
	String excelPath = System.getProperty("user.dir").concat("/data/BinanceAccount.xlsx");
	String urlMail = "https://www.guerrillamail.com/inbox";
	String urlKava = "https://www.binance.com/en/my/wallet/account/main/deposit/crypto/KAVA";
	String emailRegex;

	@Parameters({ "browser", "version", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String version, String url) {
		driver = openMultiBrowser(browser, version, url);
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		readExcel = new ExcelUtil();

		registerPage.openBinancePage("https://accounts.binance.com/en/register");
		List<String> listAccount = readExcel.getAccountInfo(excelPath);

		username = listAccount.get(0);
		passsword = listAccount.get(1);
		currentRow = listAccount.get(2);

		emailRegex = getTextRegex("(.*)@.*", username);

		System.out.println("-----Username--------   = " + username);
		System.out.println("-----Password--------   = " + passsword);
		System.out.println("-----Dong hien tai---   = " + currentRow);
		System.out.println("-----emailRegex---   = " + emailRegex);

	}

	@Test(invocationCount = 600)
	public void getAccountRegister() {

//		registerPage.clickRegister();
		registerPage.inputEmail(username);
		registerPage.inputPassword(passsword);
		registerPage.clickCreateAccount();
		emailOTPPage = registerPage.openEmailUrl(urlMail);
		emailOTPPage.inputEmailOTP(emailRegex);
		emailOTPPage.selectSuffixEmail("grr.la");
		emailOTPPage.clickSet();
		String otp = emailOTPPage.getOTP();
		emailOTPPage.closeCurrentTab(1);
		registerPage = emailOTPPage.openEnterOTPPage();
		registerPage.inputOtp(otp);
		registerPage.clickGoToDashBoard();
		registerPage.openWalletPage(urlKava);
		registerPage.clickUnderStand();
		kavaAddress = registerPage.getKavaAddress();
		memoAddress = registerPage.getKavaMemo();
		System.out.println("--------kavaAddress------- = " + kavaAddress);
		System.out.println("--------memoAddress------- = " + memoAddress);
		registerPage.clickLogOut();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void afterMethod() {
		readExcel.updateAddressWallet(excelPath, Integer.parseInt(currentRow), kavaAddress, memoAddress);
		closeBrowser();
	}

}
