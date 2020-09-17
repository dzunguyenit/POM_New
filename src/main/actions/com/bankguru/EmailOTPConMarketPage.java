package com.bankguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CommonPages.CommonFuntions;

public class EmailOTPConMarketPage extends CommonFuntions {
	public EmailOTPConMarketPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "inbox-id")
	WebElement txtEmailOTP;

	@FindBy(css = "#inbox-id input")
	WebElement txtOTP;

	@FindBy(id = "gm-host-select")
	WebElement cbxSuffixEmail;

	@FindBy(xpath = "//button[text()='Set']")
	WebElement btnSet;

	@FindBy(xpath = "(//td[contains(text(),'accounts@e.coinmarketcap.com')])[1]")
	WebElement lbTitleEmail;

	@FindBy(xpath = "//a[contains(text(),'Verify account')]")
	WebElement btnLogInNow;

	@FindBy(xpath = "//span[contains(@style,'9b434')]")
	WebElement lbOTPCode;

	public void inputEmailOTP(String email) {
		waitVisible(txtEmailOTP);
		click(txtEmailOTP);
		click(txtEmailOTP);
		clearAndInput(txtOTP, email);
	}

	public void selectSuffixEmail(String value) {
		selectCombobox(cbxSuffixEmail, value);
	}

	public void clickSet() {
		click(btnSet);
	}

	public void clickLogInNow() {
		waitVisible(lbTitleEmail, 240);
		click(lbTitleEmail);
		click(btnLogInNow);
		switchWindowTab(2);
	}

	public String getUrl() {
		return getCurrentUrl();
	}

	public RegisterPage openEnterOTPPage() {
		switchWindowByTitle("");
		return PageFactory.initElements(driver, RegisterPage.class);
	}

}
