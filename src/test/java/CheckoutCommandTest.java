import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CheckoutCommandTest {

    InputDriver inputDriver;
    OutputDriver outputDriver;
    Library library = new Library();
    UserAccounts userAccounts;
    User user = new User("1234567", "qwerty", "swathi",
            "swathi.manthri@thoughtworks.com", "1111111111");

    @BeforeEach
    public void setUp() {
        userAccounts = new UserAccounts();
        userAccounts.loggedInUser = user;
        inputDriver = mock(InputDriver.class);
        outputDriver = mock(OutputDriver.class);
    }

    @DisplayName("test for checking out a movie")
    @Test
    public void testGetListOfMoviesAsStringsAfterCheckoutMovie() {

        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        new CheckOutCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");
    }

    @DisplayName("after checkout of a book, it should not be displayed in the list")
    @Test
    public void testGetListOfBookAfterCheckingOutOfBook() {

        when(inputDriver.readInput()).thenReturn("Head First Java");
        new CheckOutCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);
        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("if we give invalid book to checkout, it should print that book is not available" +
            " and print should give all the books")
    @Test
    public void testCheckingOutOfInvalidBook() {

        when(inputDriver.readInput()).thenReturn("Head");
        new CheckOutCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);
        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("That item is not available.");
        verify(outputDriver).print(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003));
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("if we give invalid movie to checkout, it should print that item is not available " +
            "and print should give all the movies")
    @Test
    public void testCheckingOutOfInvalidMovie() {

        when(inputDriver.readInput()).thenReturn("Alice");
        new CheckOutCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);
        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);

        verify(outputDriver).print("That item is not available.");
        verify(outputDriver).print(String.format("%-25s %8s %8s %8s", "Alice In Wonder Land", 2000, "Alice", 9));
        verify(outputDriver).print(String.format("%-25s %8s %8s %8s", "Wonder women", 2002, "Women", 10));
    }
}
