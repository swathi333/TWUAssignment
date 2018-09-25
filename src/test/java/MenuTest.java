import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MenuTest {

    OutputDriver outputDriver = mock(OutputDriver.class);
    InputDriver inputDriver = mock(InputDriver.class);
    Library library = new Library();
    UserAccounts userAccounts = new UserAccounts();
    User user;
    @BeforeEach
    public void setUp() {
        user = new User("1234567", "qwerty", "swathi",
                "swathi.manthri@thoughtworks.com", "1111111111");
        userAccounts.loggedInUser = userAccounts.login("1234567","qwerty");
    }

    @DisplayName("test act method")
    @Test
    public void testActOfListBooks() {
        Menu menu = Menu.PRINT_BOOK;
        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003));
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("test Invalid menu option")
    @Test
    public void testInvalidMenu() {
        Menu menu = Menu.INVALID_OPTION;

        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);
        verify(outputDriver).print("Select a valid option!");
    }

    @DisplayName("book should not be displayed on checkout of it")
    @Test
    public void testCheckOutBook() {
        Menu menu = Menu.CHECKOUT_BOOK;
        when(inputDriver.readInput()).thenReturn("Head First Java");

        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);
        verify(outputDriver).print("Thank you! Enjoy the item");
    }

    @DisplayName("book should not be displayed on checkout of it")
    @Test
    public void testForBookNotPresent() {
        Menu menu = Menu.CHECKOUT_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);
        Menu.PRINT_MOVIE.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("return that book not available when we again checkout for the same book")
    @Test
    public void testForBookCheckoutWhenWeAlreadyCheckoutIt() {
        Menu menu = Menu.CHECKOUT_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);
        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");
        verify(outputDriver).print("That item is not available.");
    }

    @DisplayName("test for returning the book")
    @Test
    public void testReturnTheBook() {

        Menu menuCheckOut = Menu.CHECKOUT_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menuCheckOut.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        Menu menuReturn = Menu.RETURN_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menuReturn.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");

        verify(outputDriver).print("Thank you for returning the item.");
    }

    @DisplayName("if return the book which is already returned it gives not valid return message")
    @Test
    public void testReturnTwoTimes() {
        Menu menu = Menu.CHECKOUT_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        Menu menuReturn = Menu.RETURN_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menuReturn.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        menuReturn.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");

        verify(outputDriver).print("Thank you for returning the item.");

        verify(outputDriver).print("That is not a valid item to return.");
    }

    @DisplayName("if return the book and display them, all books shoulb be displayed")
    @Test
    public void testReturnTheBookAndDisplay() {
        Menu menu = Menu.CHECKOUT_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menu.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        Menu menuReturn = Menu.RETURN_BOOK;
        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        menuReturn.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        Menu.PRINT_BOOK.action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");

        verify(outputDriver).print("Thank you for returning the item.");

        verify(outputDriver).print(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003));
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("testPrintMenu")
    @Test
    public void testPrintMenu() {
        LibraryManagement libraryManagement = new LibraryManagement(inputDriver, outputDriver);
        libraryManagement.printMenu();

        verify(outputDriver).print("Enter\n1.List Books   2.checkout book   3.return book  \n" +
                "4.List Movies      5.Checkout movie   6.Return movie  0.quit \n");
    }
}
