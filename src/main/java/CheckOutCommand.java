//Checkout allows take the item
public class CheckOutCommand implements Command {
    public void action(final Library library, final OutputDriver outputDriver,
                       final InputDriver inputDriver, final TypeOfItem typeOfItem,UserAccounts userAccounts) {
        String name = inputDriver.readInput();
        if (library.isCheckOut(name) || !(library.isValidItem(name))) {
            outputDriver.print("That item is not available.");
            return;
        }
        User user = userAccounts.loggedInUser;
        library.checkOutItem(name, user);
        outputDriver.print("Thank you! Enjoy the item");
    }
}
