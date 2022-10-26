import org.testng.annotations.Test;

public class LoginTestRunner extends Setup{
    @Test
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage.doLogin();
    }
}
