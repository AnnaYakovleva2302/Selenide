package ru.netology.card;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Ханты-Мансийск");
        Calendar cal = new GregorianCalendar();
        cal.roll(Calendar.DAY_OF_YEAR, 3);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date = df.format(cal.getTime());
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Яковлева Анна");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Забронировать")).click();
        $("[data-test-id=notification] .notification__title").shouldHave(text("Успешно!"), Duration.ofSeconds(15));
    }
}
