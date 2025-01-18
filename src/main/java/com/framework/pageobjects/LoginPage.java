package com.framework.pageobjects;

import com.framework.utilities.LoggerUtility;
import com.framework.utilities.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private final WebDriver driver;
	private final WaitHelper waitHelper;

	@FindBy(id = "username")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "loginButton")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.waitHelper = new WaitHelper(driver);
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		LoggerUtility.info("Entering username: " + username);
		waitHelper.waitForElementToBeVisible(usernameField, 10).sendKeys(username);
	}

	public void enterPassword(String password) {
		LoggerUtility.info("Entering password.");
		waitHelper.waitForElementToBeVisible(passwordField, 10).sendKeys(password);
	}

	public void clickLoginButton() {
		LoggerUtility.info("Clicking on the login button.");
		waitHelper.waitForElementToBeClickable(loginButton, 10);
		loginButton.click();
	}
}
