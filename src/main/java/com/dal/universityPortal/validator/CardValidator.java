package com.dal.universityPortal.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dal.universityPortal.constant.RegexConstant.CARD_VALIDATION_STRING;


public class CardValidator implements Validator<String> {
    @Override
    public boolean isValid(String cardNumber) {
        /*
        *   Visa : 13 or 16 digits, starting with 4.
            MasterCard : 16 digits, starting with 51 through 55.
            Discover : 16 digits, starting with 6011 or 65.
            American Express : 15 digits, starting with 34 or 37.
            Diners Club : 14 digits, starting with 300 through 305, 36, or 38.
            JCB : 15 digits, starting with 2131 or 1800, or 16 digits starting with 35.
            *
            * Used Luhn algorithm
            * Algorithm: https://en.wikipedia.org/wiki/Luhn_algorithm
        * */
        List<String> cardNumberList = new ArrayList<>();
        cardNumberList.add(cardNumber);
        Pattern pattern = Pattern.compile(CARD_VALIDATION_STRING);
        for (String ignored : cardNumberList) {
            cardNumber = cardNumber.replace("-", "");
            Matcher match = pattern.matcher(cardNumber);
            if (match.matches()) {
                int[] arrayCardNumber = new int[cardNumber.length()];
                for (int i = 0; i < cardNumber.length(); i++) {
                    arrayCardNumber[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
                }
                for (int i = arrayCardNumber.length - 2; i >= 0; i = i - 2) {
                    int j = arrayCardNumber[i];

                    j = j * 2;
                    if (j > 9) {
                        j = j % 10 + 1;
                    }
                    arrayCardNumber[i] = j;
                }
                int sum = 0;
                for (int j : arrayCardNumber) {
                    sum += j;
                }
                if (sum % 10 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getErrorMessage() {
        return "Not valid card!";
    }
}
