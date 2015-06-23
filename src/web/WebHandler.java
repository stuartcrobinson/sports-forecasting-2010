package web;


import main.Farmer;

import org.openqa.selenium.By;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebHandler {
	public Farmer f;	
	
	public FirefoxDriver d;
	public WebDriverWait wait;	
	
	public WebTasks t;
	public WebScraper s;

	public WebHandler(Farmer farmer) {
		f	 = farmer;
		
		d	 = new FirefoxDriver();		
		wait = new WebDriverWait(d, 15);		

		t 	 = new WebTasks(this);
		s 	 = new WebScraper(this);
	}

	public void quit(){
		d.close();
	}

	public ExpectedCondition<Boolean> cssExists(final String myCssSelector) {		
		ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return Boolean.valueOf(((RenderedWebElement) d.findElement(
						By.cssSelector(myCssSelector))).isDisplayed());
			}
		};
		return e;
	}
	
}
