package gigi.task;

import java.util.Iterator;
import java.util.List;

/**
 * Manages a list of tasks and provides methods to manipulate and query them.
 * Implements {@code Iterable<Task>} to allow for easy iteration over the task collection.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        int oldSize = tasks.size();
        tasks.remove(index);
        assert tasks.size() == oldSize - 1;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Marks the task at the specified index as done.
     * @param index The index of the task.
     */
    public void markTask(int index) {
        assert index >= 0;
        assert index < tasks.size();
        tasks.get(index).markDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmarkDone();
    }

    /**
     * Searches for tasks containing the query string in their description or tags.
     * @param query The search string.
     * @return A formatted string of matching tasks.
     */
    public String findString(String query) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(query)) {
                sb.append("\t")
                        .append(i + 1)
                        .append(". ")
                        .append(tasks.get(i))
                        .append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * @return A numbered list of tasks.
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    public void addTag(int index, String tag) {
        tasks.get(index).addTag(tag);
    }

    public void removeTag(int index, String tag) {
        tasks.get(index).removeTag(tag);
    }

    public String printTask(int index) {
        return tasks.get(index).toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
