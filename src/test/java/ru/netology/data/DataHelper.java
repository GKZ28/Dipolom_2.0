package ru.netology.data;

import lombok.Value;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvv;
    }

    public static String approvedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String declinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String shortCardNumber() {
        return "4444 4444 4444 44";
    }

    public static String lettersCardNumber() {
        return "aaaa aaaa aaaa aaaa";
    }

    public static String emptyCardNumberValue() {
        return " ";
    }

    public static String invalidCardNumber() {
        return "0000 0000 0000 0001";
    }

    public static String validMonth() {
        return "11";
    }

    public static String emptyMonthValue() {
        return " ";
    }

    public static String invalidMonth() {
        return "13";
    }

    public static String invalidMonthZero() {
        return "00";
    }

    public static String validYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.plusYears(1);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }

    public static String invalidYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.plusYears(50);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }

    public static String expiredYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.minusYears(11);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }

    public static String emptyYearValue() {
        return " ";
    }

    public static String yearValueOneDigit() {
        return "2";
    }

    public static String validOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String emptyOwnerValue() {
        return " ";
    }

    public static String invalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String validCVV() {
        return "123";
    }

    public static String emptyCvvValue() {
        return " ";
    }

    public static String invalidCvv() {
        return "12";
    }

    public static CardInfo approvedCardInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo declinedCardInfo() {
        return new CardInfo(declinedCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo invalidCardNumberInfo() {
        return new CardInfo(invalidCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo shortCardNumberInfo() {
        return new CardInfo(shortCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo lettersCardNumberInfo() {
        return new CardInfo(lettersCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo emptyCardNumberInfo() {
        return new CardInfo(emptyCardNumberValue(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo invalidMonthInfo() {
        return new CardInfo(approvedCardNumber(), invalidMonth(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo emptyMonthInfo() {
        return new CardInfo(approvedCardNumber(), emptyMonthValue(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo invalidMonthZeroInfo() {
        return new CardInfo(approvedCardNumber(), invalidMonthZero(), validYear(), validOwner(), validCVV());
    }

    public static CardInfo expiredYearInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), expiredYear(), validOwner(), validCVV());
    }

    public static CardInfo yearInfoOneDigit() {
        return new CardInfo(approvedCardNumber(), validMonth(), yearValueOneDigit(), validOwner(), validCVV());
    }

    public static CardInfo invalidYearInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), invalidYear(), validOwner(), validCVV());
    }

    public static CardInfo emptyYearInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), emptyYearValue(), validOwner(), validCVV());
    }

    public static CardInfo invalidOwnerInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), validYear(), invalidOwner(), validCVV());
    }

    public static CardInfo emptyOwnerInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), validYear(), emptyOwnerValue(), validCVV());
    }

    public static CardInfo emptyCvvInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), validYear(), validOwner(), emptyCvvValue());
    }

    public static CardInfo invalidCvvInfo() {
        return new CardInfo(approvedCardNumber(), validMonth(), validYear(), validOwner(), invalidCvv());
    }

    public static CardInfo emptyFields() {
        return new CardInfo(emptyCardNumberValue(), emptyMonthValue(), emptyYearValue(),
                emptyOwnerValue(), emptyCvvValue());
    }
}
