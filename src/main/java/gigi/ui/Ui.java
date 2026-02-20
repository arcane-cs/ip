package gigi.ui;

import java.util.Scanner;

/**
 * Handles user interactions for the Gigi application.
 * Provides methods to read input from the console and format messages for the display.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Returns a welcome message and the application logo.
     * @return The welcome greeting string.
     */
    public String showWelcome() {
        return "Hello from\nWhat can I do for you?";
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String showMessage(String message) {
        return message;
    }

    public String showError(String message) {
        return "Error: " + message;
    }
}
