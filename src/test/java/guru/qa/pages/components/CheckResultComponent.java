package guru.qa.pages.components;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CheckResultComponent {
    public void checkResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }

    public void checkDateOfBirthResultComponent(String day, String month, String year) {
        String expectedDate = buildExpectedDate(day, month, year);
        checkResult("Date of Birth", expectedDate);
    }

    private String buildExpectedDate(String day, String month, String year) {
        return year + "-" + getMonthNumber(month) + "-" + day;
    }

    private String getMonthNumber(String month) {
        return switch (month) {
            case "January" -> "01";
            case "February" -> "02";
            case "March" -> "03";
            case "April" -> "04";
            case "May" -> "05";
            case "June" -> "06";
            case "July" -> "07";
            case "August" -> "08";
            case "September" -> "09";
            case "October" -> "10";
            case "November" -> "11";
            case "December" -> "12";
            default -> "This month not exist!!!";
        };
    }
}
