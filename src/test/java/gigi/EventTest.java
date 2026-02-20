package gigi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gigi.task.Event;

/**
 * This class contains unit tests to verify the string representation of Event tasks
 * and the validation logic for task descriptions during initialization.
 */
public class EventTest {
    /**
     * Verifies that the toString method correctly formats the event identifier,
     * start date, and end date into the expected UI string.
     */
    @Test
    public void testEventFormatting() {
        Event event = new Event("project meeting", "2026-12-15", "2026-12-16");
        String output = event.toString();
        System.out.println(output);
        assertTrue(output.contains("[E]"));
        assertTrue(output.contains("from: Dec 15 2026"));
        assertTrue(output.contains("to: Dec 16 2026"));
    }

    /**
     * Tests the ability to toggle the completion status of an event and
     * confirms the visual representation in the toString output.
     */
    @Test
    public void testMarkUnmark() {
        Event event = new Event("concert", "2026-12-15", "2026-12-16");
        event.markDone();
        assertTrue(event.toString().contains("[X]"));
        event.unmarkDone();
        assertTrue(event.toString().contains("[ ]"));
    }
}
