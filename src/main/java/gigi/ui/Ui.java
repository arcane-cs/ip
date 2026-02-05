package gigi.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public String showWelcome() {
        String logo = "  ________.__       .__ \n" +
                " /  _____/|__| ____ |__|\n" +
                "/   \\  ___|  |/ ___\\|  |\n" +
                "\\    \\_\\  \\  / /_/  >  |\n" +
                " \\______  /__\\___  /|__|\n" +
                "        \\/  /_____/     ";
        return "Hello from\n" + "What can I do for you?";
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