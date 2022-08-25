package guru.ga;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureLambdaStepsTest {

    int issue = 813;
    String baseUrl = "https://github.com/";
    String request = "allure-framework/allure-java";
    String gitRepository = "allure-framework/allure-java";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open(baseUrl);
        });

        step("Поиск нужного репозитория " + request, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(request);
            $(".header-search-input").submit();
        });

        step("Кликаем по ссылке ропозитория " + gitRepository, () -> {
            $(linkText(gitRepository)).click();
        });

        step("Открываем таб Issues ", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером " + issue, () -> {
            $(withText("#" + issue)).should(Condition.exist);
        });
    }

}
