import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class ChangePasswordDemoTest {

    private ChangePasswordDemo sut = new ChangePasswordDemo();

    @Test
    public void shouldHaveAtLeast18AlphaCharacters() {
        Assert.assertFalse(sut.hasAtLeast18Characters(""));
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
    public void shouldHaveAtLeastOneOfEachAplhaCharacter() {
        Assert.assertFalse(sut.hasOneOfEachAlphaCharacter("./?"));
        Assert.assertFalse(sut.hasOneOfEachAlphaCharacter("1a@"));

        Assert.assertTrue(sut.hasOneOfEachAlphaCharacter("aA1$./?"));
        Assert.assertTrue(sut.hasOneOfEachAlphaCharacter("1Aa@"));
        Assert.assertTrue(sut.hasOneOfEachAlphaCharacter("$$AAzz00"));
    }

    @Test
    public void shouldAllowAtMost4RepeatCharacters() {
        Assert.assertTrue(sut.hasAtMost4RepeatCharacters("a"));
        Assert.assertTrue(sut.hasAtMost4RepeatCharacters("aa"));
        Assert.assertTrue(sut.hasAtMost4RepeatCharacters("aaaa"));
        Assert.assertTrue(sut.hasAtMost4RepeatCharacters("abababab"));
        Assert.assertFalse(sut.hasAtMost4RepeatCharacters("aaaaa"));
        Assert.assertFalse(sut.hasAtMost4RepeatCharacters("ZZZZZ"));
        Assert.assertFalse(sut.hasAtMost4RepeatCharacters("77777"));
        Assert.assertFalse(sut.hasAtMost4RepeatCharacters("*****"));
    }

    @Test
    public void shouldHaveLessThan5SpecialCharacters() {
        Assert.assertTrue(sut.hasLessThan5SpecialCharacters("*"));
        Assert.assertTrue(sut.hasLessThan5SpecialCharacters("!!!@"));
        Assert.assertFalse(sut.hasLessThan5SpecialCharacters("$$$$$"));
    }

    @Test
    public void shouldHaveAtMost50PercentNumbers() {
        Assert.assertTrue(sut.hasAtMost50PercentNumbers("a"));
        Assert.assertTrue(sut.hasAtMost50PercentNumbers("1A"));
        Assert.assertTrue(sut.hasAtMost50PercentNumbers("z0Z"));
        Assert.assertTrue(sut.hasAtMost50PercentNumbers("$0!"));
        Assert.assertFalse(sut.hasAtMost50PercentNumbers("1234BC$"));
        Assert.assertFalse(sut.hasAtMost50PercentNumbers("11A"));
        Assert.assertFalse(sut.hasAtMost50PercentNumbers("1234???"));

    }

    @Test
    public void shouldReturnPasswordSimilarity() {
        Assert.assertThat(sut.getPasswordSimilarity("a", "b"), is(0.0F));
        Assert.assertThat(sut.getPasswordSimilarity("a", "A"), is(0.0F));
        Assert.assertThat(sut.getPasswordSimilarity("a", "@"), is(0.0F));
        Assert.assertThat(sut.getPasswordSimilarity("ab", "ba"), is(0.0F));
        Assert.assertThat(sut.getPasswordSimilarity("ab", "ac"), is(50.0F));
        Assert.assertThat(sut.getPasswordSimilarity("aba", "ab"), is(66.66667F));
        Assert.assertThat(sut.getPasswordSimilarity("a", "a"), is(100.0F));

        Assert.assertThat(sut.getPasswordSimilarity("tacocat", "tacobat"), is(85.71429F));
        Assert.assertThat(sut.getPasswordSimilarity("t@c0c4T", "t@c0c4t"), is(85.71429F));
    }

    @Test
    public void shouldNotAllowChangePassword() {
        sut.setFakePassword("ThisIsAFakePassw0rd!");
        Assert.assertFalse("Verify that invalid old password returns false",
                sut.changePassword("ThisIsAFakePassword1!", "ChangeToNewPassw0rd!"));

        Assert.assertFalse("Verify that password.length() < 18 returns false",
                sut.changePassword("ThisIsAFakePassw0rd!", "n5kpD5pfVP"));

        Assert.assertFalse("Verify that password with invalid characters returns false",
                sut.changePassword("ThisIsAFakePassw0rd!", "2Qh97QJmyKJxJXNhp?"));

        Assert.assertFalse("Verify that password with repeating characters returns false",
                sut.changePassword("ThisIsAFakePassw0rd!", "2Qh97QQQQQJxJXNhp?"));

        Assert.assertFalse("Verify that password with more than 4 special characters returns false",
                sut.changePassword("ThisIsAFakePassw0rd!", "Th!s!s@FakeP@ssw0rd!"));

        Assert.assertFalse("Verify that password with more than 50% numbers returns false",
                sut.changePassword("ThisIsAFakePassw0rd!", "12345678900abcdefghi"));

        Assert.assertFalse("Verify that password with similar old and new passwords returns false",
                sut.changePassword("ThisIsAFakePassw0rd!", "This1sAFakePassw0rd!"));
    }

    @Test
    public void shouldAllowChangePassword() {
        sut.setFakePassword("ThisIsAFakePassword1!");
        Assert.assertTrue("Verify that valid old password returns true",
                sut.changePassword("ThisIsAFakePassword1!", "ChangeToNewPassw0rd!"));

        Assert.assertTrue("Verify that password with valid characters and length returns true",
                sut.changePassword("ThisIsAFakePassword1!", "2Qh97QQQyKJxJXNhp@"));
    }


}
