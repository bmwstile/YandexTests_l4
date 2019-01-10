import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

class OpenPage {

    private WebDriver driver;

    OpenPage(WebDriver driver) { this.driver = driver; }

    @Step("Открываем страницу \"{0}\"")
    void checkLink(String s) {
        WebElement link =  driver.findElement(By.linkText(s));
        Assert.assertEquals("Error", s, link.getText());
        link.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Step("Навести мышкой \"{0}\"")
    void moveTo(String s) {
        WebElement link =  driver.findElement(By.linkText(s));
        new Actions(driver).moveToElement(driver.findElement(By.linkText(s))).perform();
        Assert.assertEquals("Error", s, link.getText());
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }


}
