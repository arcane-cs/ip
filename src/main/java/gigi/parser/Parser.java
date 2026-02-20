package gigi.parser;

import gigi.GigiException;
import gigi.task.Deadline;
import gigi.task.Event;
import gigi.task.Task;
import gigi.task.TaskList;
import gigi.task.Todo;
import gigi.ui.Ui;

/**
 * Handles the interpretation of user commands and coordinates the
 * execution of tasks, UI updates, and data modifications.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input The full raw string input provided by the user.
     * @param tasks The TaskList object to be modified.
     * @param ui    The Ui object used for user interaction.
     * @return The response message to be displayed in the UI.
     * @throws GigiException If the command is invalid or arguments are missing.
     */
    public static String parse(String input, TaskList tasks, Ui ui) throws GigiException {
        try {
            String[] split = input.trim().split(" ", 2);
            String command = split[0].toLowerCase();
            String arguments = split.length < 2 ? "" : split[1].trim();

            return switch (command) {
                case "bye" -> ui.showMessage("Bye. Hope to you see you again soon!");
                case "hello" -> ui.showWelcome();
                case "list" -> ui.showMessage("Here's your list:\n" + tasks.printList());
                case "find" -> handleFind(arguments, tasks, ui);
                case "mark", "unmark", "delete" -> handleTaskAugmentation(command, arguments, tasks, ui);
                case "todo", "deadline", "event" -> handleAddTask(command, arguments, tasks, ui);
                case "tag" -> handleTag(arguments, tasks);
                default -> ui.showMessage("Invalid command :(");
            };
        } catch (GigiException e) {
            return ui.showMessage(e.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showMessage("Give a valid index!\n" + tasks.printList());
        }
    }

    private static String handleTag(String args, TaskList tasks) {
        String[] parts = args.split(" ", 3);
        String action = parts[0];
        int idx = Integer.parseInt(parts[1]) - 1;
        String tag = parts[2];
        if (action.equals("add")) {
            tasks.addTag(idx, tag);
            return "Added Tag!";
        } else if (action.equals("remove")) {
            tasks.removeTag(idx, tag);
            return "Removed Tag...";
        } else {
            return "Invalid Tag Action!";
        }
    }

    private static String handleFind(String args, TaskList tasks, Ui ui) {
        if (args.isEmpty()) {
            return ui.showMessage("Please provide a keyword to find!");
        }
        String result = tasks.findString(args);
        return result.isEmpty() ? ui.showMessage("No matches found") : "Here you go:\n" + ui.showMessage(result);
    }

    private static String handleTaskAugmentation(String command, String args, TaskList tasks, Ui ui) {
        if (args.isEmpty()) {
            return ui.showMessage("Please provide a task index!");
        }
        int idx = Integer.parseInt(args) - 1;

        if (command.equals("delete")) {
            String response = ui.showMessage("I have removed the following task:\n\t" + tasks.printTask(idx));
            tasks.deleteTask(idx);
            return response;
        }

        if (command.equals("mark")) {
            tasks.markTask(idx);
            return ui.showMessage("I have marked the task as done:\n\t" + tasks.printTask(idx));
        }

        tasks.unmarkTask(idx);
        return ui.showMessage("I have unmarked the task as done:\n\t" + tasks.printTask(idx));
    }

    private static String handleAddTask(String command, String args, TaskList tasks, Ui ui) throws GigiException {
        if (args.isEmpty()) {
            throw new GigiException("The description of a " + command + " cannot be empty.");
        }

        Task newTask = switch (command) {
            case "todo" -> new Todo(args);
            case "deadline" -> createDeadline(args);
            case "event" -> createEvent(args);
            default -> throw new GigiException("Unexpected task type.");
        };

        tasks.addTask(newTask);
        return ui.showMessage("added: \n\t" + newTask + "\nThere are now " + tasks.size() + " tasks in the list.");
    }

    private static Deadline createDeadline(String args) throws GigiException {
        String[] parts = args.split("/by", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new GigiException("Provide a '/by' date for your deadline!");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    private static Event createEvent(String args) throws GigiException {
        String[] parts = args.split("/from", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new GigiException("Provide a '/from' time for your event!");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
            throw new GigiException("Provide a '/to' time for your event!");
        }
        return new Event(description, timeParts[0].trim(), timeParts[1].trim());
    }
}