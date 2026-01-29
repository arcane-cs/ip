package gigi.storage;

import gigi.GigiException;
import gigi.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws GigiException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            for (String line : Files.readAllLines(file.toPath())) {
                String[] component = line.split("\\|");
                boolean isDone = component[1].startsWith("[X]");
                component[1] = component[1].substring(4);
                switch (component[0]) {
                    case "D" -> {
                        if (component.length > 3) {
                            throw new GigiException("Invalid gigi.task.Task from Saved Data");
                        }
                        tasks.add(new Deadline(component[1], component[2]));
                    }
                    case "E" -> {
                        if (component.length > 4) {
                            throw new GigiException("Invalid gigi.task.Task from Saved Data");
                        }
                        tasks.add(new Event(component[1], component[2], component[3]));
                    }
                    case "T" -> {
                        if (component.length > 2) {
                            throw new GigiException("Invalid gigi.task.Task from Saved Data");
                        }
                        tasks.add(new Todo(component[1]));
                    }
                    default -> throw new GigiException("Invalid gigi.task.Task from Saved Data");
                }
                if (isDone) {
                    tasks.get(tasks.size()-1).markDone();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occured while reading data: " + e.getMessage());
        } catch (GigiException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid gigi.task.Task from Saved Data");
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        File file = new File(filePath);
        try{
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(task.serialize()+"\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving data: " + e.getMessage());
        }
    }
}