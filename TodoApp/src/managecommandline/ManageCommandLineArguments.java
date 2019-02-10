package managecommandline;

import taskhandling.TaskHandler;

public class ManageCommandLineArguments {

    public void getCommand(String[] args) {

        String possibleCommandLineArguments = "Command Line Todo application\n" +
                "=============================\n" +
                "\n" +
                "Command line arguments:\n" +
                " -l   Lists all the tasks\n" +
                " -a   Adds a new task\n" +
                " -r   Removes a task\n" +
                " -c   Completes a task";
        if (args.length == 0) {
            System.out.println(possibleCommandLineArguments);
        } else {
            switch (args[0]) {
                case "-l":
                    TaskHandler.getTodoList();
                    break;
                case "-a":
                    if (args.length == 2) {
                        TaskHandler.addTask(args[1]);
                    } else {
                        System.out.println("Unable to add: no task provided.");
                    }
                    break;
                case "-r":
                    if (args.length == 2) {
                        TaskHandler.removeTask(args[1]);
                    } else {
                        System.out.println("Unable to remove: no index provided");
                    }
                    break;
                case "-c":
                    if (args.length == 2) {
                        TaskHandler.checkFinishedTask(args[1]);
                    } else {
                        System.out.println("Unable to check: no index provided");
                    }
                    break;
                default:
                    System.out.println("Unsupported argument!\n\n" +
                            possibleCommandLineArguments);
            }
        }
    }
}