package gigi;

import gigi.task.TaskList;
import gigi.task.Todo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList(new ArrayList<>());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.deleteTask(0);
        });
    }

    @Test
    public void getTaskListAsString_multipleTasks_formattedCorrectly() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new Todo("task 1"));
        tasks.addTask(new Todo("task 2"));

        String output = tasks.printList();

        assertTrue(output.contains("1. [T][ ] task 1"));
        assertTrue(output.contains("2. [T][ ] task 2"));
    }
}