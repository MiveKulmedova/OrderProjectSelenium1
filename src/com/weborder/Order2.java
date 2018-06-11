package com.weborder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Order2 {

	public static String randomString(int len) {

		char[] ch = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };

		char[] c = new char[len];
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}

		return new String(c);

	}

	public static String randomCreditCard(int len, String type) {
		StringBuilder std = new StringBuilder();
		int rdigit;
		for (int i = 0; i < len; i++) {
			rdigit = (int) (Math.random() * (9 - 0)) + 0;
			std.append(rdigit);

		}
		return std.toString();
	}
	/*
	 * Enter any card number. If you selected Visa, card number should start with 4.
	 * If you selected Master, card number should start with 5. If you selected
	 * American Express, card number should start with 3. New card number should be
	 * auto generated every time you run the test. Card numbers should be 16 digits
	 * for Visa and Master, 15 for American Express.
	 * 
	 * 
	 */

	public static int getRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Sibel/Documents/selenium dependencies/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_username\"]")).sendKeys("Tester");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_password\"]")).sendKeys("test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_login_button\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();
		Thread.sleep(2000);
		// select from drop down menu
		Select dropdown = new Select(
				driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_ddlProduct\"]")));
		Thread.sleep(2000);

		dropdown.selectByVisibleText("FamilyAlbum");
		Thread.sleep(2000);
		int r = (int) (Math.random() * (100 - 1)) + 1;
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtQuantity\"]"))
				.sendKeys(Integer.toString(r));
		Thread.sleep(2000);
		String randomMiddle = randomString(7);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtName\"]"))
				.sendKeys("John " + randomMiddle + " Smith");
		Thread.sleep(2000);

		// street
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox2\"]")).sendKeys("123 Any st");
		Thread.sleep(2000);

		// city
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox3\"]")).sendKeys("Anytown");
		Thread.sleep(2000);

		// state
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox4\"]")).sendKeys("Virginia");
		Thread.sleep(2000);
		// zip
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox5\"]")).sendKeys("20120");
		Thread.sleep(2000);

		// randomly select card type
		// *[@id="ctl00_MainContent_fmwOrder_cardList_0"]
		// *[@id="ctl00_MainContent_fmwOrder_cardList_1"]
		// *[@id="ctl00_MainContent_fmwOrder_cardList_2"]

		int r2 = (int) (Math.random() * (2 - 0)) + 0;
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_" + r2 + "\"]")).click();
		Thread.sleep(2000);

		// card Number
		String type = "";
		int len = 0;
		if (r2 == 0) {
			type = "visa";
			len = 15;
		} else if (r2 == 1) {
			type = "master";
			len = 15;
		} else if (r2 == 2) {
			type = "americanExpress";
			len = 14;
		}
		String cardnumber = randomCreditCard(len, type);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox6\"]")).sendKeys(cardnumber);
		Thread.sleep(2000);

		System.out.println(type + "    " + cardnumber);

		// expire

		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox1\"]")).sendKeys("04/19");
		Thread.sleep(2000);

		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_InsertButton\"]")).click();

		String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong"))
				.getText();
		String expected = "New order has been successfully added.";
		System.out.println(actual);

		if (expected.contains(actual)) {
			System.err.println("passed the test");
		} else {
			System.out.println("something wrong");
		}
		Thread.sleep(1000);
		driver.close();

	}
}