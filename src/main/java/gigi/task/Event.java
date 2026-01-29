package gigi.task;

import gigi.GigiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    LocalDate from;
    LocalDate to;


    public Event(String task, String from, String to){
        super(task);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
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
        return "E|" + super.toString() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
