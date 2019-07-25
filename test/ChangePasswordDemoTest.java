import org.junit.Assert;
import org.junit.Test;

public class ChangePasswordDemoTest {

    private ChangePasswordDemo sut = new ChangePasswordDemo();

    @Test
    public void shouldHaveAtLeast18AlphaCharacters() {
        Assert.assertFalse(sut.hasAtLeast18Characters("a"));
        Assert.assertFalse(sut.hasAtLeast18Characters("aaaaaaaaaaaaaaaaa"));
        Assert.assertTrue(sut.hasAtLeast18Characters("aaaaaaaaaaaaaaaaaa"));
        Assert.assertTrue(sut.hasAtLeast18Characters("aaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void shouldHaveAlphaNumericAndSpecialCharacters() {
        Assert.assertFalse(sut.hasValidAlphaCharacters("./?"));
        Assert.assertFalse(sut.hasValidAlphaCharacters("aA1$./?"));
        Assert.assertTrue(sut.hasValidAlphaCharacters("123"));
        Assert.assertTrue(sut.hasValidAlphaCharacters("ABC"));
        Assert.assertTrue(sut.hasValidAlphaCharacters("abc"));
        Assert.assertTrue(sut.hasValidAlphaCharacters("@#$&*"));
        Assert.assertTrue(sut.hasValidAlphaCharacters("1Aa@"));
    }

    @Test
    public void shouldNotAllowChangePassword() {
        Assert.assertFalse(sut.changePassword("", ""));
    }

    @Test
    public void shouldAllowChangePassword() { }


}
