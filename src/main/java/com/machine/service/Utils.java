package com.machine.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String getMessage(String key, HttpServletRequest request) {
        String language = request.getSession()
                .getAttribute("language").toString();
        Locale locale = new Locale(language);
        ResourceBundle rb = ResourceBundle.getBundle("messages",
                locale);
        return rb.getString(key);
    }

    public static boolean isAllFieldisFilled(String login, String password,
                                             String confirm_password, String phone, String email) {
        boolean res = false;
        if (login != null && password !=null && confirm_password != null
                && phone != null && email != null)
            if (!login.isEmpty() && !password.isEmpty()
                    && !confirm_password.isEmpty() && !phone.isEmpty()
                    && !email.isEmpty()) {
                res = true;
            }
        return res;
    }

    public static boolean isConfrimPasswordEqualsPassword(String password,
                                                          String confirm_password) {
        boolean res = false;
        if (password.equals(confirm_password)) {
            res = true;
        }
        return res;
    }
/*
    public static boolean isAlreadyUser(String login) {
        boolean res = false;
        DaoFactory factory = MySQLFactory.getInstance();
        CustomerDao customerDao = factory.getCustomerDao();
        Customer customer = customerDao.getCustomer(login);
        if (customer != null) {
            res = true;
        }
        return res;
    }
*/
    public static boolean isValidEMail(String email) {
        boolean res = false;
        String regex = "(\\w+)@(\\w+\\.)(\\w+)(\\.\\w+)*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        res = m.matches();
        return res;
    }

    public static boolean isValidLogin(String login) {
        boolean res = false;
        String regex = "^[A-za-zА-яа-я]{3,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(login);
        res = m.matches();
        return res;
    }

    public static boolean isValidPassword(String password) {
        boolean res = false;
        String regex = "^[a-z0-9_-]{3,18}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        res = m.matches();
        return res;
    }

    public static boolean isValidPhone(String phone) {
        boolean res = false;
        String regex = "^[\\(]{0,1}([0-9]){3}[\\)]{0,1}[ ]?([^0-1]){1}([0-9]){2}[ ]?[-]?[ ]?([0-9]){4}[ ]*{0,1}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        res = m.matches();
        return res;
    }

    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return e.getStackTrace()[1].getClassName();
        }
    }
}
