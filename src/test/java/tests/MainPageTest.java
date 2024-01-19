package tests;

import com.codeborne.selenide.Selenide;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    @Test
    @Description("Тест на наличие логотипа")
    void logoAvailabilityTest() {
        open("https://astondevs.ru/");
        $("header .chakra-image").should(exist);
    }

    @Test
    @Description("Тест на кликабельность логотипа")
    void clickAbilityLogoTest() {
        open("https://astondevs.ru/");
        $("header .chakra-image").shouldBe(enabled).click();
        String currentUrl = url();
        assertEquals("https://astondevs.ru/", currentUrl, "Текущий URL не соответствует главной странице.");
    }

    @ValueSource(
            strings = {"Вакансии", "Стажировка", "Оставить заявку"}
    )
    @ParameterizedTest(name = "Элемент \"{0}\" Находится на странице")
    @Description("Тест на наличие всех разделов навигации")
    void availabilityAllNavigationSections(String sectionName) {
        open("https://astondevs.ru/");
        $(".chakra-stack.css-azggd9").$(byText(sectionName)).should(exist);
    }

    @CsvSource({
            "Вакансии,https://career.astondevs.ru/",
            "Стажировка,https://career.astondevs.ru/trainee",
            "Оставить заявку,https://astondevs.ru/"
    })
    @ParameterizedTest(name = "Ссылка \"{0}\" Переходит на \"{1}\"")
    @Description("Тест на корректность ссылок в навигации")
    void correctLinkInNavigation(
            String sectionName,
            String productUrl
    ) {
        open("https://astondevs.ru/");
        $(".chakra-stack.css-azggd9").$(byText(sectionName)).should(exist).click();
        String currentUrl = url();
        assertEquals(productUrl, currentUrl, "Текущий URL не соответствует главной странице.");
    }

    @Test
    @Description("Тест на кликабельность кнопки \"Оставить заявку\"")
    void clickAbilityButtonTest() {
        open("https://astondevs.ru/");
        $(".chakra-stack.css-azggd9 [type='button']").click();
        $("#contact-form").shouldBe(appear);
    }


}
