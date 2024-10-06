package Custom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    //kiểm tra rỗng
    public static boolean IsEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static boolean IsValidNamelength(String name) {
        String pattern = "^.{0,50}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(name);
        return matcher.matches();
    }
    public static boolean IsValidNameProduct(String name){
        String pattern = "^[^!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>/?]*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(name);
        return matcher.matches();
    }
    //kiểm tra số điện thoại
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String pattern = "^0\\d{9}$";

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(phoneNumber);

        return matcher.matches();
    }

    //kiểm tra số dương
    public static boolean isPositiveNumber(String number) {
        String pattern = "\\d+";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(number);
        return matcher.matches();
    }

    //kiểm tra tràn số
    public static boolean OverflowChecker(String number) {
        try {
            int num = Integer.parseInt(number);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    //Kiểm tra email
    public static boolean isValidEmail(String email) {
//        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@(gmail|email)\\.com$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidAddress(String Address){
        String pattern = "^[^!@#$%^&*()_+=\\[\\]{};:'\"\\\\|<>.?~`]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(Address);
        return matcher.matches();
    }

    //kiểm tra tên
    public static boolean isValidName(String name) {
        String pattern = "^[^\\d!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>/?]*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(name);
        return matcher.matches();
    }
    //kiểm tra mật khẩu
    public static boolean isValidPass(String pass) {
        String pattern = "^[^!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>/?]*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(pass);
        return matcher.matches();
    }
}
