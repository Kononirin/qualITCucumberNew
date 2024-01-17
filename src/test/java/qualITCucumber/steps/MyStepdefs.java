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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyStepdefs {

    ChromeOptions options = new ChromeOptions();
    WebDriver driver = new ChromeDriver(options);

    private WebElement dropdownSandbox = driver.findElement(By.id("navbarDropdown"));
    private WebElement pointFood = driver.findElement(By.xpath("//a[@href='/food']"));
    private WebElement buttonDeleteData = driver.findElement(By.id("reset"));
    private WebElement buttonAdd = driver.findElement(By.xpath("//button[text()='Добавить']"));
    private WebElement inputName = driver.findElement(By.id("name"));
    private WebElement dropdownType = driver.findElement(By.id("type"));
    private WebElement dropdownPointFruitType = driver.findElement(By.xpath("//option[@value='FRUIT']"));
    private WebElement dropdownPointVegetableType = driver.findElement(By.xpath("//option[@value='VEGETABLE']"));
    private WebElement checkBoxExotic = driver.findElement(By.id("exotic"));
    private WebElement buttonSave = driver.findElement(By.id("save"));
    String url = "http://localhost:8080/";
    String exoticFruit = "ЭкЗоТиЧеСкИй FrUiT!";

    @Дано("открыт стенд по адресу {string}")
    public void открыт_стенд_по_адресу(String str) {
        driver.get(url);
    }

    @Когда("Пользователь нажимает на выпадающий список Песочница")
    public void clickDropdownSandbox() {
        dropdownSandbox.click();
    }

    @И("Пользователь выбирает в выпадающем списке пункт Товары")
    public void clickPointFood() {
        pointFood.click();
    }

    @И("Пользователь нажимает на кнопку Добавить")
    public void clickButtonAdd() {
        buttonAdd.click();
    }

    @И("Пользователь вводит наименование {string}")
    public void typeName(String str) {
        inputName.sendKeys(exoticFruit);
    }

    @И("Пользователь выбирает в выпадающем списке тип Фрукт")
    public void clickDropdownTypeFruit() {
        dropdownType.click();
        dropdownPointFruitType.click();
    }

    @И("Пользователь выбирает чек-бокс Экзотический")
    public void clickCheckBoxExotic() {
        checkBoxExotic.click();
    }

    @И("Пользователь нажимает кнопку Сохранить")
    public void clickButtonSave() {
        buttonSave.click();
    }


    @Тогда("Проверяем, что в последней строке таблицы Товары отображаются введенные данные")
    public void assertResult() {
        List<String> listWithFruitCyrillicAndLatinSymbols = Arrays.asList(
                "5", "ЭкЗоТиЧеСкИй FrUiT!", "Фрукт", "true");

        List<WebElement> lastRowInTableOfFoods = driver.findElements(By.xpath(
                "//tr[5]/*"));

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
        buttonDeleteData.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
