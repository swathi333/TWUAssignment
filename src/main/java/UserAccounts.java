import java.util.ArrayList;
import java.util.List;

public class UserAccounts {
    final List<User> users;
    User loggedInUser;

    UserAccounts() {
        users = new ArrayList<>();
        users.add(new User("1234567", "qwerty", "swathi",
                "swathi.manthri@thoughtworks.com", "1111111111"));
        users.add(new User("1111111", "onetwo", "manasa",
                "manasa.manthri@gmail.com", "2222222222"));
    }

    final User login(final String libraryNumber, final String password) {
        for (User user : users) {
            if (user.authenticate(libraryNumber, password) && user.isInFormat()) {
                user.setLoginStatus(true);
                loggedInUser = user;
                return user;
            }
        }
        return null;
    }

    final void logout(final User user) {
        for (User userDetails : users) {
            if (userDetails.equals(user)) {
                user.setLoginStatus(false);
                loggedInUser = null;
                return;
            }
        }
    }
}
