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
	String randomUsername, password, currentRow, kavaAddress, memoAddress;
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

		randomUsername = randomCharacter(5) + randomUniqueNumber() + "@grr.la";

		password = "$Hoangxuan68@$";
		currentRow = listAccount.get(0);

		System.out.println("-----Username--------   = " + randomUsername);
		System.out.println("-----Password--------   = " + password);
		System.out.println("-----Dong hien tai---   = " + currentRow);

		emailRegex = getTextRegex("(.*)@.*", randomUsername);

		System.out.println("-----emailRegex---   = " + randomUsername);

	}

	@Test(invocationCount = 600)
	public void getAccountRegister() {

//		registerPage.clickRegister();
		registerPage.inputEmail(randomUsername);
		registerPage.inputPassword(password);
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
		readExcel.updateAddressWallet(excelPath, Integer.parseInt(currentRow), randomUsername, kavaAddress,
				memoAddress);
		closeBrowser();
	}

}
