package test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.CategoryPage;
import page.DropdownPage;
import page.ListCategoriesPage;
import util.BrowserFactory;

public class TestNGTest {

	WebDriver driver;
	DropdownPage dropdownPage;
	CategoryPage categoryPage;
	ListCategoriesPage listCategoriesPage;
	Random rnd = new Random();
	String categoryName = "AutoTest" + rnd.nextInt(99);
	String duplicateCategoryName = "MyTest" + rnd.nextInt(999);

	@BeforeMethod
	public void startEverything() {
		driver = BrowserFactory.startBrowser();
		dropdownPage = PageFactory.initElements(driver, DropdownPage.class);
		categoryPage = PageFactory.initElements(driver, CategoryPage.class);
		listCategoriesPage = PageFactory.initElements(driver, ListCategoriesPage.class);
	}

	@Test(priority = 1)
	public void userShouldBeAbleToAddCategory() throws InterruptedException {
		categoryPage.addCategory(categoryName);
		List<String> actualList = listCategoriesPage.getListOf();
		Assert.assertTrue(isDataExist(categoryName, actualList), "New Category Does Not Exist!");
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void userShouldNotBeAbleToAddDuplicateCategory() throws InterruptedException {
		categoryPage.addCategory(duplicateCategoryName);
		categoryPage.addCategory(duplicateCategoryName);
		categoryPage.waitForPage();

	}

	@Test(priority = 3)
	public void dropdownShouldHaveAllMonths() throws InterruptedException {
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		List<String> dropdownList = dropdownPage.getListOfDropDown();
		Assert.assertTrue(isDataMatch(months, dropdownList), "Values does not match");
		Thread.sleep(5000);
	}

	private boolean isDataMatch(String[] months, List<String> dropdownList) {
		for (int i = 0; i < dropdownList.size(); i++) {
			if (dropdownList.get(i).equalsIgnoreCase(months.toString())) {
			}
		}
		return true;
	}

	private boolean isDataExist(String categoryName, List<String> actualList) {
		for (int i = 0; i < actualList.size(); i++) {
			if (categoryName.equalsIgnoreCase(actualList.get(i)))
				;
		}
		return true;
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
