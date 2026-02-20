package gigi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import gigi.task.Event;

public class EventTest {
    @Test
    public void testEventFormatting() {
        Event event = new Event("project meeting", "2026-12-15", "2026-12-16");
        String output = event.toString();
        System.out.println(output);
        assertTrue(output.contains("[E]"));
        assertTrue(output.contains("from: Dec 15 2026"));
        assertTrue(output.contains("to: Dec 16 2026"));
    }

    @Test
    public void testMarkUnmark() {
        Event event = new Event("concert", "2026-12-15", "2026-12-16");
        event.markDone();
        assertTrue(event.toString().contains("[X]"));
        event.unmarkDone();
        assertTrue(event.toString().contains("[ ]"));
    }
}