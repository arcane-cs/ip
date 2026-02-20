package gigi.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for tasks created by the user
 */
public abstract class Task {
    private boolean isDone;
    private final String task;
    private final List<String> tags;

    /**
     * Constructs a new Task with a description and initializes it as not done.
     * @param task The description of the task
     * @throws IllegalArgumentException If the task description is empty.
     */
    public Task(String task) {
        this.isDone = false;
        if (task.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.task = task;
        this.tags = new ArrayList<>();
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public abstract String serialize();

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public String getTag() {
        return String.join(", ", tags);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
}
