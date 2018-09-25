import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class LibraryManagementTest {
    private List<Book> listOfBooks;
    InputDriver inputDriver;
    OutputDriver outputDriver;
    LibraryManagement libraryManagement;

    @BeforeEach
    public void setUp() {
        inputDriver = mock(InputDriver.class);
        outputDriver = mock(OutputDriver.class);
        libraryManagement = new LibraryManagement(inputDriver, outputDriver);
        listOfBooks = new ArrayList<>();
        listOfBooks.add(new Book("Head First Java", "Kathy Sierra", 2003, TypeOfItem.BOOK));
        listOfBooks.add(new Book("You can win", "Shiv Khera", 1998, TypeOfItem.BOOK));
    }

    @DisplayName("test for printing welcome when staring the project")
    @Test
    public void testPrintWelcome() {
        libraryManagement.printWelcome();
        verify(outputDriver).print("Welcome to Library Management System");
    }

    @DisplayName("test whether it is printing list of books or not")
    @Test
    public void testPrintListOfBooks() {

        libraryManagement.printLibraryBooks();

        verify(outputDriver).print(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003));
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("select list books from main menu options")
    @Test
    public void testChooseMenu() {

        when(inputDriver.readMenuInput()).thenReturn(1).thenReturn(0);
        libraryManagement.chooseMenu(1);

        verify(outputDriver).print(String.format("%-25s %8s %8s", "Head First Java", "Kathy Sierra", 2003));
        verify(outputDriver).print(String.format("%-25s %8s %8s", "You can win", "Shiv Khera", 1998));
    }

    @DisplayName("if selects an invalid option it should return select valid option")
    @Test
    public void testInvalidOption() {

        when(inputDriver.readMenuInput()).thenReturn(-1).thenReturn(0);
        libraryManagement.chooseMenu(-1);

        verify(outputDriver).print("Select a valid option!");
    }

    @DisplayName("test print menu")
    @Test
    public void testPrintMenu() {
        libraryManagement.printMenu();
        verify(outputDriver).print("Enter\n1.List Books   2.checkout book   3.return book  \n" +
                "4.List Movies      5.Checkout movie   6.Return movie  0.quit \n");
    }
}
