package tests;

import com.codeborne.selenide.Condition;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {
    @Test
    @Description("Тест на наличие логотипа")
    void logoAvailabilityTest(){
        open("/");
        $("header .chakra-image").should(exist);
    }
    @Test
    @Description("Тест на кликабельность логотипа")
    void clicabilityLogoTest(){
        open("/");
        $("header .chakra-image").shouldBe(enabled).click();
        String currentUrl = url();
        assertEquals("/",currentUrl,"Текущий URL не соответствует главной странице.");
    }
}
