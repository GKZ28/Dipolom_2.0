package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import ru.netology.data.DataHelper;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;

public class Payment {
    private final SelenideElement fieldCardNumber = $("[placeholder = '0000 0000 0000 0000']");
    private final SelenideElement fieldMonth = $("[placeholder = '08']");
    private final SelenideElement fieldYear = $("[placeholder = '22']");
    private final SelenideElement fieldCvv = $("[placeholder='999']");
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement requestSendButton = $(withText("Отправляем запрос в Банк..."));
    private final SelenideElement fieldOwner = $$("[class='input__control']").get(3);

    private final SelenideElement invalidExpiration = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpired = $(withText("Истёк срок действия карты"));
    private final SelenideElement operationIsApproved = $(withText("Операция одобрена Банком"));
    private final SelenideElement errorNotification = $(withText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement wrongFormat = $(".input__sub");
    private final SelenideElement wrongFormatAll = $(withText("Неверный формат"));
    private final SelenideElement requiredField = $(withText("Поле обязательно для заполнения"));

    public void fillForm(DataHelper.cardInfo cardInfo) {
        fieldCardNumber.setValue(cardInfo.getCardNumber());
        fieldMonth.setValue(cardInfo.getMonth());
        fieldYear.setValue(cardInfo.getYear());
        fieldOwner.setValue(cardInfo.getOwner());
        fieldCvv.setValue(cardInfo.getCvv());
        continueButton.click();
    }

    public void fillFormAndSend(DataHelper.cardInfo cardInfo) {
        this.fillForm(cardInfo);
        requestSendButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void checkingOperationIsApproved() {
        operationIsApproved.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkingErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkingInvalidExpirationDate() {
        invalidExpiration.shouldBe(Condition.visible);
    }

    public void checkingCardExpired() {
        cardExpired.shouldBe(Condition.visible);
    }

    public void checkingWrongFormat() {
        wrongFormat.shouldHave(text("Неверный формат"));
    }

    public void checkingWrongFormatAll() {
        wrongFormatAll.shouldBe(Condition.visible);
    }

    public void checkingRequiredField() {
        requiredField.shouldBe(Condition.visible);
    }

    public void checkingInvalidCardNumber() {
        fieldCardNumber.$("input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkingInvalidMonthT() {
        fieldMonth.$("input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkingInvalidYearT() {
        fieldYear.$("input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkingInvalidOwnerT() {
        fieldOwner.$("input__sub").shouldHave(text("Неверный формат"));
    }

    public void checkingInvalidCVVT() {
        fieldCvv.$("input__sub").shouldHave(text("Неверный формат"));
    }
}