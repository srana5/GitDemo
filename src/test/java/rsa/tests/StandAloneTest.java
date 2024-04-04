package rsa.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rsa.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		String userName = "alt.gq-eon86v6j@yopmail.com";
		String password = "Qwerty123@";
		driver.findElement(By.id("userEmail")).sendKeys(userName);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));

//		products.stream().filter(a->a.getText().contains("ZARA")).forEach(s->s.findElement(By.cssSelector("button.w-10")).click());

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		wt.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector("div.cartSection h3"));
		boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("li.totalRow button")).click();

		Actions country = new Actions(driver);
		country.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
		driver.findElement(By.cssSelector("section.ta-results button:last-of-type")).click();
		driver.findElement(By.cssSelector("a.btnn")).click();
		String orderCnf = driver.findElement(By.cssSelector("h1.hero-primary")).getText().trim();
		Assert.assertEquals(orderCnf, "THANKYOU FOR THE ORDER.");
		Assert.assertTrue(orderCnf.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.findElement(By.cssSelector("li:nth-child(5)")).click();
		driver.quit();
		
//		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
//		List<WebElement> countries = driver.findElements(By.cssSelector("span.ng-star-inserted"));
//
//		WebElement country = countries.stream().filter(s -> s.getText().equalsIgnoreCase("India")).findFirst()
//				.orElse(null);
//		if (country != null) {
//			country.click();
//		}
//		driver.findElement(By.cssSelector("a.btnn")).click();
//		String orderID = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
//		String order = Arrays.asList(orderID.split(" ")).get(1);
//		System.out.println(order);
		
	}

}
