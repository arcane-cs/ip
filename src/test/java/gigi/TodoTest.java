package gigi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gigi.task.Todo;

/**
 * Test suite for the Todo class.
 * Focuses on verifying string representation and status transitions.
 */
public class TodoTest {
    /**
     * Verifies that a Todo task is correctly formatted with the [T] identifier
     * and empty tags by default.
     */
    @Test
    public void testStringFormatting() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book\n\tTags: ", todo.toString());
    }

    /**
     * Confirms that marking a Todo as done updates its string representation
     * to show the [X] completion status.
     */
    @Test
    public void testMarkingAsDone() {
        Todo todo = new Todo("borrow book");
        todo.markDone();
        assertEquals("[T][X] borrow book\n\tTags: ", todo.toString());
    }
}
