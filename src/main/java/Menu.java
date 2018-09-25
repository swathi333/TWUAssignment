
//Menu options
public enum Menu {
    QUIT("0.quit", new Command() {
        @Override
        public void action(Library library, OutputDriver outputDriver,
                           InputDriver inputDriver, TypeOfItem typeOfItem,UserAccounts userAccounts) {
            outputDriver.print("Thank you for visiting");
        }
    }, TypeOfItem.BOOK),

    PRINT_BOOK("1.Print list of books", new PrintCommand(), TypeOfItem.BOOK),

    CHECKOUT_BOOK("2.checkout book", new AuthorizedCommand(new CheckOutCommand()), TypeOfItem.BOOK),

    RETURN_BOOK("3.return book", new AuthorizedCommand(new ReturnItemCommand()), TypeOfItem.BOOK),

    PRINT_MOVIE("4.Print list of books", new PrintCommand(), TypeOfItem.MOVIE),

    CHECKOUT_MOVIE("5.checkout book", new AuthorizedCommand(new CheckOutCommand()), TypeOfItem.MOVIE),

    RETURN_MOVIE("6.return book", new AuthorizedCommand(new ReturnItemCommand()), TypeOfItem.MOVIE),

    LOGOUT( "7.Login", new Command() {
        @Override
        public void action(Library library, OutputDriver outputDriver,
                           InputDriver inputDriver, TypeOfItem typeOfItem, UserAccounts userAccounts) {
            userAccounts.logout(userAccounts.loggedInUser);
        }
    },TypeOfItem.BOOK),

    PRINT_MENU("", new Command() {
        @Override
        public void action(Library library, OutputDriver outputDriver,
                           InputDriver inputDriver, TypeOfItem typeOfItem,UserAccounts userAccounts) {
            outputDriver.print("Enter\n1.List Books   2.checkout book   3.return book  \n" +
                    "4.List Movies      5.Checkout movie   6.Return movie  0.quit \n");
            if(userAccounts.loggedInUser != null) {
                outputDriver.print("7.logout");
            }
        }
    }, TypeOfItem.BOOK),
    INVALID_OPTION("", new Command() {
        @Override
        public void action(Library library, OutputDriver outputDriver,
                           InputDriver inputDriver, TypeOfItem typeOfItem,UserAccounts userAccounts) {
            outputDriver.print("Select a valid option!");
        }
    }, TypeOfItem.BOOK);


    private final String printMessage;
    private final Command command;
    private final TypeOfItem type;
    UserAccounts userAccounts = new UserAccounts();
    Menu(final String printMessage, final Command command, final TypeOfItem type) {
        this.printMessage = printMessage;
        this.command = command;
        this.type = type;
    }

    String print() {
        return this.printMessage+"      ";
    }

    void action(Library library, OutputDriver outputDriver, InputDriver inputDriver,
                TypeOfItem type,UserAccounts userAccounts) {
        this.command.action(library, outputDriver, inputDriver, type, userAccounts);
    }

    TypeOfItem getType() {
        return this.type;
    }
}