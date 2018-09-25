import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private User user;
    private Library library;

    @BeforeEach
    public void setUp() {

        user = new User("1234567", "qwerty", "swathi",
                "swathi.manthri@thoughtworks.com", "1111111111");
        library = new Library();
    }

    @DisplayName("test whether we are getting list of books or not")
    @Test
    public void testGetListOfBooksAsStrings() {

        List<String> listOfStringsOfBookDetails = library.getListOfItemDetails(TypeOfItem.BOOK);

        assertEquals(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003), listOfStringsOfBookDetails.get(0));
        assertEquals(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998), listOfStringsOfBookDetails.get(1));
    }

    @DisplayName("after checking out it should return only one book")
    @Test
    public void testGetListOfBooksAsStringsAfterRemovingBook() {
        library.checkOutItem("Head First Java", user);

        List<String> listOfStringsOfBookDetails = library.getListOfItemDetails(TypeOfItem.BOOK);

        assertNotEquals(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003), listOfStringsOfBookDetails.get(0));
        assertEquals(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998), listOfStringsOfBookDetails.get(0));
    }

    @DisplayName("test whether the book is checking out or not")
    @Test
    public void testIsCheckOut() {

        library.checkOutItem("Head First Java", user);

        assertTrue(library.isCheckOut("Head First Java"));
        assertFalse(library.isCheckOut("You can win"));
    }

    @DisplayName("test for return book")
    @Test
    public void testReturnBook() {

        library.checkOutItem("Head First Java", user);
        library.returnItem("Head First Java", user);

        assertFalse(library.isCheckOut("Head First Java"));
    }

    @DisplayName("test whether we are getting list of movies or not")
    @Test
    public void testGetListOfMoviesAsStrings() {

        List<String> listOfStringsOfItemDetails = library.getListOfItemDetails(TypeOfItem.MOVIE);

        assertEquals(String.format("%-25s %8s %8s %8s", "Alice In Wonder Land", 2000, "Alice", 9),
                listOfStringsOfItemDetails.get(0));
        assertEquals(String.format("%-25s %8s %8s %8s", "Wonder women", 2002, "Women", 10),
                listOfStringsOfItemDetails.get(1));
    }

    @DisplayName("after checking out it should not return all the movies")
    @Test
    public void testGetListOfMoviesAsStringsAfterCheckoutMovie() {

        library.checkOutItem("Alice In Wonder Land", user);
        List<String> listOfStringsOfItemDetails = library.getListOfItemDetails(TypeOfItem.MOVIE);

        assertNotEquals(String.format("%-25s %8s %8s %8s", "Alice In Wonder Land", 2000, "Alice", 9),
                listOfStringsOfItemDetails.get(0));
        assertEquals(String.format("%-25s %8s %8s %8s", "Wonder women", 2002, "Women", 10),
                listOfStringsOfItemDetails.get(0));
    }

    @DisplayName("who checkout the item")
    @Test
    public void testWhoCheckOutItem() {

        library.checkOutItem("Alice In Wonder Land", user);
        String result = library.whoCheckOut("Alice In Wonder Land");

        assertEquals("1234567", result);
    }

    @DisplayName("after check out and returning item,the user should not be present in checkout users list")
    @Test
    public void testCheckOutUsersListAfterReturn() {

        library.checkOutItem("Alice In Wonder Land", user);
        library.returnItem("Alice In Wonder Land", user);
        String result = library.whoCheckOut("Alice In Wonder Land");

        assertNotEquals(result, "1234567");
        assertEquals("no one checked out it", result);
    }

    @DisplayName("after check out ,the user should be present in checkout users list")
    @Test
    public void testCheckOutUsersList() {

        library.checkOutItem("Alice In Wonder Land", user);
        String result = library.whoCheckOut("Alice In Wonder Land");

        assertEquals(result, "1234567");
        assertNotEquals("no one checked out it", result);
    }
}

