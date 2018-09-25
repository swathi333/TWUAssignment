//Command to return book
public class ReturnItemCommand implements Command {
    public void action(Library library, OutputDriver outputDriver,
                       InputDriver inputDriver, TypeOfItem typeOfItem, UserAccounts userAccounts) {

        String name = inputDriver.readInput();
        User user = userAccounts.loggedInUser;
        if ((library.isCheckOut(name) && library.isValidItem(name))) {
            user.returnItem(name);
            library.returnItem(name, user);
            outputDriver.print("Thank you for returning the item.");
            return;
        }

        outputDriver.print("That is not a valid item to return.");
    }
}
