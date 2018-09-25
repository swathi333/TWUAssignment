import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieTest {
    Movie movie;

    @BeforeEach
    public void setUp() {
        movie = new Movie("Wonder women", 2002, "Women", 10, TypeOfItem.MOVIE);
    }

    @DisplayName("returns Wonder women as movie name")
    @Test
    public void testGetItemDetails() {

        assertEquals(movie.getItemDetails(), String.format("%-25s %8s %8s %8s", "Wonder women", 2002, "Women", 10));
    }

    @DisplayName("check whether a movie is available")
    @Test
    public void testHasMovie() {

        assertTrue(movie.hasItem("Wonder women"));
    }

    @DisplayName("get type of item as movie")
    @Test
    public void testGetTypeOfItem() {

        assertEquals(TypeOfItem.MOVIE,movie.getTypeOfItem());
    }
}
