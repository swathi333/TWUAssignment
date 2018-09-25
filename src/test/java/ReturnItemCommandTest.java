import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ReturnItemCommandTest {

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

    @DisplayName("test for returning the item")
    @Test
    public void testReturnTheBook() {

        when(inputDriver.readInput()).thenReturn("Head First Java");
        new CheckOutCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        new ReturnItemCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");
        verify(outputDriver).print("Thank you for returning the item.");
    }


    @DisplayName("if we return the book without checkout, it should print ,not a valid book")
    @Test
    public void testReturnInvalidBook() {

        when(inputDriver.readInput()).thenReturn("Head First Java");
        new ReturnItemCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("That is not a valid item to return.");
    }

    @DisplayName("if return the book and display them, all books shoulb be displayed")
    @Test
    public void testReturnTheBookAndDisplay() {

        when(inputDriver.readInput()).thenReturn("Head First Java");
        new CheckOutCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        when(inputDriver.readInput()).thenReturn("Head First Java");
        new ReturnItemCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");
        verify(outputDriver).print("Thank you for returning the item.");
        verify(outputDriver).print(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003));
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("test returning a movie after check out of it")
    @Test
    public void testReturnMovieAfterCheckOut() {

        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        new CheckOutCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);

        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        new ReturnItemCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);

        verify(outputDriver).print("Thank you! Enjoy the item");
        verify(outputDriver).print("Thank you for returning the item.");
    }

    @DisplayName("test returning a movie without check out of it")
    @Test
    public void testReturnMovieWithoutCheckOut() {

        when(inputDriver.readInput()).thenReturn("Alice In Wonder Land");
        new ReturnItemCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);

        verify(outputDriver).print("That is not a valid item to return.");
    }
}
