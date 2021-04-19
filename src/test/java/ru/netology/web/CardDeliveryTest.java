package ru.netology.web;


        import com.codeborne.selenide.Configuration;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.Test;

        import java.time.Duration;
        import java.time.LocalDate;

        import static com.codeborne.selenide.Condition.exactText;
        import static com.codeborne.selenide.Condition.visible;
        import static com.codeborne.selenide.Selectors.withText;
        import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
    }

    @Test
    void shouldRegisterCardOrder() {
        open("http://localhost:9999");
        LocalDate date = LocalDate.now();
        LocalDate futureDate = date.plusDays(3);
        $("[data-test-id=city] .input__control").setValue("Владимир");
        $("[data-test-id=date] .input__control").setValue(futureDate.toString());
        $("[data-test-id=name] .input__control").setValue("Иванова Алла");
        $("[data-test-id=phone] .input__control").setValue("+78005999999");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }

    @Test
    void shouldRegisterCardOrderWithCalendar() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Уфа");
        $("[data-test-id='date'] .input__icon").click();
        $(".calendar__arrow[ data-step='1']").click();
        $$(".calendar__layout .calendar__row .calendar__day").find(exactText("5")).click();
        $("[data-test-id=name] .input__control").setValue("Леонова Алла");
        $("[data-test-id=phone] .input__control").setValue("+78005553535");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }

    @Test
    void shouldRegisterCardOrderWithCity() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Ул");
        $$(".menu-item").find(exactText("Москва")).click();
        $("[data-test-id='date'] .input__icon").click();
        $(".calendar__arrow[ data-step='1']").click();
        $$(".calendar__layout .calendar__row .calendar__day").find(exactText("5")).click();
        $("[data-test-id=name] .input__control").setValue("Леонова Алла");
        $("[data-test-id=phone] .input__control").setValue("+78005553535");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }

}