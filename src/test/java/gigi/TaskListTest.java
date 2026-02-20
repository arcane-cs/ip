package gigi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import gigi.task.Task;
import gigi.task.Todo;
import gigi.task.TaskList;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;
    private ArrayList<Task> internalList;

    @BeforeEach
    public void setUp() {
        internalList = new ArrayList<>();
        taskList = new TaskList(internalList);
    }

    @Test
    public void addTask_incrementsSize() {
        Task task = new Todo("test task");
        taskList.addTask(task);
        assertEquals(1, taskList.size(), "Size should be 1 after adding a task");
    }

    @Test
    public void deleteTask_decrementsSize() {
        taskList.addTask(new Todo("task 1"));
        taskList.addTask(new Todo("task 2"));
        taskList.deleteTask(0);
        assertEquals(1, taskList.size(), "Size should be 1 after deleting one of two tasks");
    }

    @Test
    public void markTask_updatesTaskStatus() {
        //AI was used to generate this Unit Test
        Task task = new Todo("test task");
        taskList.addTask(task);
        taskList.markTask(0);
        // This assumes your Task class has an isDone() or similar check
        assertTrue(task.toString().contains("[X]"), "Task should be marked as done in toString");
    }

    @Test
    public void findString_returnsMatchingTasks() {
        //AI was used to generate this Unit Test
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write code"));

        String result = taskList.findString("book");
        assertTrue(result.contains("read book"), "Result should contain the matching task");
        assertFalse(result.contains("write code"), "Result should not contain non-matching tasks");
    }

    @Test
    public void printList_formatsCorrectly() {
        //AI was used to generate this Unit Test
        taskList.addTask(new Todo("task 1"));
        String result = taskList.printList();
        // Matching the format: \t1. [task description]\n
        assertTrue(result.startsWith("\t1. "), "List should be formatted with tabs and indexing");
    }

}