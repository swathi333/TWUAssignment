import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrintCommandTest {
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
        user = new User("1234567", "qwerty", "swathi",
                "swathi.manthri@thoughtworks.com", "1111111111");
    }

    @DisplayName("test whether we are getting list of movies or not")
    @Test
    public void testGetListOfMoviesAsStrings() {

        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);

        verify(outputDriver).print(String.format("%-25s %8s %8s %8s", "Alice In Wonder Land", 2000, "Alice", 9));
        verify(outputDriver).print(String.format("%-25s %8s %8s %8s", "Wonder women", 2002, "Women", 10));

    }

    @DisplayName("test whether we are getting list of books or not")
    @Test
    public void testGetListOfBooksAsStrings() {

        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003));
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));

    }

    @DisplayName("after checking out a movie it should return the remaining movies")
    @Test
    public void testGetListOfMoviesAsStringsAfterCheckoutMovie() {

        library.checkOutItem("Alice In Wonder Land", user);
        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.MOVIE,userAccounts);

        verify(outputDriver).print(String.format("%-25s %8s %8s %8s", "Wonder women", 2002, "Women", 10));
    }

    @DisplayName("after checking out a book it should return only remaining books")
    @Test
    public void testGetListOfBooksAsStringsAfterCheckoutBook() {

        library.checkOutItem("Head First Java", user);
        new PrintCommand().action(library, outputDriver, inputDriver, TypeOfItem.BOOK,userAccounts);

        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }
}
