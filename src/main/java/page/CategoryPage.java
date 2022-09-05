package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
	WebDriver driver;

	public CategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.NAME, using = "categorydata")
	WebElement Categorydata_Field;
	@FindBy(how = How.CSS, using = "input[value='Add category']")
	WebElement Add_Category_Button;
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'The category you want to add already exists')]")
	WebElement Duplicate_Display_Element;

	public void addCategory(String categoryName) throws InterruptedException {
		Categorydata_Field.sendKeys(categoryName);
		Thread.sleep(2000);
		Add_Category_Button.click();
	}

	public void waitForPage() {
		waitForElement(Duplicate_Display_Element, driver);
	}

	public void waitForElement(WebElement element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public boolean isDuplicateMessageDisplayed() {
		try {
			waitForPage();
			return true;
		} catch (Exception e) {

		}
		return false;
	}
}
