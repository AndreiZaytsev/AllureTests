package guru.ga;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    String baseUrl = "https://github.com/";
    String gitRepository = "allure-framework/allure-java";
    int issue = 813;

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(baseUrl);
    }

    @Step("Поиск нужного репозитория {request}")
    public void searchRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Кликаем по ссылке ропозитория {gitRepository}")
    public void clickOnRepositoryLing(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues ")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером ")
    public void shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }

    @Test
    public void annotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchRepository(gitRepository);
        steps.clickOnRepositoryLing(gitRepository);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(issue);
    }
}
