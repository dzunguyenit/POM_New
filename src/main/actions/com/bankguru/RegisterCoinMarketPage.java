package com.bankguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CommonPages.CommonFuntions;

public class RegisterCoinMarketPage extends CommonFuntions {

	public RegisterCoinMarketPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[type=email]")
	WebElement txtEmail;

	@FindBy(css = "[type=password]")
	WebElement txtPassword;

	@FindBy(css = "input[type=checkbox]")
	WebElement ckxGetA;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement btnSignUp;

	@FindBy(xpath = "//h1[text()='Check your email']")
	WebElement lbSendEmail;

	@FindBy(xpath = "(//input[@inputmode='numeric'])[1]")
	WebElement txtOTP;

	public void inputEmail(String email) {
		waitVisible(txtEmail);
		input(txtEmail, email);
	}

	public void clickCheckboxGetA() {
		waitVisible(ckxGetA);
		click(ckxGetA);
	}

	public void inputPassword(String pass) {
		waitVisible(txtPassword);
		input(txtPassword, pass);
	}

	public void inputOtp(String otp) {
		waitVisible(txtOTP);
		input(txtOTP, otp);
	}

	public void waitEmailSent() {
		waitClickable(btnSignUp, 300);
		click(btnSignUp);
		waitVisible(lbSendEmail, 300);
	}

	public EmailOTPConMarketPage openEmailUrl(String url) {
		openTab();
		switchWindowTab(1);
		openUrl(url);
		return PageFactory.initElements(driver, EmailOTPConMarketPage.class);
	}

}
