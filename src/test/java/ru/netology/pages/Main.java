package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class Main {
    private final SelenideElement buttonForBuy = $(byText("Купить"));
    private final SelenideElement buttonForCredit = $(byText("Купить в кредит"));
    private final SelenideElement paymentForDebit = $(byText("Оплата по карте"));
    private final SelenideElement paymentForCredit = $(byText("Кредит по данным карты"));

    public Payment byDebitCard() {
        buttonForBuy.click();
        paymentForDebit.shouldHave(Condition.visible);
        return new Payment();
    }

    public Payment byCreditCard() {
        buttonForCredit.click();
        paymentForCredit.shouldHave(Condition.visible);
        return new Payment();
    }
}