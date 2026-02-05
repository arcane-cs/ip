package gigi;

import gigi.parser.Parser;
import gigi.storage.Storage;
import gigi.task.TaskList;
import gigi.ui.Ui;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    // Helper Stub to capture messages instead of printing to console
    class UiStub extends Ui {
        private String lastMessage;

        @Override
        public String showMessage(String m) {
            return this.lastMessage = m;
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }

    @Test
    public void parse_deadlineMissingByDelimiter_errorMessageCaptured() {
        TaskList tasks = new TaskList(new ArrayList<>());
        UiStub ui = new UiStub();
        Storage storage = new Storage("data/test.txt");

        // Non-trivial: Command is "deadline" but lacks the "/by" keyword
        String input = "deadline Submit report by tomorrow";

        Parser.parse(input, tasks, ui);

        assertEquals("Provide a 'by' for your deadline!", ui.getLastMessage());
        assertEquals(0, tasks.size(), "Task should not be added on failure");
    }

    @Test
    public void parse_eventIncompleteSplit_errorMessageCaptured() {
        TaskList tasks = new TaskList(new ArrayList<>());
        UiStub ui = new UiStub();
        Storage storage = new Storage("data/test.txt");

        // Non-trivial: Command has "/from" but is missing the "/to" segment
        String input = "event Career Fair /from Monday 10am";

        Parser.parse(input, tasks, ui);

        assertEquals("Provide a 'to' for your event!", ui.getLastMessage());
        assertEquals(0, tasks.size(), "Task should not be added on failure");
    }
}