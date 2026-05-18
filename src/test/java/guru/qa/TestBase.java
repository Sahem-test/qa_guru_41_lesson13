package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.pages.PracticeFormPages;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    public PracticeFormPages practiceFormPages = new PracticeFormPages();

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.remote = System.getProperty("remote");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void afterEach() {
        Attach.screenShotAs("Last screenshot");
        Attach.pageSource();
        Attach.attachAsText("Some file", "Some content");
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }
}
