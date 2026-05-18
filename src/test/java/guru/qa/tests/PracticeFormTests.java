package guru.qa.tests;

import guru.qa.TestBase;
import guru.qa.pages.PracticeFormPages;
import guru.qa.testData.TestData;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Story("Registration form")
public class PracticeFormTests extends TestBase {
    PracticeFormPages practiceFormPages = new PracticeFormPages();
    TestData td = new TestData();

    @Test
    @Tag("smoke")
    @Tag("homeWork")
    @DisplayName("Fill registration form, positive test")
    void fillPracticeFormPositiveTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Fill registration form", () -> {
            practiceFormPages.setFirstName(td.userName)
                    .setLastName(td.lastName)
                    .setUserEmail(td.userEmail)
                    .setGender(td.gender)
                    .setUserNumber(td.userNumber)
                    .setDateOfBirth(td.day, td.month, td.year)
                    .setSubjects(td.subjects)
                    .setHobby(td.hobby)
                    .uploadPicture(td.uploadPicture)
                    .setCurrentAddress(td.currentAddress)
                    .setStateAndCite(td.state, td.city)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldBeVisible()
                    .checkGreetingTextModalWindow()
                    .checkResult("Student Name", td.userName + " " + td.lastName)
                    .checkResult("Student Email", td.userEmail)
                    .checkResult("Gender", td.gender)
                    .checkResult("Mobile", td.userNumber)
                    .checkDateOfBirth(td.day, td.month, td.year)
                    .checkResult("Subjects", td.subjects)
                    .checkResult("Hobbies", td.hobby)
                    .checkResult("Picture", td.uploadPicture)
                    .checkResult("Address", td.currentAddress)
                    .checkResult("State and City", td.state + " " + td.city);
        });
    }

    @Test
    @Tag("homeWork")
    @DisplayName("Minimal required fields, positive test")
    void minimalRequiredFieldsPositiveTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Fill required fields", () -> {

            practiceFormPages.setFirstName(td.userName)
                    .setLastName(td.lastName)
                    .setGender(td.gender)
                    .setUserNumber(td.userNumber)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldBeVisible()
                    .checkGreetingTextModalWindow()
                    .checkResult("Student Name", td.userName + " " + td.lastName)
                    .checkResult("Gender", td.gender)
                    .checkResult("Mobile", td.userNumber);
        });
    }

    @Test
    @Tag("homeWork")
    @DisplayName("Fill less then minimal required fields, negative test")
    void lessMinimalRequiredFieldsNegativeTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Don't fill in enough fields", () -> {
            practiceFormPages.setFirstName(td.userName)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldNotBeVisible();
        });

    }

    @Test
    @Tag("homeWork")
    @DisplayName("Phone has less required signs, negative test")
    void lessMinimalSignPhoneNegativeTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Fill registration form", () -> {
            practiceFormPages.setFirstName(td.userName)
                    .setLastName(td.lastName)
                    .setGender(td.gender)
                    .setUserNumber(td.wrongNumber)
                    .submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldNotBeVisible();
        });
    }

    @Test
    @Tag("homeWork")
    @DisplayName("Empty form, negative test")
    void EmptyFormTest() {
        step("Open registration form", () -> {
            practiceFormPages.openPage()
                    .removeAds();
        });
        step("Submit form", () -> {
            practiceFormPages.submitForm();
        });
        step("Check form results", () -> {
            practiceFormPages.modalWindowShouldNotBeVisible()
                    .requirementFillFormTest();
        });

    }

}
