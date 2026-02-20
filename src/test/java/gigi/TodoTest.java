package gigi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import gigi.task.Todo;

public class TodoTest {
    @Test
    public void testStringFormatting() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book\n\tTags: ", todo.toString());
    }

    @Test
    public void testMarkingAsDone() {
        Todo todo = new Todo("borrow book");
        todo.markDone();
        assertEquals("[T][X] borrow book\n\tTags: ", todo.toString());
    }
}