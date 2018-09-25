import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user = new User("1234567", "qwerty", "swathi",
            "swathi.manthri@thoughtworks.com", "1111111111");
    UserAccounts userAccounts = new UserAccounts();

    @DisplayName("return tru login status after logging in")
    @Test
    public void testLoginStatus() {
        User loggedInUser = userAccounts.login("1234567", "qwerty");
        assertFalse(user.isLoggedIn());
        assertTrue(loggedInUser.isLoggedIn());
    }

    @DisplayName("check set login status")
    @Test
    public void testCheckLoginStatus() {
        user.setLoginStatus(true);
        assertTrue(user.isLoggedIn());
    }

    @DisplayName("check library number format")
    @Test
    public void testLibraryNumberFormat() {
        assertTrue(user.isInFormat());
    }

    @DisplayName("get user number")
    @Test
    public void testGetUserNumber() {
        assertEquals("1234567", user.getUserNumber());
    }

    @DisplayName("get user profile only after login")
    @Test
    public void testGetUserProfileOnlyAfterLogin() {

        User loggedInUser = userAccounts.login("1234567", "qwerty");
        assertEquals("swathi ,swathi.manthri@thoughtworks.com ,1111111111", loggedInUser.getMyProfile());
    }

    @DisplayName("test authenticate method")
    @Test
    public void testAuthenticate() {
        User loggedInUser = userAccounts.login("1234567", "qwerty");

        assertTrue(loggedInUser.authenticate("1234567", "qwerty"));
    }

    @DisplayName("if user adds the item,it should be present in checkout list")
    @Test
    public void testAddCheckOut() {
        User loggedInUser = userAccounts.login("1234567", "qwerty");
        loggedInUser.addCheckOutItem(new Book("You can win", "Shiv khera",
                1998, TypeOfItem.BOOK));

        assertTrue(loggedInUser.isCheckout("You can win"));
    }

    @DisplayName("if user returns the item,it should not be present in checkout list")
    @Test
    public void testReturn() {
        User loggedInUser = userAccounts.login("1234567", "qwerty");
        loggedInUser.addCheckOutItem(new Book("You can win", "Shiv khera",
                1998, TypeOfItem.BOOK));
        loggedInUser.returnItem("You can win");
        assertFalse(loggedInUser.isCheckout("You can win"));
    }

}
