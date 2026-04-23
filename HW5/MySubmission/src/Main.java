import java.io.IOException;

/**
 * Entry point of the program. Initializes and runs the TaskManager.
 */
public class Main {
    /**
     * Main method that starts the program.
     * Expects a configuration file path as the first command-line argument.
     *
     * @param args command-line arguments; the first argument should be the path to the config file
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Program will not run without entering config file name.Exitting...");
            return;
        }

        try {
            TaskManager tm = new TaskManager(args[0]);
            tm.getTasks();
            tm.executeTasks();
        } catch (IOException e) {
            System.err.println("File not found:\"" +args[0] +"\".Exitting...");
            return;
        }
    }
}

