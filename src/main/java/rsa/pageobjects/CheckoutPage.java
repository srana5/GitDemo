package rsa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryField;

	@FindBy(css = "section.ta-results button:last-of-type")
	WebElement countryOption;
	
	@FindBy(css = "a.btnn")
	WebElement submitBtn;

	By countrySearchResult = By.cssSelector("section.ta-results");

	public void selectCountry(String countryName) {
		Actions country = new Actions(driver);
		country.sendKeys(countryField, countryName).build().perform();
		waitForElementToAppear(countrySearchResult);
		countryOption.click();

	}

	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}
}
