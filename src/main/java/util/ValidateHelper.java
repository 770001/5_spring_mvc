package util;

/*
Проверка вводимых данных с веб-странички.
 */
public class ValidateHelper {
    //проверка ввода String name, surname, age
    public static boolean isValidate(String name, String surname, String age) {
        if (name != null && name.length() > 0 && !name.isEmpty()) {
            if (surname != null && surname.length() > 0 && !surname.isEmpty()) {
                if (age != null && age.length() > 0 && !age.isEmpty()) {
                    Long num = Long.parseLong(age);
                    if (num > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Новый метод. проверка ввода String name, surname, age, role
    public static boolean isValidate(String name, String surname, String role, String age) {
        if (name != null && name.length() > 0 && !name.isEmpty()) {
            if (surname != null && surname.length() > 0 && !surname.isEmpty()) {
                if (role.equals("admin") | role.equals("user")) {
                    if (age != null && age.length() > 0 && !age.isEmpty()) {
                        Long num = Long.parseLong(age);
                        if (num > 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //Новый метод. Проверка при login
    public static boolean isValidate (String name, String surname) {
        if (name != null && name.length() > 0 && !name.isEmpty()) {
            if (surname != null && surname.length() > 0 && !surname.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    //проверка Number id при Update
    public static boolean isValidate(String number) {
        if (number != null && number.length() > 0 && !number.isEmpty()) {
            Long num = Long.parseLong(number);
            if (num > 0) {
                return true;
            }
        }
        return false;
    }
}
