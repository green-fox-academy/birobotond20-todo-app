package taskhandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskHandler {

    private static String todoTasksFile = "ToDo_List.txt";

    public static void getTodoList() {

        try {
            Path file = Paths.get(todoTasksFile);

            if (Files.lines(file).count() == 0) {
                System.out.println("No todos for today! :)");
            }

            int count = 0;

            for (String line : Files.readAllLines(file)) {
                if (line.startsWith("[x]")) {
                    System.out.println(++count + " - " + line);
                } else {
                    System.out.println(++count + " - [ ] " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("No " + todoTasksFile + " file exists!\n" +
                    "To create one, add a task with the '-a \"your task\"' command.");
        }
    }

    public static void addTask(String argument) {

        try {
            Path file = Paths.get(todoTasksFile);
            List<String> tasks = new ArrayList<>();

            if (Files.notExists(file)) {
                Files.createFile(file);
                tasks.add(argument);
                Files.write(file, tasks);
                System.out.println("File " + todoTasksFile + "created with the task " + argument);
            } else if (Files.size(file) == 0) {
                tasks.add(argument);
                Files.write(file, tasks);
            } else {
                tasks.addAll(Files.readAllLines(file));
                tasks.add(argument);
                Files.write(file, tasks);
            }

        } catch (IOException e) {
            System.out.println("No such file: " + e.getMessage());
        }
    }

    public static void removeTask(String index) {

        try {
            Path file = Paths.get(todoTasksFile);
            int taskIndex = Integer.parseInt(index) - 1;

            if (Files.notExists(file)) {
                System.out.println("No " + todoTasksFile + " file exists.");
            } else if (Files.size(file) == 0) {
                System.out.println("Unable to remove from empty file.");
            } else {
                List<String> tasks = new ArrayList<>(Files.readAllLines(file));
                tasks.remove(taskIndex);
                Files.write(file, tasks);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to remove: index is out of bound.");
        } catch (NumberFormatException e) {
            System.out.println("Unable to remove: index is not a number.");
        }
    }

    public static void checkFinishedTask(String index) {

        try {
            Path file = Paths.get(todoTasksFile);
            int taskIndex = Integer.parseInt(index) - 1;

            if (Files.notExists(file)) {
                System.out.println("No " + todoTasksFile + "file exists.");
            } else if (Files.size(file) == 0) {
                System.out.println("Unable to check task in empty file.");
            } else {
                List<String> tasks = new ArrayList<>(Files.readAllLines(file));
                if (!(tasks.get(taskIndex).endsWith(" [x]"))) {
                    tasks.set(taskIndex, "[x] " + tasks.get(taskIndex));
                    Files.write(file, tasks);
                } else {
                    System.out.println("Task already checked.");
                }
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to check: index is out of bound.");
        } catch (NumberFormatException e) {
            System.out.println("Unable to check: index is not a number.");
        }
    }
}
