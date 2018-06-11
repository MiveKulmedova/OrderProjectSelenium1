package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Order {
	
	public static String randomCreditCard(int len,String type) {
		StringBuilder std=new StringBuilder();
		int rdigit;
	   for (int i = 0; i < len; i++) {
		   rdigit = (int) (Math.random() * (9 - 0)) + 0;
		   std.append(rdigit);
		
	}
	   return std.toString();
}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Asus\\Documents\\selenium dependencies\\drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(" http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();

		double randNumber = Math.random();
		int num1 = (int) (randNumber * 100);

		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder(6);
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String midname = sb.toString();

		// int rz = (int) (Math.random() * (99999 - 9999)) + 1;
		int k;
		do {
			double randNumber2 = Math.random();
			k = (int) (randNumber2 * 100000);
		} while (k <= 9999 || k > 99999);

		// select from drop down menu
		Select dropdown = new Select(
				driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_ddlProduct\"]")));
		Thread.sleep(2000);

		dropdown.selectByVisibleText("FamilyAlbum");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtQuantity\"]"))
				.sendKeys(Integer.toString(num1));
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtName\"]"))
				.sendKeys("John " + midname + " Smith");
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox2\"]")).sendKeys("123 Any st");
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox3\"]")).sendKeys("Anytown");
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox4\"]")).sendKeys("Virginia");
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox5\"]")).sendKeys(Integer.toString(k));

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
		}else {
			System.out.println("something wrong");
		}
		Thread.sleep(1000);
		driver.close();

	}

}
