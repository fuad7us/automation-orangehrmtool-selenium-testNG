import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunner extends Setup{
    @Test
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage.doLogin();
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urlExpected));
    }

    @Test
    public void ifProfileImageExists(){
        boolean imageDisplayStatus = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(imageDisplayStatus);
    }
}
