import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class User {
    private final String libraryNumber;
    private final String password;
    private boolean loginStatus = false;
    private final List<Item> checkoutItems;

    private final String name;
    private final String email;
    private final String phoneNumber;


    public User(final String libraryNumber, final String password, final String name,
                final String email, final String phoneNumber) {
        checkoutItems = new ArrayList<>();
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    final void addCheckOutItem(final Item item) {
        checkoutItems.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(libraryNumber, user.libraryNumber) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, password);
    }

    final void setLoginStatus(boolean state) {
        this.loginStatus = state;
    }

    final boolean isLoggedIn() {
        return this.loginStatus;
    }

    final boolean isInFormat() {
        return (this.libraryNumber.length() == 7);
    }

    final boolean isCheckout(final String name) {
        for (Item item : checkoutItems) {
            return (item.hasItem(name));
        }
        return false;
    }

    final void returnItem(final String name) {
        for (Item item : checkoutItems) {
            if (item.hasItem(name)) {
                checkoutItems.remove(item);
                return;
            }
        }
    }

    final String getUserNumber() {
        return this.libraryNumber;
    }

    final String getMyProfile() {
        if (this.loginStatus) {
            String profile = this.name + " ," + this.email + " ," + this.phoneNumber;
            return profile;
        }
        return "login first";
    }

    final boolean authenticate(String libraryNumber, String password) {
        return (this.libraryNumber.equals(libraryNumber) && this.password.equals(password));
    }
}
