package com.bankguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.CommonPages.CommonFuntions;

public class RegisterPage extends CommonFuntions {

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;

	@FindBy(xpath = "(//input[@inputmode='numeric'])[1]")
	WebElement txtOTP;

	@FindBy(css = "#click-registration-submit")
	WebElement btnCreateAccount;

	@FindBy(id = "header_register")
	WebElement btnRegister;

	public void inputEmail(String email) {
		waitVisible(txtEmail);
		input(txtEmail, email);
	}

	public void inputPassword(String pass) {
		waitVisible(txtPassword);
		input(txtPassword, pass);
	}

	public void inputOtp(String otp) {
		waitVisible(txtOTP);
		input(txtOTP, otp);
	}

	public void clickCreateAccount() {
		waitVisible(btnCreateAccount);
		click(btnCreateAccount);
	}

	public void clickRegister() {
		waitVisible(btnRegister);
		click(btnRegister);
	}

	public EmailOTPPage openEmailUrl(String url) {
		openTab();
		switchWindowTab(1);
		openUrl(url);
		return PageFactory.initElements(driver, EmailOTPPage.class);
	}

}
