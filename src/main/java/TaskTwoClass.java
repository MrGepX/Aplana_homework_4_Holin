
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

final class TaskTwoClass {
    static void SecondTestingTask() {
        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        WebElement element;

        driver.navigate().to("http://www.sberbank.ru/ru/person");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div/div[4]/header/div/div/div/div[2]/div[3]/div/div/a"))));

        element = driver.findElement(By.className("hd-ft-region__title"));
        element.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@aria-label, 'Выбор региона')]"))));

        element = driver.findElement(By.xpath("//A[@class='kit-link kit-link_m hd-ft-region__city'][text()='Нижегородская область']"));
        element.click();

        try {
            Assert.assertEquals("Нижегородская область", driver.findElement(By.className("hd-ft-region__title")).getText());
        } catch (Exception e) {
            System.out.println("Неверно выбрана область " + e.getMessage());
        }

        WebElement webElement = driver.findElement(By.xpath("//FOOTER[@class='footer__subfooter']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", webElement);

        try {
            Assert.assertTrue(driver.findElements(By.xpath("//UL[@class='footer__social']")).size() != 0);
        } catch (AssertionError e) {
            System.out.println("Пропали значки социальных сетей - " + e.getMessage());
        }

        driver.close();
    }



}
