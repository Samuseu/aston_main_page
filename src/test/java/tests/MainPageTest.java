package tests;

import com.codeborne.selenide.Condition;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
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

    @Test
    @Description("Тест на наличие заголовка \"Услуги\"")
    void serviceHeaderAvailabilityTest() {
        open("https://astondevs.ru/");
        $(".chakra-stack.css-1fjhh54").shouldHave(text("Услуги")).should(exist);
    }

    @ValueSource(
            strings = {"Мобильная- \n разработка", "Frontend- \n разработка", "Backend- \n разработка", "1C- \n разработка"}
    )
    @ParameterizedTest(name = "Услуги \"{0}\" Находится на странице")
    @Description("Тест на наличие всех блоков с услугами")
    void allBlocksWithServicesTest(String serviceName) {
        open("https://astondevs.ru/");
        $(".chakra-stack .css-pi2qqf").scrollTo();
        $("div.css-pi2qqf").shouldHave(text(serviceName)).shouldBe(visible);
    }


    @ValueSource(strings = {"IOS", "Android", "Flutter", "React Native"})
    @ParameterizedTest(name = "Тест на наличие {0} в  блоке Мобильная-разработка")
    @Description("Тест на наличие списка технологий в блоке Мобильная разработка")
    void listOfTechologiesInEachServiceBlockTest(String technology) {
        open("https://astondevs.ru/");
        $(".chakra-stack .css-pi2qqf").scrollTo();
        $(".css-uxvjyu").shouldHave(Condition.text(technology));
    }


//    @ValueSource(
//            strings = {"Мобильная- \n разработка"}
//    )
//    @ParameterizedTest
//    @Description("")
//    void serviceMobileDevelopmentTest(String serviceName) {
//        open("https://astondevs.ru/");
//        $(".chakra-stack .css-pi2qqf").scrollTo();
//        $("div.css-pi2qqf").shouldHave(text(serviceName));
//        $(".css-1u7ypi0").parent().shouldHave(pseudo(":before", "position"));
//        assertThat($(".css-1u7ypi0").pseudo(":before", "content")).isEqualTo("\"beforeContent\"");

}







