package web;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class WebTasks {
	
	WebHandler w;
	
	public WebTasks(WebHandler webhandler){
		w = webhandler;
	}

	public String selectSsnFromDropdown(Integer ssn) throws Exception {

		w.d.get(w.f.m.lf.url_leaderboard());
		
		w.wait.until(w.cssExists(".left a"));
		Select ssnDropdown = new Select(w.d.findElement(By.id("ddlYear")));

		System.out.println("selecting option: " + w.f.m.lf.ssnDropdownText(ssn));
		ssnDropdown.selectByVisibleText(w.f.m.lf.ssnDropdownText(ssn));
		System.out.println("selected option: " + ssnDropdown.getFirstSelectedOption().getText());
		w.d.findElement(By.cssSelector("#dropDown .btn")).click();
		Thread.sleep(2000);
		return null;
	}

	public boolean pageContainsBets() {
		return (w.d.getPageSource().indexOf("No records for this period") == -1);
	}
}
