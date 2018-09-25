import java.util.List;

//Manages the library application
public class LibraryManagement {
    private final OutputDriver outputDriver;
    private final InputDriver inputDriver;
    private Library library;
    UserAccounts userAccounts = new UserAccounts();

    LibraryManagement(final InputDriver inputDriver, final OutputDriver outputDriver) {
        library = new Library();
        this.inputDriver = inputDriver;
        this.outputDriver = outputDriver;
    }

    final void printWelcome() {
        this.outputDriver.print("Welcome to Library Management System");
    }

    final void printLibraryBooks() {
        List<String> listOfBookDetails = new Library().getListOfItemDetails(TypeOfItem.BOOK);
        for (String name : listOfBookDetails) {
            outputDriver.print(name);
        }
    }

    final void chooseMenu(final int input) {
        Menu menu[] = Menu.values();
        Menu invalidOption = Menu.INVALID_OPTION;
        int invalidOptionIndex = invalidOption.ordinal();
        if (input < 0 || input > menu.length) {
            menu[invalidOptionIndex].action(library, outputDriver,
                    inputDriver, menu[invalidOptionIndex].getType(),userAccounts);
            return;
        }
        menu[input].action(library, outputDriver, inputDriver, menu[input].getType(),userAccounts);
    }

    final void printMenu() {
        Menu.PRINT_MENU.action(library, outputDriver,
                inputDriver, TypeOfItem.BOOK, userAccounts);
    }
}
