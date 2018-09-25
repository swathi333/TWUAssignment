import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAccountsTest {
    UserAccounts userAccounts;

    @BeforeEach
    public void setUp() {
        userAccounts = new UserAccounts();
    }

    @DisplayName("if user a logged in then user login status should be true")
    @Test
    public void checkOutWithLogin() {

        User loggedInUser = userAccounts.login("1234567", "qwerty");

        assertTrue(loggedInUser.isLoggedIn());
    }

    @DisplayName("if a user logged out then the user's login status should be false")
    @Test
    public void testLogOut() {
        User loggedInUser = userAccounts.login("1234567", "qwerty");
        userAccounts.logout(loggedInUser);

        assertFalse(loggedInUser.isLoggedIn());
    }
}
