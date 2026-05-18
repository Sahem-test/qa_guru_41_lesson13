package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;
import guru.qa.pages.components.CheckResultComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormPages {

    CalendarComponent calendarComponent = new CalendarComponent();
    CheckResultComponent checkResultComponent = new CheckResultComponent();

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement openCalendar = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement setUserHobbies = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateSelect = $("#state");
    private final SelenideElement citySelect = $("#city");
    private final SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private final SelenideElement button = $("#submit");
    private final SelenideElement modalWindow = $(".modal-dialog");
    private final SelenideElement modalWindowText = $("#example-modal-sizes-title-lg");
    private final SelenideElement formError = $("#formError");

    @Step("Open registration page /automation-practice-form")
    public PracticeFormPages openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Removing advertisement")
    public PracticeFormPages removeAds() {
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        return this;
    }

    @Step("Type first name \"{value}\"")
    public PracticeFormPages setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Type last name \"{value}\"")
    public PracticeFormPages setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Type email \"{value}\"")
    public PracticeFormPages setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Chose gender \"{value}\"")
    public PracticeFormPages setGender(String value) {
        genderWrapper.$(byText((value))).click();
        return this;
    }

    @Step("Type user number \"{value}\"")
    public PracticeFormPages setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Chose date of birth \"{day}\" \"{month}\", \"{year}\"")
    public PracticeFormPages setDateOfBirth(String day, String month, String year) {
        openCalendar.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Chose hobbies \"{value}\"")
    public PracticeFormPages setHobby(String value) {
        setUserHobbies.$(byText(value)).click();
        return this;
    }

    @Step("Chose subject \"{value}\"")
    public PracticeFormPages setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Chose picture \"{value}\"")
    public PracticeFormPages uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    @Step("Type user Current address \"{value}\"")
    public PracticeFormPages setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Chose state \"{state}\" and \"{city}\"")
    public PracticeFormPages setStateAndCite(String state, String city) {
        stateSelect.click();
        stateCityContainer.$(byText(state)).click();
        citySelect.click();
        stateCityContainer.$(byText(city)).click();
        return this;
    }

    @Step("Press button")
    public PracticeFormPages submitForm() {
        button.click();
        return this;
    }

    @Step("Check that the modals have to visible")
    public PracticeFormPages modalWindowShouldBeVisible() {
        modalWindow.shouldBe(visible);
        return this;
    }

    @Step("Check that the modals not have to visible")
    public PracticeFormPages modalWindowShouldNotBeVisible() {
        modalWindow.shouldNotBe(visible);
        return this;
    }

    @Step("Check that the modals have to content greeting")
    public PracticeFormPages checkGreetingTextModalWindow() {
        modalWindowText.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    @Step("Check that the result this field {key} has to {value} ")
    public PracticeFormPages checkResult(String key, String value) {
        checkResultComponent.checkResult(key, value);
        return this;
    }


    public PracticeFormPages checkDateOfBirth(String day, String month, String year) {
        checkResultComponent.checkDateOfBirthResultComponent(day, month, year);
        return this;
    }
    
    @Step("Check that the required fields are filled in")
    public void requirementFillFormTest() {
        formError.shouldHave(text(("Please fill required fields and enter a valid 10-digit mobile number.")));
    }

}
