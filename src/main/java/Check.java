import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Check {

    private WebDriver driver;

    Check (WebDriver driver) { this.driver = driver; }

    @Step("Проверка что элементов 48")
    void getList() {
        if(driver.findElements(By.xpath(".//div[@class='n-snippet-card2__title']/a")).size() > 0)
        {
            WebElement firstNote = driver.findElement(By.xpath(".//div[@class='n-snippet-card2__title']/a"));
            String firstNoteText = firstNote.getText();
            driver.findElement(By.xpath("//*[@id=\"header-search\"]")).sendKeys(firstNoteText);
            checkProducts(firstNoteText);
            List<WebElement> list = driver.findElements(By.className("n-snippet-card2__title"));
            assertThat(48, equalTo(list.size()));
        }
        else System.out.println("Не найдено моделей по заданным критериям поиска");

    }


    @Step("Проверка результата поиска по названию: \"{0}\"")
    private void checkProducts(String firstNoteText) {
        driver.findElement(By.xpath(".//span[text()='Найти']/..")).click();
        WebElement sameNote = driver.findElement(By.xpath(".//div[@class='n-snippet-card2__title']/a"));
        String sameNoteText = sameNote.getText();
        System.out.print(firstNoteText + " ");
        System.out.print(sameNoteText);
        assertThat(sameNoteText, equalTo(firstNoteText));
    }
}
