package gigi;

import gigi.parser.Parser;
import gigi.storage.Storage;
import gigi.task.TaskList;
import gigi.ui.Ui;

import java.util.ArrayList;

public class Gigi {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Gigi("data/gigi.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
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