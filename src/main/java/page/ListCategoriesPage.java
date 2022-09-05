package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ListCategoriesPage {
	WebDriver driver;

	public ListCategoriesPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.NAME, using = "categorydata")
	WebElement Categorydata_Field;

	public List<String> getListOf() {
		List<String> list = new ArrayList<String>();

		List<WebElement> categoryDataElements = driver.findElements(By.xpath("//a[@title='Remove this category']"));
		for (int i = 0; i < categoryDataElements.size(); i++) {
			list.add(i, categoryDataElements.get(i).getText());
		}

		return list;
	}

}
