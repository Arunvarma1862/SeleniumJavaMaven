package com.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DyanamicTable {

	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://demo.opencart.com/admin/index.php?route=common/login");
		
		driver.manage().window().maximize();
		
		// Login
		
		
	  WebElement username = driver.findElement(By.id("input-username"));
	  username.sendKeys("demo");
	
	  WebElement passsword=   driver.findElement(By.id("input-password"));
	  passsword.sendKeys("demo");
	  
	  
	  driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	  
	  
	  driver.findElement(By.xpath("//button[@class='btn-close']")).click();
	  
	  
	  
	  // sales- orders
	  
	   driver .findElement(By.xpath("//a[normalize-space()='Sales']")).click();
	  
	   driver .findElement(By.xpath("//a[normalize-space()='Orders']")).click();
	   
	  
	   
	   // Tables
	   
	   // 1. Find Totals number of page in Table
	   
	String text=   driver.findElement(By.xpath("//div[text()='Showing 1 to 2 of 2 (1 Pages)']")).getText();
	System.out.println(text);
	
	int total_pages=   Integer.valueOf(text.substring(text.indexOf("(")+1,text.indexOf("Pages")-1));
	System.out.println("Total pages:  "+total_pages);
	
	
	// 2. Find how many rows exits in each Page
	
	
	for (int p = 1; p <=total_pages; p++)
	{
		WebElement active_page = driver.findElement(By.xpath("//u'[@class='pagination']//li//span"));
		System.out.println("Active Page: "+active_page.getText() );
		active_page.click();
		
		int rows=driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr")).size();
		System.out.println("number of rows: "+rows);
		
	String PageNo=	Integer.toString(p+1);
	driver.findElement(By.xpath("//ul[@class='pagination']//li//a[text()='"+PageNo+"']")).click();	
		
	}
	
	int rows=driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr")).size();
	System.out.println("number of rows: "+rows);
	
	
	// 3. Read all the rows from each page
	
	for (int r = 1; r <=rows; r++) {
		
	String orderId=	driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+r+"]//td[2]")).getText();
	String customer=	driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+r+"]//td[4]")).getText();
	String status=	driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+r+"]//td[5]")).getText();
	
	//4. print only pending status
	
	if(status.equals("Pending")) 
	{
		System.out.println(orderId+"   "+customer+"  "+status);	
	}
	
	
	}
	
	
	driver.quit();
	
	
	}

}
