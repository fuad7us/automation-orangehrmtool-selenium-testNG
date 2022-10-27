import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTestRunner extends Setup{
    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Test(priority = 1)
    public void doLogin(){
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage.doLogin("admin", "admin123");
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urlExpected));
    }

    @Test(priority = 2)
    public void ifProfileImageExists(){
        WebElement imgProfile = loginPage.imgProfile;
        Assert.assertTrue(imgProfile.isDisplayed());
    }

    @Test(priority = 3)
    public void selectEmployeeStatus(){
        List<WebElement> dropDowns = driver.findElements(By.className("oxd-select-text-input"));
        dropDowns.get(0).click();
        for (int i = 0; i < 4; i++) {
            dropDowns.get(0).sendKeys((Keys.ARROW_DOWN));
        }
        dropDowns.get(0).sendKeys((Keys.ENTER));
        driver.findElement(By.cssSelector("[type=submit]")).click();
    }

    @Test(priority = 4)
    public void getEmployeeStatus(){
        Utils.scrollDown(driver);

        WebElement table = driver.findElement(By.className("oxd-table-body"));
        List<WebElement> allRows = table.findElements(By.cssSelector("[role=row]"));

        for (WebElement row: allRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("[role=cell]"));
            Assert.assertTrue(cells.get(5).getText().contains("Full-Time Probation"));
        }
    }

    @Test(priority = 5)
    public void doLogout(){
        dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileImg.click();
        dashboardPage.linkLogout.click();
        //driver.findElement(By.partialLinkText("Logout")).click();
    }
}
