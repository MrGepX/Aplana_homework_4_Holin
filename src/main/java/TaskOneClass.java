import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

final class TaskOneClass {
     static void TestingOne() {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        WebElement element;

        driver.navigate().to("http://www.rgs.ru");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(TaskOneData.menuInsurance)))); //Ждем загрузки пункта меню - страхование
        element = driver.findElement(By.xpath(TaskOneData.menuInsurance));
        element.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(TaskOneData.menuDmsInsurance))));
        element = driver.findElement(By.xpath(TaskOneData.menuDmsInsurance)); //Выбираем категорию
        element.click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(TaskOneData.sendStatement))));

        try {
            Assert.assertEquals("Добровольное медицинское страхование", driver.getTitle());
        } catch (ComparisonFailure e) {
            System.out.println("Ожидаемый заголовок не совпадает. " + e.getMessage());
        }

        element = driver.findElement(By.xpath(TaskOneData.sendStatement));
        element.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(TaskOneData.statementFooter))));

        try {
            Assert.assertTrue(driver.findElements(By.xpath(TaskOneData.titleChecker)).size() != 0);
        } catch (ComparisonFailure e) {
            System.out.println("Неверная страница, заявку отправить невозможно" + e.getMessage());
        }


        //Заполняем данные
        element = driver.findElement(By.name(TaskOneData.secondNameName));
        element.sendKeys(TaskOneData.secondName);

        element = driver.findElement(By.name(TaskOneData.fistNameName));
        element.sendKeys(TaskOneData.firstName);

        element = driver.findElement(By.name(TaskOneData.middleNameName));
        element.sendKeys(TaskOneData.middleName);

        element = driver.findElement(By.xpath(TaskOneData.regionPath));
        element.click();

        element = driver.findElement(By.xpath(TaskOneData.phonePath));
        element.click();
        element.sendKeys(TaskOneData.phone);

        element = driver.findElement(By.name(TaskOneData.mailName));
        element.sendKeys(TaskOneData.mail);

        element = driver.findElement(By.name(TaskOneData.preferDateName));
        element.sendKeys(TaskOneData.preferDate);

        element = driver.findElement(By.name(TaskOneData.commentsName));
        element.sendKeys(TaskOneData.comments);

        element = driver.findElement(By.className(TaskOneData.checkboxClassName));
        element.click();

        //Проверяем введенные данные
        try {
           Assert.assertEquals(driver.findElement(By.name(TaskOneData.fistNameName)).getAttribute("value"), TaskOneData.firstName);
        } catch (Exception e) {
           System.out.println("Неверно ввелось имя" + e.getMessage());
        }

        try {
           Assert.assertEquals(driver.findElement(By.name(TaskOneData.secondNameName)).getAttribute("value"), TaskOneData.secondName);
        } catch (Exception e) {
           System.out.println("Неверно ввелась фамилия" + e.getMessage());
        }

        try {
           Assert.assertEquals(driver.findElement(By.name(TaskOneData.middleNameName)).getAttribute("value"), TaskOneData.middleName);
        } catch (Exception e) {
           System.out.println("Неверно ввелось отчество" + e.getMessage());
        }

        Select select = new Select(driver.findElement(By.name(TaskOneData.regionSelecterName)));
        try {
           Assert.assertEquals(select.getFirstSelectedOption().getText(), TaskOneData.regionName);
        } catch (AssertionError e) {
           System.out.println(e.getMessage());
        }

        System.out.println(select.getFirstSelectedOption().getText());

        try {
           Assert.assertEquals(driver.findElement(By.xpath(TaskOneData.phonePath)).getAttribute("value"), phoneConverter(TaskOneData.phone));
        } catch (AssertionError e) {
           System.out.println("Неверно ввелся телефон - " + e.getMessage());
        }

        try {
           Assert.assertEquals(driver.findElement(By.name(TaskOneData.mailName)).getAttribute("value"), TaskOneData.mail);
        } catch (Exception e) {
           System.out.println("Неверно ввелась почта" + e.getMessage());
        }

        try {
           Assert.assertEquals(driver.findElement(By.name(TaskOneData.preferDateName)).getAttribute("value"), TaskOneData.preferDate);
        } catch (Exception e) {
           System.out.println("Неверно ввелась дата" + e.getMessage());
        }

        try {
           Assert.assertEquals(driver.findElement(By.name(TaskOneData.commentsName)).getAttribute("value"), TaskOneData.comments);
        } catch (Exception e) {
           System.out.println("Неверно ввелся комментарий" + e.getMessage());
        }

        try {
           Assert.assertEquals(driver.findElement(By.className(TaskOneData.checkboxClassName)).isEnabled(), true);
        } catch (Exception e) {
           System.out.println("Не установилась галочка" + e.getMessage());
        }

        element = driver.findElement(By.id(TaskOneData.buttonId));
        element.click();

        // Проверка верности почты
        try {
             Assert.assertEquals(0, driver.findElements(By.className("validation-error-text")).size());
        } catch (AssertionError e) {
             System.out.println("Увы, с такими данными зарегистрироваться нельзя, введите другую почту");
        }
     }

      static String phoneConverter(String phone) { //Пришлось добавить обработку, т.к. после ввода меняется формат введеного телефона
        char[] data = phone.toCharArray();
        return "+7 (" +
                data[0] + data[1] + data[2] +
                ") " +
                data[3] + data[4] + data[5] +
                "-" +
                data[6] + data[7] +
                "-" +
                data[8] + data[9];
     }
}
