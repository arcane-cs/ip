package gigi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gigi.task.Task;
import gigi.task.TaskList;
import gigi.task.Todo;

/**
 * Unit tests for the TaskList class.
 * This class verifies the core functionality of the task management system,
 * including addition, deletion, marking status, and searching.
 */
public class TaskListTest {
    private TaskList taskList;
    private ArrayList<Task> internalList;

    /**
     * Initializes the TaskList and its underlying storage before each test.
     */
    @BeforeEach
    public void setUp() {
        internalList = new ArrayList<>();
        taskList = new TaskList(internalList);
    }

    /**
     * Verifies that adding a task correctly increases the count of tasks.
     */
    @Test
    public void addTask_incrementsSize() {
        Task task = new Todo("test task");
        taskList.addTask(task);
        assertEquals(1, taskList.size(), "Size should be 1 after adding a task");
    }

    /**
     * Verifies that deleting a task correctly decreases the count of tasks.
     */
    @Test
    public void deleteTask_decrementsSize() {
        taskList.addTask(new Todo("task 1"));
        taskList.addTask(new Todo("task 2"));
        taskList.deleteTask(0);
        assertEquals(1, taskList.size(), "Size should be 1 after deleting one of two tasks");
    }

    /**
     * Confirms that marking a task changes its status to completed in its string representation.
     */
    @Test
    public void markTask_updatesTaskStatus() {
        //AI was used to generate this Unit Test
        Task task = new Todo("test task");
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(task.toString().contains("[X]"), "Task should be marked as done in toString");
    }

    /**
     * Tests the search functionality to ensure only tasks matching the query are returned.
     */
    @Test
    public void findString_returnsMatchingTasks() {
        //AI was used to generate this Unit Test
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write code"));

        String result = taskList.findString("book");
        assertTrue(result.contains("read book"), "Result should contain the matching task");
        assertFalse(result.contains("write code"), "Result should not contain non-matching tasks");
    }

    /**
     * Checks that the printed list adheres to the expected tabbed and numbered format.
     */
    @Test
    public void printList_formatsCorrectly() {
        //AI was used to generate this Unit Test
        taskList.addTask(new Todo("task 1"));
        String result = taskList.printList();
        assertTrue(result.startsWith("\t1. "), "List should be formatted with tabs and indexing");
    }
}
