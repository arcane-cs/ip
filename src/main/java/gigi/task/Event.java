package gigi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gigi.GigiException;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an Event with description, start date, and end date.
     * @param task Task description.
     * @param from Start date in yyyy-mm-dd format.
     * @param to End date in yyyy-mm-dd format.
     * @throws GigiException If date parsing fails.
     */
    public Event(String task, String from, String to) {
        super(task);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new GigiException("Please use the format yyyy-mm-dd (e.g., 2019-10-15)");
        }
    }

    /**
     * Constructs an Event with description, start date, end date, and tags.
     * @param task Task description.
     * @param from Start date in yyyy-mm-dd format.
     * @param to End date in yyyy-mm-dd format.
     * @param tags Comma-separated tags.
     * @throws GigiException If date parsing fails.
     */
    public Event(String task, String from, String to, String tags) {
        super(task);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
            for (String tag: tags.split(", ")) {
                addTag(tag);
            }
        } catch (DateTimeParseException e) {
            throw new GigiException("Please use the format yyyy-mm-dd (e.g., 2019-10-15)");
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
        return "E|" + super.toString() + "|" + this.from + "|" + this.to + "|" + getTag();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" + "\n\tTags: " + getTag();
    }
}
