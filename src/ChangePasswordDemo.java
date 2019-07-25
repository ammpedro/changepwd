import java.util.regex.Pattern;

public class ChangePasswordDemo {

    static boolean hasAtLeast18Characters(String password) {
        return password.length() >= 18;
    }

    static boolean hasValidAlphaCharacters(String password) {
        String regex = "[0-9a-zA-Z!@#$&*]*";

        return Pattern.matches(regex, password);
    }

    static boolean changePassword(String oldPassword, String newPassword) {
        if ( hasAtLeast18Characters(newPassword) &&
                hasValidAlphaCharacters(newPassword))
            return true;

        return false;
    }
}
