package gigi;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gigi.task.Deadline;

/**
 * This class contains unit tests to verify the string representation of deadline tasks
 * and the validation logic for task descriptions during initialization.
 */
public class DeadlineTest {

    /**
     * Verifies that toString contains the [D] prefix and the formatted date.
     */
    @Test
    public void testStringFormatting() {
        // Assuming your app handles date strings or LocalDate
        Deadline deadline = new Deadline("submit IP", "2026-02-20");
        assertTrue(deadline.toString().contains("(by Feb 20 2026)"));
        assertTrue(deadline.toString().startsWith("[D]"));
    }

    /**
     * Ensures an IllegalArgumentException is thrown for empty descriptions.
     */
    @Test
    public void testEmptyDescription_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("", "2026-02-20");
        });
    }
}
