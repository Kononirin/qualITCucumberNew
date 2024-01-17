package qualITCucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qualITCucumber.data.Data;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class MyStepdefs {

    ChromeOptions options = new ChromeOptions();
    WebDriver driver = new ChromeDriver(options);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

    @Дано("открыт стенд по адресу локалхост")
    public void открыт_стенд_по_адресу() {
        driver.get("http://localhost:8080/");
    }

    @Когда("Пользователь нажимает на выпадающий список Песочница")
    public void clickDropdownSandbox() {
        WebElement dropdownSandbox = driver.findElement(By.id("navbarDropdown"));
        dropdownSandbox.click();
    }

    @И("Пользователь выбирает в выпадающем списке пункт Товары")
    public void clickPointFood() {
        WebElement pointFood = driver.findElement(By.xpath("//a[@href='/food']"));
        pointFood.click();
    }

    @И("Пользователь нажимает на кнопку Добавить")
    public void clickButtonAdd() {
        WebElement buttonAdd = driver.findElement(By.xpath("//button[text()='Добавить']"));
        buttonAdd.click();
    }

    @И("Пользователь вводит наименование {string}")
    public void typeName(String str) {
        WebElement inputName = driver.findElement(By.id("name"));
        wait.until(ExpectedConditions.elementToBeClickable(inputName));
        inputName.sendKeys(Data.fruit);
    }

    @И("Пользователь выбирает в выпадающем списке тип Фрукт")
    public void clickDropdownTypeFruit() {
        WebElement dropdownType = driver.findElement(By.id("type"));
        dropdownType.click();
        WebElement dropdownPointFruitType = driver.findElement(By.xpath("//option[@value='FRUIT']"));
        dropdownPointFruitType.click();
    }

    @И("Пользователь выбирает чек-бокс Экзотический")
    public void clickCheckBoxExotic() {
        WebElement checkBoxExotic = driver.findElement(By.id("exotic"));
        checkBoxExotic.click();
    }

    @И("Пользователь нажимает кнопку Сохранить")
    public void clickButtonSave() {
        WebElement buttonSave = driver.findElement(By.id("save"));
        buttonSave.click();
    }


    @Тогда("Проверяем, что в последней строке таблицы Товары отображаются введенные данные")
    public void assertResult() {


        List<String> listWithFruitCyrillicAndLatinSymbols = Arrays.asList(
                "5", "ЭкЗоТиЧеСкИй FrUiT!", "Фрукт", "true");

        List<WebElement> lastRowInTableOfFoods = driver.findElements(By.xpath(
                "//tr[5]/*"));

        wait.until(ExpectedConditions.elementToBeClickable(lastRowInTableOfFoods.get(0)));

        List<String> lastRowInTableOfFoodsStr = new ArrayList<>();

        for (WebElement element : lastRowInTableOfFoods) {
            lastRowInTableOfFoodsStr.add(element.getText());
        }

        Assertions.assertEquals(
                listWithFruitCyrillicAndLatinSymbols,
                lastRowInTableOfFoodsStr,
                "Не совпадают элементы в таблице товаров");
    }

    @И("Пользователь выбирает в выпадающем списке пункт Сброс данных")
    public void clickPointDeleteData() {
        WebElement buttonDeleteData = driver.findElement(By.id("reset"));
        buttonDeleteData.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
