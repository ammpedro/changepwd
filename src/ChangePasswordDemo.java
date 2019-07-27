import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordDemo {

    private String allowedCharacters = "[0-9a-zA-Z!@#$&*]*";
    private String specialChars = "[!@#$&*]";
    private String fakePassword = "fakePassword";
    private String currentPassword = "fakePassword";

    void setFakePassword(String password) {
        this.fakePassword = password;
    }

    void setCurrentPassword(String password) { this.currentPassword = password; }

    String getCurrentPassword() { return fakePassword; }

    boolean hasAtLeast18Characters(String password) {
        return password.length() >= 18;
    }

    boolean hasValidAlphaCharacters(String password) {
        return Pattern.matches(allowedCharacters, password);
    }

    boolean hasOneOfEachAlphaCharacter(String password) {
        return ( Pattern.matches(".*[\\p{javaDigit}].*", password) &&
                Pattern.matches(".*[\\p{javaUpperCase}].*", password) &&
                Pattern.matches(".*[\\p{javaLowerCase}].*", password) &&
                Pattern.matches(".*" + specialChars + ".*", password));
    }

    boolean hasAtMost4RepeatCharacters(String password) {
        Pattern regex = Pattern.compile("(.)\\1{4}");
        Matcher matcher = regex.matcher(password);

        return !matcher.matches();
    }

    int getCharacterCount(String pattern, String password){
        int charCount = 0;
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(password);

        while (matcher.find())
            charCount++;
        return charCount;
    }

    boolean hasLessThan5SpecialCharacters(String password) {
        return getCharacterCount(specialChars, password) < 5;
    }

    boolean hasAtMost50PercentNumbers(String password) {
        float percentAlphaCharacters = (float) getCharacterCount("[\\p{javaDigit}]", password) / password.length() * 100;
        return percentAlphaCharacters <= 50;
    }

    boolean isValidOldPassword(String password) {
        return getCurrentPassword().equals(password);
    }

    float getPasswordSimilarity(String password1, String password2) {
        int counter = Integer.min(password1.length(), password2.length());
        float charMatch = 0;

        for (int x = 0; x < counter; x++)
            if (password1.charAt(x) == password2.charAt(x))
                charMatch++;

        return charMatch / Integer.max(password1.length(), password2.length()) * 100;
    }

    boolean isNewPasswordDifferent(float threshold, String password1, String password2) {
        return getPasswordSimilarity(password1, password2) < threshold;
    }
    
    boolean isValidNewPassword(String password) {
        return (hasAtLeast18Characters(password) &&
                hasValidAlphaCharacters(password) &&
                hasOneOfEachAlphaCharacter(password) &&
                hasAtMost4RepeatCharacters(password) &&
                hasLessThan5SpecialCharacters(password) &&
                hasAtMost50PercentNumbers(password));
    }

    boolean changePassword(String oldPassword, String newPassword) {
        
        if ( isValidNewPassword(newPassword) && 
                isValidOldPassword(oldPassword) && 
                isNewPasswordDifferent(80.0F, oldPassword, newPassword)) {

            setCurrentPassword(newPassword);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        ChangePasswordDemo demo = new ChangePasswordDemo();

        System.out.println(demo.changePassword(args[0], args[1]));
    }
}
