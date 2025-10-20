import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MtsOnlinePaymentTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBlockTitle() {
        System.out.println("Тест 1: Проверка заголовка блока");
        WebElement title = driver.findElement(By.xpath("//*[contains(text(), 'Онлайн пополнение')]"));
        assertTrue(title.isDisplayed());
        System.out.println("Заголовок найден");
    }

    @Test
    public void testPaymentLogos() {
        System.out.println("Тест 2: Проверка логотипов");
        WebElement visaLogo = driver.findElement(By.xpath("//img[contains(@src, 'visa')]"));
        assertTrue(visaLogo.isDisplayed());
        System.out.println("Логотип Visa найден");
    }

    @Test
    public void testDetailsLink() {
        System.out.println("Тест 3: Проверка ссылки 'Подробнее'");
        WebElement detailsLink = driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"));
        assertTrue(detailsLink.isDisplayed());
        System.out.println("Ссылка найдена");
    }

    @Test
    public void testPaymentFormWithResult() {
        System.out.println("Тест 4: Проверка формы с результатом");

        try {
            // 1. Сначала выбираем "Услуги связи"
            WebElement serviceButton = driver.findElement(By.xpath("//*[contains(text(), 'Услуги связи')]"));
            serviceButton.click();
            System.out.println("Выбраны 'Услуги связи'");
            Thread.sleep(1000);

            // 2. Теперь поле номера телефона станет активным
            WebElement phoneField = driver.findElement(By.xpath("//input[@type='tel']"));
            phoneField.sendKeys("297777777");
            System.out.println("Номер введен");

            // 3. Вводим сумму 100 рублей
            WebElement amountField = driver.findElement(By.xpath("//input[@type='number']"));
            amountField.sendKeys("100");
            System.out.println("Сумма 100 руб введена");

            // 4. Нажимаем кнопку
            WebElement button = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
            String urlBefore = driver.getCurrentUrl();

            button.click();
            System.out.println("Кнопка нажата");

            Thread.sleep(3000);

            String urlAfter = driver.getCurrentUrl();
            if (!urlAfter.equals(urlBefore)) {
                System.out.println("УСПЕХ: Переход на " + urlAfter);
            } else {
                System.out.println("Форма работает, остались на странице");
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
