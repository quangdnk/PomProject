package testcases;

import base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

import java.util.List;

public class BrowserTest extends BaseSetup {
    private WebDriver driver;
    public HomePage homePage;

    @Parameters({"browserType", "appURL"})
    @BeforeClass
    public void setup(String browserType, String appURL) {
        driver = setDriver(browserType, appURL);
    }

    @Test(priority = 1)
    public void checkName() throws Exception {
        homePage = new HomePage(driver);
        homePage.showBrowser(driver);
        boolean check = false;
        int page = 3;
//        SoftAssert softassert = new SoftAssert();
        while (!check) {
            WebElement link = driver.findElement(By.xpath("//*[@id=\"pills-all-course\"]/div[@class='Pagination']/nav/ul/li[" + page + "]"));
            link.click();
            List<WebElement> allTitle = driver.findElements(By.className("title"));
            for (var i = 0; i < allTitle.stream().count(); i++) {
                if (allTitle.get(i).getText().equals("Blockchain Developer Certification Course")) {
                    Assert.assertTrue(allTitle.get(i).getText().equals("Blockchain Developer Certification Course"));
//                    softassert.assertTrue(allTitle.get(i).getText().contains("Blockchain Developer Certification Course"));
                    check = true;
                    break;
                }
            }
            page++;
        }
//        softassert.assertAll();
    }
}
