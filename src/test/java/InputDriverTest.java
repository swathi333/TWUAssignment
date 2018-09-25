import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputDriverTest {

    @Test
    public void testMenuInput() {
        setSystemIn("1\n");

        InputDriver inputDriver = new InputDriver();
        assertEquals(1, inputDriver.readMenuInput());
    }

    @Test
    public void testMenuString() {
        setSystemIn("Head First Java\n");

        InputDriver inputDriver = new InputDriver();
        assertEquals("Head First Java", inputDriver.readInput());
    }

    private void setSystemIn(String string) {

        System.setIn(new ByteArrayInputStream(string.getBytes()));
    }

    @AfterEach
    public void setup() {

        System.setIn(System.in);
    }
}
