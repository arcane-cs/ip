package gigi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gigi.parser.Parser;
import gigi.task.TaskList;
import gigi.ui.Ui;

/**
 * Test suite for the Parser class.
 * Focuses on handling edge cases and validating error messages for incorrect command syntax.
 */
public class ParserTest {

    /**
     * A test double for the Ui class to capture output for verification.
     */
    static class UiStub extends Ui {
        private String lastMessage;

        @Override
        public String showMessage(String m) {
            return this.lastMessage = m;
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }

    /**
     * Verifies that the parser identifies a missing "/by" delimiter in deadline commands
     * and returns the appropriate error message without modifying the task list.
     */
    @Test
    public void parse_deadlineMissingByDelimiter_errorMessageCaptured() {
        TaskList tasks = new TaskList(new ArrayList<>());
        UiStub ui = new UiStub();

        // Non-trivial: Command is "deadline" but lacks the "/by" keyword
        String input = "deadline Submit report by tomorrow";

        Parser.parse(input, tasks, ui);

        assertEquals("Provide a 'by' for your deadline!", ui.getLastMessage());
        assertEquals(0, tasks.size(), "Task should not be added on failure");
    }

    /**
     * Verifies that the parser identifies a missing "/to" delimiter in event commands
     * and returns the appropriate error message without modifying the task list.
     */
    @Test
    public void parse_eventIncompleteSplit_errorMessageCaptured() {
        TaskList tasks = new TaskList(new ArrayList<>());
        UiStub ui = new UiStub();

        // Non-trivial: Command has "/from" but is missing the "/to" segment
        String input = "event Career Fair /from Monday 10am";

        Parser.parse(input, tasks, ui);

        assertEquals("Provide a 'to' for your event!", ui.getLastMessage());
        assertEquals(0, tasks.size(), "Task should not be added on failure");
    }
}
