package com.bankguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CommonPages.CommonFuntions;

public class EmailOTPPage extends CommonFuntions {
	public EmailOTPPage(WebDriver driver) {
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

	@FindBy(xpath = "//td[contains(.,'Confirm Your Registration')]")
	WebElement lbTitleEmail;
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

	public String getOTP() {
		waitVisible(lbTitleEmail, 120);
		click(lbTitleEmail);
		String textOtp = getText(lbOTPCode);
		return textOtp;
	}

	public RegisterPage openEnterOTPPage() {
		switchWindowByTitle("");
		return PageFactory.initElements(driver, RegisterPage.class);
	}

}
