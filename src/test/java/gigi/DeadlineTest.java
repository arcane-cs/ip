package gigi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import gigi.task.Deadline;

public class DeadlineTest {
    @Test
    public void testStringFormatting() {
        // Assuming your app handles date strings or LocalDate
        Deadline deadline = new Deadline("submit IP", "2026-02-20");
        assertTrue(deadline.toString().contains("(by Feb 20 2026)"));
        assertTrue(deadline.toString().startsWith("[D]"));
    }

    @Test
    public void testEmptyDescription_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("", "2026-02-20");
        });
    }
}