import managecommandline.ManageCommandLineArguments;

public class Main {

    public static void main(String[] args) {

        ManageCommandLineArguments cli = new ManageCommandLineArguments();
        cli.getCommand(args);
    }
}