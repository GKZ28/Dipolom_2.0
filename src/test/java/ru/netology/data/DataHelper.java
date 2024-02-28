package ru.netology.data;

import lombok.Value;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class DataHelper {

    @Value
    public static class cardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvv;
    }

    public String approvedCardNumber() {
        return "4444 4444 4444 4441";
    }
    public String declinedCardNumber() {
        return "4444 4444 4444 4442";
    }
    public String shortCardNumber() {
        return "4444 4444 4444 44";
    }
    public String lettersCardNumber() {
        return "aaaa aaaa aaaa aaaa";
    }
    public String emptyCardNumberValue() {
        return " ";
    }
    public String invalidCardNumber() {
        return "0000 0000 0000 0001";
    }
    public String validMonth() {
        return "11";
    }
    public String emptyMonthValue() {
        return " ";
    }
    public String invalidMonth() {
        return "13";
    }
    public String invalidMonthZero() {
        return "00";
    }

    public String validYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.plusYears(1);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }
    public String invalidYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.plusYears(50);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }
    public String expiredYear() {
        LocalDate year = LocalDate.now();
        LocalDate newYear = year.minusYears(11);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        return newYear.format(yearFormatter);
    }
    public String emptyYearValue() {
        return " ";
    }
    public String yearValueOneDigit() {
        return "2";
    }

    public String validOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }
    public String emptyOwnerValue() {
        return " ";
    }
    public String invalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public String validCVV() {
        return "123";
    }
    public String emptyCvvValue() {
        return " ";
    }
    public String invalidCvv() {
        return "12";
    }



    public cardInfo approvedCardInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public cardInfo declinedCardInfo() {
        return new cardInfo(declinedCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public cardInfo invalidCardNumberInfo() {
        return new cardInfo(invalidCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public cardInfo shortCardNumberInfo() {
        return new cardInfo(shortCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public cardInfo lettersCardNumberInfo() {
        return new cardInfo(lettersCardNumber(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public cardInfo emptyCardNumberInfo() {
        return new cardInfo(emptyCardNumberValue(), validMonth(), validYear(), validOwner(), validCVV());
    }

    public cardInfo invalidMonthInfo() {
        return new cardInfo(approvedCardNumber(), invalidMonth(), validYear(), validOwner(), validCVV());
    }

    public cardInfo emptyMonthInfo() {
        return new cardInfo(approvedCardNumber(), emptyMonthValue(), validYear(), validOwner(), validCVV());
    }

    public cardInfo invalidMonthZeroInfo() {
        return new cardInfo(approvedCardNumber(), invalidMonthZero(), validYear(), validOwner(), validCVV());
    }

    public cardInfo expiredYearInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), expiredYear(), validOwner(), validCVV());
    }

    public cardInfo yearInfoOneDigit() {
        return new cardInfo(approvedCardNumber(), validMonth(), yearValueOneDigit(), validOwner(), validCVV());
    }

    public cardInfo invalidYearInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), invalidYear(), validOwner(), validCVV());
    }

    public cardInfo emptyYearInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), emptyYearValue(), validOwner(), validCVV());
    }

    public cardInfo invalidOwnerInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), validYear(), invalidOwner(), validCVV());
    }

    public cardInfo emptyOwnerInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), validYear(), emptyOwnerValue(), validCVV());
    }

    public cardInfo emptyCvvInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), validYear(), validOwner(), emptyCvvValue());
    }

    public cardInfo invalidCvvInfo() {
        return new cardInfo(approvedCardNumber(), validMonth(), validYear(), validOwner(), invalidCvv());
    }

    public cardInfo emptyFields() {
        return new cardInfo(emptyCardNumberValue(), emptyMonthValue(), emptyYearValue(),
                emptyOwnerValue(), emptyCvvValue());
    }



}