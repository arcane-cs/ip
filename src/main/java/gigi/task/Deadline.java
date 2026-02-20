package gigi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gigi.GigiException;

/**
 *  Handles Tasks with a 'by' parameter
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a new Deadline task with a specified description and due date.
     * This constructor attempts to parse the provided date string into a LocalDate.
     * If the string does not conform to the format (yyyy-mm-dd), a
     * GigiException is thrown with a descriptive error message.
     *
     * @param task The description of the task.
     * @param by The due date string in the format "yyyy-mm-dd".
     * @throws GigiException If the provided date string is not in the correct format.
     */
    public Deadline(String task, String by) {
        super(task);

        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new GigiException("Please use the format yyyy-mm-dd (e.g., 2019-01-15)");
        }
    }

    /**
     * Constructs a Deadline task with a description, a due date, and initial tags.
     * The due date must follow the format (yyyy-mm-dd).
     *
     * @param task The description of the task.
     * @param by The due date string (e.g., "2026-11-20").
     * @param tags A comma-separated string of tags to be assigned (e.g., "urgent, school").
     * @throws GigiException If the date string format is invalid.
     */
    public Deadline(String task, String by, String tags) {
        super(task);

        try {
            this.by = LocalDate.parse(by.trim());
            for (String tag: tags.split(", ")) {
                addTag(tag);
            }
        } catch (DateTimeParseException e) {
            throw new GigiException("Please use the format yyyy-mm-dd (e.g., 2019-01-15)");
        }
    }

    /**
     * Converts the task into a simplified string format suitable for file storage.
     * The format typically includes the task type, completion status, description,
     * and any relevant dates, separated by a specific delimiter (e.g., "|").
     *
     * @return A formatted string representing the task for persistent storage.
     */
    public String serialize() {
        return "D|" + super.toString() + "|" + this.by + "|" + getTag();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")"
                + "\n\tTags: " + getTag();
    }
}
