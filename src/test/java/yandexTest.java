import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.concurrent.TimeUnit;

@Title("Тестовое задание")
public class yandexTest {

    private WebDriver driver;

    @Step("Открытие ссылок")
    private void openPage(String s) {
        OpenPage openPage = new OpenPage(driver);
        openPage.checkLink(s);
    }

    @Step("Поиск по фильтрам: цена от \"{0}\" и до \"{1}\", компании: \"{2}\", \"{3}\"")
    private void searching(String fromprice, String toprice, String company1, String company2) throws InterruptedException {
        OpenPage openPage = new OpenPage(driver);
        Filter filter = new Filter(driver);
        filter.fromSetPrice(fromprice);
        filter.toSetPrice(toprice);
        openPage.checkLink("Показать всё");
        filter.setCompany(company1);
        filter.setCompany(company2);
    }

    @Step("Проверка элементов списка")
    private void check() {
        Check check = new Check(driver);
        check.getList();
    }

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void exit() {
        driver.quit();
    }

    @Test
    @Title("Тест Яндекс 1")
    public void testYandexTask() throws InterruptedException {
        OpenPage openPage = new OpenPage(driver);
        driver.get("https://market.yandex.ru");
        openPage.checkLink("Маркет");
        openPage.moveTo("Компьютерная техника");
        openPage.checkLink("Компьютеры");
        openPage.checkLink("Ноутбуки");
        searching("0","30000", "HP", "Lenovo");
        check();
    }

    @Test
    @Title("Тест Яндекс 2")
    public void testYandexTask2() throws InterruptedException {
        OpenPage openPage = new OpenPage(driver);
        driver.get("https://market.yandex.ru");
        openPage.checkLink("Маркет");
        openPage.moveTo("Компьютерная техника");
        openPage.checkLink("Компьютеры");
        openPage.checkLink("Планшеты");
        searching("20000","25000", "Acer", "DELL");
        check();
    }


}