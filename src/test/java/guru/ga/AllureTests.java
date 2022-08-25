package guru.ga;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class AllureTests {

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String baseUrl = "https://github.com/";
        String request = "allure-framework/allure-java";
        String gitRepository = "allure-framework/allure-java";
        String issue = "#813";

        open(baseUrl);
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(request);
        $(".header-search-input").submit();

        $(linkText(gitRepository)).click();
        $("#issues-tab").click();
        $(withText(issue)).should(Condition.exist);
    }
}
