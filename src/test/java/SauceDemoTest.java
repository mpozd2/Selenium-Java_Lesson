import com.saucdemo.page_object.CartPage;
import com.saucdemo.page_object.HeaderPage;
import com.saucdemo.page_object.InventoryPage;
import com.saucdemo.page_object.LoginPage;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class SauceDemoTest {

    ChromeDriver driver;// обявляем драйвер который принадлежит класу ChromeDriver
    LoginPage loginPage; //создаем и обьявляем страницу
    InventoryPage inventoryPage; // создаем и обьявляем страницу
    HeaderPage headerPage;
    CartPage cartPage;

    Configurations configs;
    Configuration config;

    //TEST CASE #1 User open page and login
    @BeforeMethod
    public void setUp() throws ConfigurationException {
        driver = new ChromeDriver(); //иницилизируем
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //wait 10 s
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver); // проиницилизировали страницу, передали туда Драйвер (создали обьект, чтоб использовать его в тестах
        headerPage = new HeaderPage(driver);
        cartPage =  new CartPage(driver);

        configs = new Configurations();
        config = configs.properties("config.properties");


        //driver.get("https://www.saucedemo.com");
        driver.get(config.getString("web.url"));

    }

    @Test
    public void SauceDemoLoginTest() {
        // driver = new ChromeDriver(); //иницилизируем драйвер в первом тесте
        //driver.get("https://www.saucedemo.com");//open page

       /* By.id/By.name - locators
        driver.findElement(By.id("user-name")).sendKeys("standard_user");//enter user name on field (By.id)
        driver.findElement(By.name("password")).sendKeys("secret_sauce");//enter password (By.name)

        //XPath
        // Example : //tagname[@attribute='value']; Click on Login button using by XPath as locator
        driver.findElement(By.xpath("//input[@data-test=\"login-button\"]")).click();
       */
        //Page Object
        //config.getString("username");
        //loginPage.authorize("standard_user","secret_sauce");

        loginPage.authorize(config.getString("username"), config.getString("password"));

        //Expected Result  - compare
        // driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        // driver.close();
        // driver.quit();

   }

    @Test
    public void sauceDemoAddItemToCartTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        inventoryPage.selectItemByName("Backpack");
        inventoryPage.selectItemByName("Bike Light");
        //Assert.assertEquals("1", headerPage.getShoppingCarBadge().getText());
        assertThat(headerPage.getShoppingCarBadge().getText()).isEqualTo("1");

//        headerPage.getShoppingCartLink().click();
//        assertThat(cartPage.getCartItems().size()).isEqualTo(2);
//        assertThat(cartPage.getCartItems().get(0).getText()).contains("Backpack");
//        assertThat(cartPage.getCartItems().get(1).getText()).contains("Bike Light");

//        assertThat(cartPage.getCartItems())
//                .extracting(WebElement::getText)
//                .anyMatch(text -> text.contains("Backpack"));
//        assertThat(cartPage.getCartItems())
//                .extracting(WebElement::getText)
//                .anyMatch(text -> text.contains("Bike Light"));



        //cartPage.getCartItems();

       System.out.println("Checking Shopping cart DONE");


    }

    //Closing Chrome windows after ending test with any result - pass /failed
    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();

    }
}
