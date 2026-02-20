package gigi.task;

import java.util.ArrayList;
import java.util.List;

public abstract class Task {
    boolean isDone;
    final String task;
    List<String> tags;

    public Task (String task) {
        this.isDone = false;
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
