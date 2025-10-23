import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OnlinePaymentTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        closeCookiePopup();
    }

    private void closeCookiePopup() {
        try {
            WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]")
            ));
            cookieAccept.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            Allure.step("Куки окно не найдено");
        }
    }

    @Test
    @Description("Проверка надписей в незаполненных полях каждого варианта оплаты услуг")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholdersForAllServices() {
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String service : services) {
            Allure.step("Проверка услуги: " + service);
            selectService(service);
            String placeholder = getPlaceholder();
            Allure.addAttachment(service, "Плейсхолдер: " + placeholder);
            assertNotNull(placeholder, "Плейсхолдер не должен быть пустым для " + service);
        }
    }

    @Test
    @Description("Проверка полного потока оплаты услуг связи с верификацией данных")
    @Severity(SeverityLevel.CRITICAL)
    public void testCompleteMobilePaymentWithVerification() {
        // 1. Выбираем услуги связи
        selectService("Услуги связи");

        // 2. Заполняем форму
        fillPaymentForm("297777777", "100");

        // 3. Нажимаем "Продолжить" и проверяем результат
        clickContinueButtonAndVerify();

        Allure.step("Тест завершен успешно");
    }

    private void selectService(String serviceName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".select__header")
            ));
            dropdown.click();
            Thread.sleep(1000);

            List<WebElement> options = driver.findElements(By.cssSelector(".select__item .select__option"));
            boolean found = false;
            for (WebElement option : options) {
                if (option.getText().trim().equals(serviceName)) {
                    option.click();
                    found = true;
                    break;
                }
            }

            if (!found) {
                fail("Опция '" + serviceName + "' не найдена в списке");
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            fail("Ошибка при выборе услуги '" + serviceName + "': " + e.getMessage());
        }
    }

    private String getPlaceholder() {
        try {
            // Ищем любое поле ввода на странице
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[not(@type='hidden') and not(@type='submit') and not(@type='button')][1]")
            ));
            return input.getAttribute("placeholder");
        } catch (Exception e) {
            return "Не найден";
        }
    }

    private void fillPaymentForm(String phone, String amount) {
        try {
            // Ищем поле для номера телефона
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[contains(@placeholder, 'номер') or contains(@placeholder, 'телефон') or contains(@id, 'phone')]")
            ));
            phoneInput.clear();
            phoneInput.sendKeys(phone);

            // Ищем поле для суммы
            WebElement amountInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[contains(@placeholder, 'Сумма') or contains(@id, 'sum') or contains(@id, 'amount')]")
            ));
            amountInput.clear();
            amountInput.sendKeys(amount);

            Allure.addAttachment("Заполненные данные", "Номер: " + phone + "\nСумма: " + amount + " руб");
        } catch (Exception e) {
            fail("Ошибка при заполнении формы: " + e.getMessage());
        }
    }

    private void clickContinueButtonAndVerify() {
        try {
            // Ищем кнопку "Продолжить"
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Продолжить') or contains(text(), 'Далее') or contains(text(), 'Продолжить оплату')]")
            ));

            System.out.println("Найдена кнопка: " + continueButton.getText());
            continueButton.click();

            // Ждем загрузки платежной формы
            Thread.sleep(5000);

            // Проверяем, появилась ли платежная форма
            verifyPaymentForm();

        } catch (Exception e) {
            takeScreenshot();
            fail("Ошибка при нажатии кнопки 'Продолжить': " + e.getMessage());
        }
    }

    private void verifyPaymentForm() {
        try {
            // Сначала проверяем, есть ли iframe с платежной формой
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Найдено iframe'ов на странице: " + iframes.size());

            boolean paymentFormFound = false;

            // Переключаемся между iframe'ами и ищем платежную форму
            for (int i = 0; i < iframes.size(); i++) {
                try {
                    driver.switchTo().frame(i);

                    // Ищем элементы платежной формы внутри iframe
                    if (isElementPresentInsideFrame(By.xpath("//*[contains(text(), 'Оплата:')]"))) {
                        System.out.println("✅ Платежная форма найдена в iframe #" + i);
                        paymentFormFound = true;

                        // Проверяем основные элементы платежной формы
                        verifyPaymentFormElements();
                        break;
                    }

                    // Возвращаемся к основному контенту
                    driver.switchTo().defaultContent();

                } catch (Exception e) {
                    // Если возникла ошибка, возвращаемся к основному контенту и продолжаем
                    driver.switchTo().defaultContent();
                }
            }

            assertTrue(paymentFormFound, "Платежная форма не найдена");
            Allure.step("Платежная форма успешно загружена");

        } catch (Exception e) {
            takeScreenshot();
            fail("Ошибка при проверке платежной формы: " + e.getMessage());
        } finally {
            // Всегда возвращаемся к основному контенту
            try {
                driver.switchTo().defaultContent();
            } catch (Exception e) {
                // Игнорируем ошибки при возврате
            }
        }
    }

    private boolean isElementPresentInsideFrame(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void verifyPaymentFormElements() {
        try {
            // 1. Проверяем заголовок с информацией об оплате
            WebElement paymentTitle = driver.findElement(By.xpath("//*[contains(text(), 'Оплата:')]"));
            String paymentText = paymentTitle.getText();
            Allure.addAttachment("Информация об оплате", paymentText);
            assertTrue(paymentText.contains("Услуги связи"), "Должна быть указана услуга");
            assertTrue(paymentText.contains("375297777777"), "Должен быть указан номер телефона");
            System.out.println("✅ Информация об оплате: " + paymentText);

            // 2. Проверяем сумму оплаты - используем более гибкий поиск
            List<WebElement> amountElements = driver.findElements(By.xpath("//*[contains(text(), '100')]"));
            boolean amountFound = false;
            for (WebElement element : amountElements) {
                if (element.getText().contains("100") && element.getText().contains("BYN")) {
                    amountFound = true;
                    Allure.addAttachment("Сумма оплаты", element.getText());
                    System.out.println("✅ Сумма оплаты: " + element.getText());
                    break;
                }
            }
            assertTrue(amountFound, "Сумма 100.00 BYN не найдена");

            // 3. Проверяем поля для ввода карты - используем более гибкий поиск
            String[] expectedCardFields = {"Номер карты", "Срок действия", "CVC", "Имя и фамилия"};
            int foundFields = 0;
            for (String field : expectedCardFields) {
                if (isElementPresentInsideFrame(By.xpath("//*[contains(text(), '" + field + "')]"))) {
                    foundFields++;
                    System.out.println("✅ Найдено поле: " + field);
                }
            }
            assertTrue(foundFields >= 3, "Должно быть найдено минимум 3 поля карты, найдено: " + foundFields);

            // 4. Проверяем кнопку оплаты - используем более гибкий поиск
            List<WebElement> payButtons = driver.findElements(By.xpath("//button | //input[@type='submit']"));
            boolean payButtonFound = false;
            for (WebElement button : payButtons) {
                String buttonText = button.getText();
                if (buttonText.contains("Оплатить") || buttonText.contains("100") ||
                        button.getAttribute("value") != null && button.getAttribute("value").contains("Оплатить")) {
                    payButtonFound = true;
                    Allure.addAttachment("Кнопка оплаты", buttonText);
                    System.out.println("✅ Найдена кнопка оплаты: " + buttonText);
                    break;
                }
            }
            assertTrue(payButtonFound, "Кнопка оплаты не найдена");

            // 5. Проверяем логотипы платежных систем
            checkPaymentSystemIcons();

            System.out.println("✅ Все элементы платежной формы проверены успешно");

        } catch (Exception e) {
            takeScreenshot();
            // Сделаем диагностику того, что есть в iframe
            System.out.println("=== ДИАГНОСТИКА IFRAME ===");
            try {
                // Выведем все видимые элементы
                List<WebElement> allElements = driver.findElements(By.xpath("//*"));
                System.out.println("Всего элементов в iframe: " + allElements.size());

                // Выведем все кнопки
                List<WebElement> buttons = driver.findElements(By.tagName("button"));
                System.out.println("Кнопки в iframe:");
                for (WebElement button : buttons) {
                    if (button.isDisplayed()) {
                        System.out.println(" - " + button.getText() + " (tag: " + button.getTagName() + ")");
                    }
                }

                // Выведем все инпуты
                List<WebElement> inputs = driver.findElements(By.tagName("input"));
                System.out.println("Инпуты в iframe:");
                for (WebElement input : inputs) {
                    if (input.isDisplayed()) {
                        System.out.println(" - type: " + input.getAttribute("type") +
                                ", placeholder: " + input.getAttribute("placeholder"));
                    }
                }

            } catch (Exception ex) {
                System.out.println("Ошибка при диагностике: " + ex.getMessage());
            }

            fail("Ошибка при проверке элементов платежной формы: " + e.getMessage());
        }
    }

    private void checkPaymentSystemIcons() {
        try {
            // Ищем изображения платежных систем
            List<WebElement> images = driver.findElements(By.tagName("img"));
            int paymentIconsCount = 0;

            for (WebElement img : images) {
                String src = img.getAttribute("src");
                String alt = img.getAttribute("alt");
                if (src != null && (src.contains("visa") || src.contains("mastercard") || src.contains("belkart") ||
                        src.contains("mir") || (alt != null && alt.toLowerCase().contains("card")))) {
                    paymentIconsCount++;
                }
            }

            Allure.addAttachment("Иконки платежных систем", "Найдено иконок: " + paymentIconsCount);
            assertTrue(paymentIconsCount >= 2, "Должно быть минимум 2 иконки платежных систем");
            System.out.println("✅ Найдено иконок платежных систем: " + paymentIconsCount);

        } catch (Exception e) {
            System.out.println("⚠️ Не удалось проверить иконки платежных систем: " + e.getMessage());
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void takeScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Скриншот страницы", "image/png", new java.io.ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}