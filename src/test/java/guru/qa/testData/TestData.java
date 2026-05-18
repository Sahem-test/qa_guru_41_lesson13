package guru.qa.testData;

import net.datafaker.Faker;

import java.util.Locale;

import static utils.RandomUtilsWithFaker.selectCity;

public class TestData {
     public static Faker faker = new Faker(new Locale("ru", "Ru"));

    public String
            userName = faker.name().name(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = faker.options().option("Male", "Female", "Other"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            wrongNumber = faker.phoneNumber().subscriberNumber(5),
            day = String.format("%02d", faker.number().numberBetween(1, 29)),
            month = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
            year = String.valueOf(faker.number().numberBetween(1976, 2010)),
            subjects = faker.options().option("English", "Math", "Physics", "Chemistry", "Biology", "Computer Science", "Economics", "Arts", "History", "Civics"),
            hobby = faker.options().option("Sports", "Reading", "Music"),
            uploadPicture = faker.options().option("Picture1.png", "Picture2.png", "Picture3.png"),
            currentAddress = faker.address().fullAddress(),
            permanentAddress = faker.address().fullAddress(),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = selectCity(state);

}
