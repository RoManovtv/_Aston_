import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class OnlinePaymentTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        sleep(3000);

        // Закрываем куки если есть
        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Принять')]")).click();
            sleep(1000);
        } catch (Exception e) {
            // Куки уже закрыты или нет окна
        }
    }

    // Тест 1: Проверка надписей в полях всех вариантов оплаты
    @Test
    public void testPlaceholdersForAllServices() {
        System.out.println("=== Тест 1: Проверка надписей в полях всех услуг ===");

        try {
            // Услуги связи
            click("//div[contains(text(), 'Услуги связи')]");
            String mobilePlaceholder = getPhonePlaceholder();
            System.out.println("Услуги связи: " + mobilePlaceholder);
            assertTrue(mobilePlaceholder.contains("номер") || mobilePlaceholder.contains("телефон"));

            // Домашний интернет
            click("//div[contains(text(), 'Домашний интернет')]");
            String internetPlaceholder = getPhonePlaceholder();
            System.out.println("Домашний интернет: " + internetPlaceholder);
            assertNotNull(internetPlaceholder);

            // Рассрочка
            click("//div[contains(text(), 'Рассрочка')]");
            String installmentPlaceholder = getPhonePlaceholder();
            System.out.println("Рассрочка: " + installmentPlaceholder);
            assertNotNull(installmentPlaceholder);

            // Задолженность
            click("//div[contains(text(), 'Задолженность')]");
            String debtPlaceholder = getPhonePlaceholder();
            System.out.println("Задолженность: " + debtPlaceholder);
            assertNotNull(debtPlaceholder);

            System.out.println("✓ Тест 1 завершен - все надписи проверены");

        } catch (Exception e) {
            System.out.println("Ошибка в тесте 1: " + e.getMessage());
        }
    }

    // Тест 2: Полная проверка оплаты услуг связи
    @Test
    public void testCompleteMobilePaymentWithVerification() {
        System.out.println("=== Тест 2: Полная проверка оплаты услуг связи ===");

        try {
            // 1. Выбираем "Услуги связи"
            click("//div[contains(text(), 'Услуги связи')]");
            System.out.println("✓ Услуги связи выбраны");

            // 2. Заполняем поля
            WebElement phoneInput = driver.findElement(By.xpath("//input[@type='tel']"));
            phoneInput.sendKeys("297777777");

            WebElement amountInput = driver.findElement(By.xpath("//input[@type='number']"));
            amountInput.sendKeys("100");

            System.out.println("✓ Форма заполнена: номер 297777777, сумма 100 руб");

            // 3. Нажимаем "Продолжить"
            driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]")).click();
            sleep(3000);
            System.out.println("✓ Кнопка 'Продолжить' нажата");

            // 4. Проверяем окно оплаты
            System.out.println("=== Проверка окна оплаты ===");

            // Проверяем номер телефона
            String displayedPhone = driver.findElement(By.xpath("//*[contains(text(), '297777777')]")).getText();
            System.out.println("Отображаемый номер: " + displayedPhone);
            assertTrue(displayedPhone.contains("297777777"), "Номер отображается некорректно");

            // Проверяем сумму
            String displayedAmount = driver.findElement(By.xpath("//*[contains(text(), '100')]")).getText();
            System.out.println("Отображаемая сумма: " + displayedAmount);
            assertTrue(displayedAmount.contains("100"), "Сумма отображается некорректно");

            // Проверяем сумму на кнопке оплаты
            String payButtonText = driver.findElement(By.xpath("//button[contains(text(), 'Оплатить')]")).getText();
            System.out.println("Текст кнопки оплаты: " + payButtonText);
            assertTrue(payButtonText.contains("100"), "Сумма на кнопке отображается некорректно");

            // 5. Проверяем надписи в полях карты
            System.out.println("=== Проверка полей карты ===");

            String cardNumberPlaceholder = driver.findElement(By.xpath("//input[@placeholder='Номер карты']")).getAttribute("placeholder");
            System.out.println("Поле номера карты: " + cardNumberPlaceholder);
            assertEquals("Номер карты", cardNumberPlaceholder);

            String cardExpiryPlaceholder = driver.findElement(By.xpath("//input[contains(@placeholder, 'Срок')]")).getAttribute("placeholder");
            System.out.println("Поле срока карты: " + cardExpiryPlaceholder);
            assertNotNull(cardExpiryPlaceholder);

            String cardCvvPlaceholder = driver.findElement(By.xpath("//input[contains(@placeholder, 'CVV')]")).getAttribute("placeholder");
            System.out.println("Поле CVV: " + cardCvvPlaceholder);
            assertNotNull(cardCvvPlaceholder);

            // 6. Проверяем иконки платежных систем
            System.out.println("=== Проверка иконок платежных систем ===");

            boolean hasVisa = driver.findElement(By.xpath("//img[contains(@src, 'visa')]")).isDisplayed();
            boolean hasMastercard = driver.findElement(By.xpath("//img[contains(@src, 'mastercard')]")).isDisplayed();

            System.out.println("Иконка Visa: " + (hasVisa ? "есть" : "нет"));
            System.out.println("Иконка Mastercard: " + (hasMastercard ? "есть" : "нет"));

            assertTrue(hasVisa, "Иконка Visa отсутствует");
            assertTrue(hasMastercard, "Иконка Mastercard отсутствует");

            System.out.println("✓ Тест 2 завершен - все проверки пройдены");

        } catch (Exception e) {
            System.out.println("Ошибка в тесте 2: " + e.getMessage());
        }
    }

    // Вспомогательные методы
    private void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
        sleep(1000);
    }

    private String getPhonePlaceholder() {
        return driver.findElement(By.xpath("//input[@type='tel']")).getAttribute("placeholder");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}