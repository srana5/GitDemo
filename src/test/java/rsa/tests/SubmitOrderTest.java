package rsa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rsa.TestComponents.BaseTest;
import rsa.TestComponents.Retry;
import rsa.pageobjects.CartPage;
import rsa.pageobjects.CheckoutPage;
import rsa.pageobjects.ConfirmationPage;
import rsa.pageobjects.OrderPage;
import rsa.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cart = productCatalogue.goToCartPage();
		boolean match = cart.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cart.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage cnfPage = checkoutPage.submitOrder();
		String orderCnf = cnfPage.readOrderMessage();
		Assert.assertEquals(orderCnf, "THANKYOU FOR THE ORDER.");
		Assert.assertTrue(orderCnf.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		cnfPage.logoutUser();
		System.out.println("User is logged out");

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData")
	public void orderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> hashMap = new HashMap<String, String>();
//		hashMap.put("email", "alt.gq-eon86v6j@yopmail.com");
//		hashMap.put("password", "Qwerty123@");
//		hashMap.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> hashMap1 = new HashMap<String, String>();
//		hashMap1.put("email", "shetty@gmail.com");
//		hashMap1.put("password", "Iamking@000");
//		hashMap1.put("productName", "ADIDAS ORIGINAL");

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rsa\\data\\PurchaseOrder.json");

//		return new Object[][] { { hashMap }, { hashMap1 } };
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	Other way around
//	@DataProvider
//	public Object[][] getData1() {
//		return new Object[][] { { "alt.gq-eon86v6j@yopmail.com", "Qwerty123@", "ZARA COAT 3" },
//				{ "shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };
//	}
}
