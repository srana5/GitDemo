package rsa.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rsa.TestComponents.BaseTest;
import rsa.pageobjects.CartPage;
import rsa.pageobjects.CheckoutPage;
import rsa.pageobjects.ConfirmationPage;
import rsa.pageobjects.LandingPage;
import rsa.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage cnfPage;
	@Given("I landed on ECommerce page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged In with username (.+) and password (.+)$")
	public void logged_In_Username_and_Password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username,password);
	}

	@When("^I add the product (.+) from Menu$")
	public void add_Product_from_Menu(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_And_Submit_Order(String productName) {
		CartPage cart = productCatalogue.goToCartPage();
		boolean match = cart.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cart.goToCheckout();
		checkoutPage.selectCountry("india");
		cnfPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation page")
	public void message_displayed_Confirmationpage(String string) {
		String orderCnf = cnfPage.readOrderMessage();
		Assert.assertTrue(orderCnf.equalsIgnoreCase(string));
		cnfPage.logoutUser();
		driver.quit();
	}
	
	 @Then("^\"([^\"]*)\" message is displayed$")
	    public void error_message_is_displayed(String message) {
		 Assert.assertEquals(message, landingPage.getErrorMessage());
		 driver.quit();
	    }
}
