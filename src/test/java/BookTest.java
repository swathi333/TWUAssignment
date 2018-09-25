import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {
    Book book;

    @BeforeEach
    public void setUp() {

       book = new Book("You can win", "Shiv khera", 1998, TypeOfItem.BOOK);
    }

    @DisplayName("returns You can win as book name")
    @Test
    public void testGetItemDetails() {

        assertEquals(book.getItemDetails(), String.format("%-25s %8s %8s", "You can win", "Shiv khera", 1998));
    }

    @DisplayName("check whether a book is available")
    @Test
    public void testHasItem() {

        assertTrue(book.hasItem("You can win"));
    }

    @DisplayName("get type of item as book")
    @Test
    public void testGetTypeOfItem() {

        assertEquals(TypeOfItem.BOOK,book.getTypeOfItem());
    }
}
