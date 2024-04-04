package rsa.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rsa.pageobjects.CartPage;
import rsa.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "li:nth-child(5)")
	WebElement logoutBtn;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement ordersBtn;
	
	public void waitForElementToAppear(By FindBy) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement FindBy) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.visibilityOf(FindBy));
	}

	public void waitForElementToDisappear(WebElement elem) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.invisibilityOf(elem));
	}
	
	
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cart = new CartPage(driver);
		return cart;
		
	}
	
	public OrderPage goToOrdersPage() {
		ordersBtn.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	
	public void logoutUser() {
		logoutBtn.click();
	}
}