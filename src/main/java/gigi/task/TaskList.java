package gigi.task;

import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmarkDone();
    }

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

    public String printTask(int index) {
        return tasks.get(index).toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

}