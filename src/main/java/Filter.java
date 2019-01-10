import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class Filter {

    private WebDriver driver;

    Filter(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
    }

    void fromSetPrice(String price) {
        WebElement element = driver.findElement(By.xpath(".//*[@id='glpricefrom']"));
        setPrice(price, element);
    }

    void toSetPrice(String price) {
        WebElement element = driver.findElement(By.xpath(".//*[@id='glpriceto']"));
        setPrice(price, element);
    }

    private void setPrice(String price, WebElement element) {
        element.sendKeys(price);
        String fromText = element.getAttribute("value");
        assertThat("ошибка в веденном значении:  "+fromText, price, equalTo(fromText));
    }


    void setCompany(String company) throws InterruptedException {

        //if(driver.findElements(By.xpath(String.format(".//div[@class='LhMupC0dLR']/span[contains(text(), '%s')]", company))).size() > 0)
        if(driver.findElement(By.xpath(String.format(".//input[@name='Производитель %s']", company))).isEnabled())
        {
            driver.findElement(By.xpath(String.format(".//div[@class='LhMupC0dLR']/span[contains(text(), '%s')]", company))).click();
            Thread.sleep(1000);
            System.out.println("Выбран производитель " + company);
        }
        else {
            driver.findElement(By.xpath(".//input[@id='7893318-suggester']")).sendKeys(company);
            if(driver.findElement(By.xpath(String.format(".//input[@name='Производитель %s']", company))).isEnabled())
            {
                driver.findElement(By.xpath(String.format(".//div[@class='LhMupC0dLR']/span[contains(text(), '%s')]", company))).click();
                Thread.sleep(1000);
            }
            else {
                System.out.println("Невозможно выбрать для поиска производителя " + company);
                Thread.sleep(1000);
            }
        }

    }

}
