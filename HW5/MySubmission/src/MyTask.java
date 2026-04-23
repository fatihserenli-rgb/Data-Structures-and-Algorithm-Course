/**
 * Represents a task associated with a user, where tasks can be compared
 * based on the user's priority and task creation order.
 */
public class MyTask implements Comparable<MyTask> {
    /**
     * The user associated with this task.
     * @uml.property name="user"
     */
    private MyUser user;

    /**
     * The unique ID of this task.
     * @uml.property name="id"
     */
    private Integer id;

    /**
     * The next ID to be assigned to a new task.
     * @uml.property name="nextId"
     */
    private static int nextId = 0;

    /**
     * Constructs a new MyTask associated with the given user.
     * The task ID is assigned automatically.
     *
     * @param user the user associated with this task
     */
    public MyTask(MyUser user) {
        this.user = user;
        this.id = nextId++;
    }

    /**
     * Returns a string representation of the task, including its ID and associated user ID.
     *
     * @return a string describing the task
     */
    @Override
    public String toString() {
        return "Task " + id + " User " + user.getID();
    }

    /**
     * Compares this task with another task based on user priority and task ID.
     * Tasks with higher priority users come first; if equal, earlier tasks come first.
     *
     * @param other the task to be compared
     * @return a negative integer, zero, or a positive integer as this task
     *         is less than, equal to, or greater than the specified task
     */
    @Override
    public int compareTo(MyTask other) {
        int priorityComparison = this.user.getPriority().compareTo(other.user.getPriority());
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        return this.id.compareTo(other.id);
    }
}

