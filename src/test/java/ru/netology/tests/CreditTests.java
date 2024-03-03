package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.Main;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTests {

    public final Main mainPage = new Main();
    public final DataHelper dataHelper = new DataHelper();
    public final SQLHelper sqlHelper = new SQLHelper();
    public final String url = "http://localhost:8080";

    @BeforeEach
    void openForTests() {
        System.setProperty("selenide.headless", "true");
        open(url);
    }

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    @DisplayName("Test: Checking Page Opened / CREDIT")
    void checkingCreditPageOpened() {
        mainPage.byCreditCard();
    }

    @Test
    @DisplayName("Test: Checking Approved Card / CREDIT")
    void checkingApprovedCardCredit() {
        var payForm = mainPage.byCreditCard();
        var approvedInfo = dataHelper.approvedCardInfo();
        payForm.fillFormAndSend(approvedInfo);
        payForm.checkingOperationIsApproved();
        String dataSQLPayment = sqlHelper.statusOfCredit();
        assertEquals("APPROVED", dataSQLPayment);
    }

    @Test
    @DisplayName("Test: Checking Declined Card / CREDIT")
    void checkingDeclinedCardCredit() {
        var payForm = mainPage.byCreditCard();
        var declinedInfo = dataHelper.declinedCardInfo();
        payForm.fillFormAndSend(declinedInfo);
        payForm.checkingErrorNotification();
        String dataSQLPayment = sqlHelper.statusOfCredit();
        assertEquals("DECLINED", dataSQLPayment);
    }

    @Test
    @DisplayName("Test: Checking Purchase In Database / CREDIT")
    void checkingPurchaseInDatabaseCredit() {
        var payForm = mainPage.byCreditCard();
        var approvedInfo = dataHelper.approvedCardInfo();
        payForm.fillFormAndSend(approvedInfo);
        payForm.checkingOperationIsApproved();
        String dataSQLPayAmount = sqlHelper.statusOfAmount();
        assertEquals("45000", dataSQLPayAmount);
    }

    @Test
    @DisplayName("Test: Checking Invalid Card Number / CREDIT")
    void checkingInvalidCardNumberCredit() {
        var payForm = mainPage.byCreditCard();
        var invalidCardNumber = dataHelper.invalidCardNumberInfo();
        payForm.fillFormAndSend(invalidCardNumber);
        payForm.checkingErrorNotification();
    }

    @Test
    @DisplayName("Test: Checking Short Card Number / CREDIT")
    void checkingShortCardNumberCredit() {
        var payForm = mainPage.byCreditCard();
        var shortCardNumber = dataHelper.shortCardNumberInfo();
        payForm.fillForm(shortCardNumber);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Empty Card Number / CREDIT")
    void checkingEmptyCardNumberCredit() {
        var payForm = mainPage.byCreditCard();
        var emptyCardNumber = dataHelper.emptyCardNumberInfo();
        payForm.fillForm(emptyCardNumber);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Letters Card Number / CREDIT")
    void checkingLettersCardNumberCredit() {
        var payForm = mainPage.byCreditCard();
        var lettersCardNumber = dataHelper.lettersCardNumberInfo();
        payForm.fillForm(lettersCardNumber);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Negative Month / CREDIT")
    void checkingNegativeMonthCredit() {
        var payForm = mainPage.byCreditCard();
        var invalidMonth = dataHelper.invalidMonthInfo();
        payForm.fillForm(invalidMonth);
        payForm.checkingInvalidExpirationDate();
    }

    @Test
    @DisplayName("Test: Checking Negative Month Zero / CREDIT")
    void checkingInvalidMonthZeroCredit() {
        var payForm = mainPage.byCreditCard();
        var invalidMonth = dataHelper.invalidMonthZeroInfo();
        payForm.fillForm(invalidMonth);
        payForm.checkingInvalidExpirationDate();
    }

    @Test
    @DisplayName("Test: Checking Empty Month / CREDIT")
    void checkingEmptyMonthCredit() {
        var payForm = mainPage.byCreditCard();
        var emptyMonth = dataHelper.emptyMonthInfo();
        payForm.fillForm(emptyMonth);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Expired Year Date / CREDIT")
    void checkingExpiredYearDateCredit() {
        var payForm = mainPage.byCreditCard();
        var expiredYear = dataHelper.expiredYearInfo();
        payForm.fillForm(expiredYear);
        payForm.checkingCardExpired();
    }

    @Test
    @DisplayName("Test: Checking Empty Year Date / CREDIT")
    void checkingEmptyYearDateCredit() {
        var payForm = mainPage.byCreditCard();
        var emptyYear = dataHelper.emptyYearInfo();
        payForm.fillForm(emptyYear);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Year One Digit / CREDIT")
    void checkingYearDateOneDigitCredit() {
        var payForm = mainPage.byCreditCard();
        var yearOneDigit = dataHelper.yearInfoOneDigit();
        payForm.fillForm(yearOneDigit);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Incorrectly Expiration Date / CREDIT")
    void checkingIncorrectlyExpirationDateCredit() {
        var payForm = mainPage.byCreditCard();
        var invalidYear = dataHelper.invalidYearInfo();
        payForm.fillForm(invalidYear);
        payForm.checkingInvalidExpirationDate();
    }

    @Test
    @DisplayName("Test: Checking Owners Data In Russian / CREDIT")
    void checkingOwnersDataInRussianCredit() {
        var payForm = mainPage.byCreditCard();
        var invalidOwner = dataHelper.invalidOwnerInfo();
        payForm.fillForm(invalidOwner);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Empty Owners / CREDIT")
    void checkingEmptyOwnersCredit() {
        var payForm = mainPage.byCreditCard();
        var emptyOwner = dataHelper.emptyOwnerInfo();
        payForm.fillForm(emptyOwner);
        payForm.checkingRequiredField();
    }

    @Test
    @DisplayName("Test: Checking Empty Cvv / CREDIT")
    void checkingEmptyCvvCredit() {
        var payForm = mainPage.byCreditCard();
        var emptyCvv = dataHelper.emptyCvvInfo();
        payForm.fillForm(emptyCvv);
        payForm.checkingWrongFormatAll();
    }

    @Test
    @DisplayName("Test: Checking Invalid Cvv / CREDIT")
    void checkingInvalidCvvCredit() {
        var payForm = mainPage.byCreditCard();
        var emptyCvv = dataHelper.invalidCvvInfo();
        payForm.fillForm(emptyCvv);
        payForm.checkingWrongFormat();
    }

}