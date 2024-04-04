package rsa.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import rsa.TestComponents.BaseTest;
import rsa.TestComponents.Retry;
import rsa.pageobjects.CartPage;
import rsa.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void incorrectLoginCredentials() throws IOException {

		landingPage.loginApplication("alt.gq-eon86v6j@yopmail.com", "Qwerty1234@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test(groups = {"ErrorHandling"})
	public void productValidationTest() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("alt.gq-eon86v6j@yopmail.com", "Qwerty123@");

		productCatalogue.addProductToCart(productName);
		CartPage cart = productCatalogue.goToCartPage();
		boolean match = cart.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
