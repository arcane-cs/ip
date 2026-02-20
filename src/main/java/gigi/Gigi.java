package gigi;

import java.util.ArrayList;

import gigi.parser.Parser;
import gigi.storage.Storage;
import gigi.task.TaskList;
import gigi.ui.Ui;

/**
 * Main logic class for the Gigi chatbot.
 * Coordinates the storage, task list, and user interface components.
 */
public class Gigi {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the chatbot by loading data from a specified file path.
     * @param filePath Path to the storage file.
     * @throws GigiException If initialization fails.
     */
    public Gigi(String filePath) throws GigiException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GigiException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Starts the command-line interface loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.equals("bye")) {
                    isExit = true;
                }
                Parser.parse(fullCommand, tasks, ui);

            } catch (GigiException e) {
                ui.showError(e.getMessage());
            }
            storage.save(tasks);
        }
    }

    /**
     * Entry point for the command-line application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Gigi("data/gigi.txt").run();
    }

    /**
     * Processes user input and returns a response string for the GUI.
     * Saves the current task list state after processing.
     * @param input User message.
     * @return Bot response message.
     */
    public String getResponse(String input) {
        String response;

        try {
            response = Parser.parse(input, tasks, ui);
        } catch (GigiException e) {
            response = ui.showError(e.getMessage());
        }

        storage.save(tasks);
        return response;
    }
}
