import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages users and their associated tasks by reading user configuration from a file,
 * accepting tasks from standard input, and executing tasks in priority order.
 */
public class TaskManager {
    /**
     * The list of users loaded from the configuration file.
     * @uml.property name="users"
     */
    private ArrayList<MyUser> users;

    /**
     * The priority queue that manages tasks to be executed.
     * @uml.property name="taskQueue"
     */
    private MinHeap<MyTask> taskQueue;

    /**
     * Constructs a TaskManager and loads users from the specified configuration file.
     *
     * @param configFilePath the path to the configuration file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public TaskManager(String configFilePath) throws IOException {
        this.users = new ArrayList<>();
        this.taskQueue = new MinHeap<>();
        loadUsers(configFilePath);
    }

    /**
     * Loads user data from the specified configuration file.
     * Each non-empty, non-commented line should contain a user priority.
     *
     * @param configFilePath the path to the configuration file
     * @throws IOException if an error occurs while reading the file
     */
    private void loadUsers(String configFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            int userId = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    // Remove comments if any
                    if (line.contains("//")) {
                        line = line.substring(0, line.indexOf("//")).trim();
                    }
                    int priority = Integer.parseInt(line);
                    users.add(new MyUser(userId++, priority));
                }
            }
        }
    }

    /**
     * Reads task input from standard input until the keyword "execute" is entered.
     * For each valid user ID input, a new task is created and added to the queue.
     * Invalid inputs are reported to standard error.
     */
    public void getTasks() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("execute")) {
            try {
                int userId = Integer.parseInt(input.trim());
                if (userId < 0 || userId >= users.size()) {
                    System.err.println("Error: Invalid user ID " + userId);
                    continue;
                }
                MyUser user = users.get(userId);
                taskQueue.add(new MyTask(user));
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid input - " + input);
            }
        }
        scanner.close();
    }

    /**
     * Executes all tasks in the priority queue and prints them in order.
     */
    public void executeTasks() {
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }
    }
}

