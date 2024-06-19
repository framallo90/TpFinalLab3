package com.framallo90.UsuarioAbstracta.view;

import com.framallo90.consola.Consola;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioView {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isValidEmail(String emailAddress) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

}
