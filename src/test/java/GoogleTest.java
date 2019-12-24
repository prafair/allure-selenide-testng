import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTest {

    @BeforeClass
    public void setUp() {
        browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }

    @AfterClass
    public void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void userCanSearch() {
        open("https://google.com/ncr");
        new GooglePage().searchFor("wikipedia");

        SearchResultsPage results = new SearchResultsPage();
        results.getResults().shouldHave(sizeGreaterThan(1));
        results.getResult(0).shouldHave(text("Википедия — свободная энциклопедия"));
    }

}