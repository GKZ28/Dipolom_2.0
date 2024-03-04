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

public class DebitTests {

    public final Main mainPage = new Main();
    public final DataHelper dataHelper = new DataHelper();
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
    @DisplayName("Test: Checking Page Opened / DEBIT")
    void checkingDebitPageOpened() {
        mainPage.byDebitCard();
    }

    @Test
    @DisplayName("Test: Checking Approved Card / DEBIT")
    void checkingApprovedCardDebit() {
        var payForm = mainPage.byDebitCard();
        var approvedInfo = dataHelper.approvedCardInfo();
        payForm.fillFormAndSend(approvedInfo);
        payForm.checkingOperationIsApproved();
        var dataSQLPayment = SQLHelper.getDebitInfo().getStatus();
        assertEquals("APPROVED", dataSQLPayment);
    }

    @Test
    @DisplayName("Test: Checking Declined Card / DEBIT")
    void checkingDeclinedCardDebit() {
        var payForm = mainPage.byDebitCard();
        var declinedInfo = dataHelper.declinedCardInfo();
        payForm.fillFormAndSend(declinedInfo);
        payForm.checkingErrorNotification();
        var dataSQLPayment = SQLHelper.getDebitInfo().getStatus();
        assertEquals("DECLINED", dataSQLPayment);
    }

    @Test
    @DisplayName("Test: Checking Purchase In Database / DEBIT")
    void checkingPurchaseInDatabaseDebit() {
        var payForm = mainPage.byDebitCard();
        var approvedInfo = dataHelper.approvedCardInfo();
        payForm.fillFormAndSend(approvedInfo);
        payForm.checkingOperationIsApproved();
        var dataSQLPayAmount = SQLHelper.getDebitInfo();
        assertEquals(45000 * 100, dataSQLPayAmount.getAmount());
    }


    @Test
    @DisplayName("Test: Checking Invalid Card Number / DEBIT")
    void checkingInvalidCardNumberDebit() {
        var payForm = mainPage.byDebitCard();
        var invalidCardNumber = dataHelper.invalidCardNumberInfo();
        payForm.fillFormAndSend(invalidCardNumber);
        payForm.checkingErrorNotification();
    }

    @Test
    @DisplayName("Test: Checking Short Card Number / DEBIT")
    void checkingShortCardNumberDebit() {
        var payForm = mainPage.byDebitCard();
        var shortCardNumber = dataHelper.shortCardNumberInfo();
        payForm.fillForm(shortCardNumber);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Empty Card Number / DEBIT")
    void checkingEmptyCardNumberDebit() {
        var payForm = mainPage.byDebitCard();
        var emptyCardNumber = dataHelper.emptyCardNumberInfo();
        payForm.fillForm(emptyCardNumber);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Letters Card Number / DEBIT")
    void checkingLettersCardNumberDebit() {
        var payForm = mainPage.byCreditCard();
        var lettersCardNumber = dataHelper.lettersCardNumberInfo();
        payForm.fillForm(lettersCardNumber);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Negative Month / DEBIT")
    void checkingNegativeMonthDebit() {
        var payForm = mainPage.byDebitCard();
        var invalidMonth = dataHelper.invalidMonthInfo();
        payForm.fillForm(invalidMonth);
        payForm.checkingInvalidExpirationDate();
    }

    @Test
    @DisplayName("Test: Checking Negative Month Zero / DEBIT")
    void checkingInvalidMonthZeroDebit() {
        var payForm = mainPage.byDebitCard();
        var invalidMonth = dataHelper.invalidMonthZeroInfo();
        payForm.fillForm(invalidMonth);
        payForm.checkingInvalidExpirationDate();
    }

    @Test
    @DisplayName("Test: Checking Empty Month / DEBIT")
    void checkingEmptyMonthDebit() {
        var payForm = mainPage.byDebitCard();
        var emptyMonth = dataHelper.emptyMonthInfo();
        payForm.fillForm(emptyMonth);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Expired Year Date / DEBIT")
    void checkingExpiredYearDateDebit() {
        var payForm = mainPage.byDebitCard();
        var expiredYear = dataHelper.expiredYearInfo();
        payForm.fillForm(expiredYear);
        payForm.checkingCardExpired();
    }

    @Test
    @DisplayName("Test: Checking Empty Year Date / DEBIT")
    void checkingEmptyYearDateDebit() {
        var payForm = mainPage.byDebitCard();
        var emptyYear = dataHelper.emptyYearInfo();
        payForm.fillForm(emptyYear);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Year One Digit / DEBIT")
    void checkingYearDateOneDigitDebit() {
        var payForm = mainPage.byDebitCard();
        var yearOneDigit = dataHelper.yearInfoOneDigit();
        payForm.fillForm(yearOneDigit);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Incorrectly Expiration Date / DEBIT")
    void checkingIncorrectlyExpirationDateDebit() {
        var payForm = mainPage.byDebitCard();
        var invalidYear = dataHelper.invalidYearInfo();
        payForm.fillForm(invalidYear);
        payForm.checkingInvalidExpirationDate();
    }

    @Test
    @DisplayName("Test: Checking Owners Data In Russian / DEBIT")
    void checkingOwnersDataInRussianDebit() {
        var payForm = mainPage.byDebitCard();
        var invalidOwner = dataHelper.invalidOwnerInfo();
        payForm.fillForm(invalidOwner);
        payForm.checkingWrongFormat();
    }

    @Test
    @DisplayName("Test: Checking Empty Owners / DEBIT")
    void checkingEmptyOwnersDebit() {
        var payForm = mainPage.byDebitCard();
        var emptyOwner = dataHelper.emptyOwnerInfo();
        payForm.fillForm(emptyOwner);
        payForm.checkingRequiredField();
    }

    @Test
    @DisplayName("Test: Checking Empty Cvv / DEBIT")
    void checkingEmptyCvvDebit() {
        var payForm = mainPage.byDebitCard();
        var emptyCvv = dataHelper.emptyCvvInfo();
        payForm.fillForm(emptyCvv);
        payForm.checkingWrongFormatAll();
    }

    @Test
    @DisplayName("Test: Checking Invalid Cvv / DEBIT")
    void checkingInvalidCvvDebit() {
        var payForm = mainPage.byDebitCard();
        var emptyCvv = dataHelper.invalidCvvInfo();
        payForm.fillForm(emptyCvv);
        payForm.checkingWrongFormat();
    }



}
