package com.dal.universityPortal.constant;

public class RegexConstant {
    public static final String SPECIAL_CHAR_PRESENT_REGEX = "[^A-Za-z0-9]";
    public static final String EMAIL_REGEX="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +  //part before @
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String CARD_VALIDATION_STRING =
            "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

    private RegexConstant() {}
}
