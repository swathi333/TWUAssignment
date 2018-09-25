import java.util.ArrayList;
import java.util.List;

//Library consist of all books
public class Library {
    private final List<Item> listOfItems;
    private final List<Item> availableListOfItems;
    private final List<User> usersWithCheckOutItem;

    Library() {
        usersWithCheckOutItem = new ArrayList<>();
        availableListOfItems = new ArrayList<>();
        listOfItems = new ArrayList<>();
        addItems();
    }

    private final void addItems() {
        listOfItems.add(new Book("Head First Java", "Kathy Sierra", 2003, TypeOfItem.BOOK));
        listOfItems.add(new Book("You can win", "Shiv Khera", 1998, TypeOfItem.BOOK));
        listOfItems.add(new Movie("Alice In Wonder Land", 2000, "Alice", 9, TypeOfItem.MOVIE));
        listOfItems.add(new Movie("Wonder women", 2002, "Women", 10, TypeOfItem.MOVIE));
        for (Item item : listOfItems) {
            availableListOfItems.add(item);
        }
    }

    final List<String> getListOfItemDetails(final TypeOfItem typeOfItem) {
        List<String> listOfBookDetailsToBeDisplayed = new ArrayList<>();
        for (Item item : availableListOfItems) {
            if (typeOfItem.equals(item.getTypeOfItem())) {
                listOfBookDetailsToBeDisplayed.add(item.getItemDetails());
            }
        }
        return listOfBookDetailsToBeDisplayed;
    }

    final void checkOutItem(final String itemName, final User user) {
        for (Item item : availableListOfItems) {
            if (item.hasItem(itemName)) {
                availableListOfItems.remove(item);
                user.addCheckOutItem(item);
                usersWithCheckOutItem.add(user);
                return;
            }
        }
    }

    final boolean isValidItem(final String itemName) {
        for (Item item : listOfItems) {
            if (item.hasItem(itemName)) {
                return true;
            }
        }
        return false;
    }

    final boolean isCheckOut(final String itemName) {
        for (Item item : availableListOfItems) {
            if (item.hasItem(itemName)) {
                return false;
            }
        }
        return true;
    }

    final void returnItem(final String itemName, final User user) {
        for (Item item : listOfItems) {
            if (item.hasItem(itemName)) {
                availableListOfItems.add(item);
                usersWithCheckOutItem.remove(user);
                return;
            }
        }
    }

    final String whoCheckOut(final String item) {
        for (User user : usersWithCheckOutItem) {
            if (user.isCheckout(item)) {
                return user.getUserNumber();
            }
        }
        return "no one checked out it";
    }
}
