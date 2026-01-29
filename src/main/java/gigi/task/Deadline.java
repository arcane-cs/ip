package gigi.task;

import gigi.GigiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    LocalDate by;

    public Deadline(String task, String by){
        super(task);

        try {
            this.by = LocalDate.parse(by.trim());
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
        return "D|" + super.toString() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
